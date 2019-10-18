package org.gentten.codegeneratorweb.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import org.gentten.framework.common.domain.base.BaseEntity;
import org.gentten.framework.common.util.EmptyUtils;
import org.gentten.framework.common.util.ReflectionUtils;
import org.gentten.framework.common.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询工具类
 *
 * @author : duanzhiqiang
 * @date : 2019-10-18 17:03
 */
public class QueryUtils {

    /**
     * 将实体中的字段找到mybatis-plus 指定的列名
     *
     * @param field 字段
     * @return 列名
     */
    public static String getTableColumnName(Field field) {
        TableField annotationFiled = field.getAnnotation(TableField.class);
        TableId annotationId = field.getAnnotation(TableId.class);
        String res;
        if (annotationFiled != null && !EmptyUtils.isEmpty(res = annotationFiled.value())) {
            return res;
        } else if (annotationId != null && !EmptyUtils.isEmpty(res = annotationId.value())) {
            return res;
        } else {
            //驼峰转下划线
            return StringUtils.humpConvertLowCase(field.getName());
        }
    }

    /**
     * @param entityQuery 实体查询数据
     * @return 返回列名与值集合
     */
    public static <T extends BaseEntity> Map<String, Object> getColumnMapFormEntity(T entityQuery) {
        Class<?> searchClass = entityQuery.getClass();
        List<Field> fieldLis;
        fieldLis = ReflectionUtils.getEntityFields(entityQuery);
        HashMap<String, Object> res = new HashMap<>();
        fieldLis.parallelStream().filter(QueryUtils::filterNotColumn).forEach(field -> {
            // 去掉序列化id
            if (!"".equals(field.getName())) {
                res.put(QueryUtils.getTableColumnName(field), ReflectionUtils.getFieldValue(entityQuery, field.getName()));
            }
        });
        return res;
    }

    /**
     * 过滤掉非数据库字段
     *
     * @param field 实体中字段包含父类的
     * @return 是数据库中字段为true
     */
    private static boolean filterNotColumn(Field field) {
        if ("serialVersionUID".equals(field.getName())) {
            return false;
        }
        return true;
    }

}

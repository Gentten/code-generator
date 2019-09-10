package org.gentten.codegeneratorweb.domain.form.query;


import org.gentten.codegeneratorweb.domain.entity.ModuleGroupRelation;
import org.gentten.codegeneratorweb.domain.form.search.ModuleGroupRelationSearch;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.gentten.framework.common.domain.base.BaseEntity;
import org.gentten.framework.common.domain.query.BaseQuery;
import org.gentten.framework.common.domain.query.pagination.Sorter;
import org.gentten.framework.common.util.EmptyUtils;
import org.gentten.framework.common.util.ReflectionUtils;
import org.gentten.framework.common.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 代码模板组关联表查询封装查询条件
 * 原因是当需求修改时需要添加查询添加条件，可以减少controller层代码的修改
 * <p>
 * QueryWrapper 支持很多查询  参考https://mp.baomidou.com/guide/wrapper.html#abstractwrapper
 * {@link BaseQuery}BaseQuery 封装常用的查询条件（实现各种类型的查询） 可选
 *
 * @author : code-generator
 * @date : Tue Sep 10 12:46:04 CST 2019
 */
@Data
@ApiModel(value = "代码模板组关联表编辑创建表单")
public class ModuleGroupRelationQuery extends BaseQuery<ModuleGroupRelation> {
    /**
     * 此处可替换成其他包装查询字段，但变量名需要定义为search 与前端约定的。然后在buildWrapper 实现自己多字段复杂查询
     * 需要注意   column 是数据库字段名字
     */
    @ApiModelProperty(value = "查询参数（以and 连接条件并以eq的形式）")
    private ModuleGroupRelationSearch search;

    @Override
    public QueryWrapper<ModuleGroupRelation> buildWrapper() {
        QueryWrapper<ModuleGroupRelation> queryWrapper = new QueryWrapper<>();
        //查询条件
        if (search != null) {
            Map<String, Object> searchMap = getSearchFields(search);
            if (!EmptyUtils.isEmpty(searchMap)) {
                searchMap.forEach((column, value) -> {
                    queryWrapper.eq(value != null, column, value);
                });
            }
        }
        //第一个参数表示是否拼接到最后生成sql 语句中，即true 时起作用， 注意驼峰原则转下划线
        // 单字段排序
        Sorter sorter;
        String order;
        if ((sorter = this.getSorter()) != null) {
            queryWrapper.orderBy(true, (order = sorter.getOrder()) != null && order.toLowerCase().contains("asc"), StringUtils.humpConvertLowCase(sorter.getField()));
        }
        return queryWrapper;
    }

    /**
     * todo：此方法暂时只支持继承BaseEntity的 ，其他会存在问题（需要类字段名到数据库字段名的转换）
     *
     * @param search
     * @return
     */
    private Map<String, Object> getSearchFields(Object search) {
        Class<?> searchClass = search.getClass();
        List<Field> fieldLis;
        if (search instanceof BaseEntity) {
            fieldLis = ReflectionUtils.getEntityFields((BaseEntity) search);
        } else {
            fieldLis = Arrays.asList(searchClass.getDeclaredFields());
        }
        HashMap<String, Object> res = new HashMap<>();
        fieldLis.parallelStream().forEach(field -> {
            // 去掉序列化id
            if (!"serialVersionUID".equals(field.getName())) {
                res.put(getTableColumnName(field), ReflectionUtils.getFieldValue(search, field.getName()));
            }
        });
        return res;
    }

    private String getTableColumnName(Field field) {
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

}

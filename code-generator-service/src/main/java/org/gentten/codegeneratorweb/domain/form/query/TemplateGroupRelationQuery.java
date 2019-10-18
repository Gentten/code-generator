package org.gentten.codegeneratorweb.domain.form.query;


import lombok.EqualsAndHashCode;
import org.gentten.codegeneratorweb.domain.entity.TemplateGroupRelation;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.gentten.codegeneratorweb.utils.QueryUtils;
import org.gentten.framework.common.domain.query.BaseQuery;
import org.gentten.framework.common.domain.query.pagination.Sorter;
import org.gentten.framework.common.util.EmptyUtils;
import org.gentten.framework.common.util.StringUtils;

import java.util.Map;

/**
 * 代码模板组
 *
 * @author : code-generator
 * @date : Tue Sep 10 12:46:04 CST 2019
 */
@Data
@ApiModel(value = "代码模板组关联表编辑创建表单")
@EqualsAndHashCode(callSuper = true)
public class TemplateGroupRelationQuery extends BaseQuery<TemplateGroupRelation> {
    /**
     * 此处可替换成其他包装查询字段，但变量名需要定义为search 与前端约定的。然后在buildWrapper 实现自己多字段复杂查询
     * 需要注意   column 是数据库字段名字
     */
    @ApiModelProperty(value = "数据库eq查询参数")
    private TemplateGroupRelation eq;

    @Override
    public QueryWrapper<TemplateGroupRelation> buildWrapper() {
        QueryWrapper<TemplateGroupRelation> queryWrapper = new QueryWrapper<>();
        //查询条件
        if (eq != null) {
            Map<String, Object> searchMap = QueryUtils.getColumnMapFormEntity(eq);
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


}

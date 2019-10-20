package org.gentten.codegeneratorweb.domain.form.query;


import org.gentten.codegeneratorweb.domain.entity.CodeTemplate;
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
 * 代码模板查询封装查询条件
 * 原因是当需求修改时需要添加查询添加条件，可以减少controller层代码的修改
 * <p>
 * QueryWrapper 支持很多查询  参考https://mp.baomidou.com/guide/wrapper.html#abstractwrapper
 * {@link BaseQuery}BaseQuery 封装常用的查询条件（实现各种类型的查询） 可选
 *
 * @author : code-generator
 * @date : Tue Sep 10 11:58:08 CST 2019
 */
@Data
@ApiModel(value = "代码模板编辑创建表单")
public class CodeTemplateQuery extends BaseQuery<CodeTemplate> {
    /**
     * 此处可替换成其他包装查询字段，但变量名需要定义为search 与前端约定的。然后在buildWrapper 实现自己多字段复杂查询
     * 需要注意   column 是数据库字段名字
     */
    @ApiModelProperty(value = "查询参数（以and 连接条件并以eq的形式）")
    private CodeTemplate eq;

    @Override
    public QueryWrapper<CodeTemplate> buildWrapper() {
        QueryWrapper<CodeTemplate> queryWrapper = new QueryWrapper<>();
        //eq查询条件
        if (eq != null) {
            Map<String, Object> searchMap = QueryUtils.entity2ColumnMap(eq);
            queryWrapper.allEq(searchMap, false);
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

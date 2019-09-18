package org.gentten.codegeneratorweb.service;

import org.gentten.codegeneratorweb.domain.entity.TemplateGroupRelation;
import org.gentten.codegeneratorweb.domain.form.query.TemplateGroupRelationQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * 代码模板组关联表服务
 * 1、继承IService接口{@link IService}直接获得curd方法不用自己去声明  泛型指定对应的实体
 * 2、当IService不满足业务需求时，自己自定义方法
 *
 * @author : code-generator
 * @date : Tue Sep 10 12:50:35 CST 2019
 */
public interface ModuleGroupRelationService extends IService<TemplateGroupRelation> {
    /**
     * 代码模板组关联表查询
     *
     * @param query 查询条件
     * @return 查询结果
     */
    PageInfo<TemplateGroupRelation> search(TemplateGroupRelationQuery query);

}

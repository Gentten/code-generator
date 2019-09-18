package org.gentten.codegeneratorweb.service;

import org.gentten.codegeneratorweb.domain.entity.CodeTemplateGroup;
import org.gentten.codegeneratorweb.domain.form.query.CodeTemplateGroupQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * 代码模板组服务
 * 1、继承IService接口{@link IService}直接获得curd方法不用自己去声明  泛型指定对应的实体
 * 2、当IService不满足业务需求时，自己自定义方法
 *
 * @author : code-generator
 * @date : Tue Sep 10 12:49:55 CST 2019
 */
public interface CodeModuleGroupService extends IService<CodeTemplateGroup> {
    /**
     * 代码模板组查询
     *
     * @param query 查询条件
     * @return 查询结果
     */
    PageInfo<CodeTemplateGroup> search(CodeTemplateGroupQuery query);



}

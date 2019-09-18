package org.gentten.codegeneratorweb.service;

import org.gentten.codegeneratorweb.domain.entity.CodeTemplate;
import org.gentten.codegeneratorweb.domain.form.query.CodeTemplateQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 代码模板服务
 * 1、继承IService接口{@link IService}直接获得curd方法不用自己去声明  泛型指定对应的实体
 * 2、当IService不满足业务需求时，自己自定义方法
 *
 * @author : code-generator
 * @date : Tue Sep 10 11:56:31 CST 2019
 */
public interface CodeModuleService extends IService<CodeTemplate> {
    /**
     * 代码模板查询
     *
     * @param query 查询条件
     * @return 查询结果
     */
    PageInfo<CodeTemplate> search(CodeTemplateQuery query);

    /**
     * 按模板组获取 groupId
     *
     * @param groupId 模板组id
     * @return 模板
     */
    List<CodeTemplate> getByGroupId(String groupId);
}

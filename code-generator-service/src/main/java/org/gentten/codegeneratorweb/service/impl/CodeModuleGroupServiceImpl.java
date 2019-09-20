package org.gentten.codegeneratorweb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.gentten.codegeneratorweb.domain.entity.CodeTemplateGroup;
import org.gentten.codegeneratorweb.domain.form.query.CodeTemplateGroupQuery;
import org.gentten.codegeneratorweb.mapper.CodeModuleGroupMapper;
import org.gentten.codegeneratorweb.service.CodeModuleGroupService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;


/**
 * 代码模板组服务实现
 * 1、继承ServiceImpl接口{@link ServiceImpl}便可实现继承curd功能即AR模式  泛型指定对应的mapper和实体
 * 2、实现自己定义的方法
 * 3、父类中已经将此实体的对应的mapper已经注入了，不需要再次注入。当然你可以注入其他mapper
 *
 * @author : code-generator
 * @date : Tue Sep 10 12:52:40 CST 2019
 */
@Service
@Slf4j
public class CodeModuleGroupServiceImpl extends ServiceImpl<CodeModuleGroupMapper, CodeTemplateGroup> implements CodeModuleGroupService {

    @Override
    public PageInfo<CodeTemplateGroup> search(CodeTemplateGroupQuery query) {
        //不分页则1到99999
        return PageHelper.startPage(query.getListMode() ? 1 : query.getPageNum(), query.getListMode() ? 999999 : query.getPageSize())
                .doSelectPageInfo(() -> list(query.buildWrapper()));
    }

}
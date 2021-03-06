package org.gentten.codegeneratorweb.service.impl;

import org.gentten.codegeneratorweb.domain.entity.TemplateGroupRelation;
import org.gentten.codegeneratorweb.domain.form.query.TemplateGroupRelationQuery;
import org.gentten.codegeneratorweb.mapper.ModuleGroupRelationMapper;
import org.gentten.codegeneratorweb.service.ModuleGroupRelationService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;


/**
 * 代码模板组关联表服务实现
 * 1、继承ServiceImpl接口{@link ServiceImpl}便可实现继承curd功能即AR模式  泛型指定对应的mapper和实体
 * 2、实现自己定义的方法
 * 3、父类中已经将此实体的对应的mapper已经注入了，不需要再次注入。当然你可以注入其他mapper
 *
 * @author : code-generator
 * @date : Tue Sep 10 12:51:33 CST 2019
 */
@Service
public class ModuleGroupRelationServiceImpl extends ServiceImpl<ModuleGroupRelationMapper, TemplateGroupRelation> implements ModuleGroupRelationService {

    @Override
    public PageInfo<TemplateGroupRelation> search(TemplateGroupRelationQuery query) {
        //不分页则1到99999
        return PageHelper.startPage(query.getListMode() ? 1 : query.getPageNum(), query.getListMode() ? 999999 : query.getPageSize())
                .doSelectPageInfo(() -> list(query.buildWrapper()));
    }

}
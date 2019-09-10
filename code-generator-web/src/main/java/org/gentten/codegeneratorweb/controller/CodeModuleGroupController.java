package org.gentten.codegeneratorweb.controller;


import com.github.pagehelper.PageInfo;
import org.gentten.codegeneratorweb.domain.form.edit.CodeModuleGroupEditForm;
import org.gentten.codegeneratorweb.service.CodeModuleGroupService;
import org.gentten.codegeneratorweb.domain.entity.CodeModuleGroup;
import org.gentten.codegeneratorweb.domain.form.query.CodeModuleGroupQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.gentten.framework.common.domain.base.OperateInfo;
import org.gentten.framework.common.domain.respone.R;
import org.gentten.framework.common.util.TransformUtils;
import org.gentten.framework.common.validation.group.Create;
import org.gentten.framework.common.validation.group.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * 代码模板组controller
 *
 * @author : code-generator
 * @date : Tue Sep 10 14:01:48 CST 2019
 */
@Api("代码模板组管理")
@RequestMapping("CodeModuleGroup")
@RestController
public class CodeModuleGroupController {

    @Resource
    private CodeModuleGroupService codeModuleGroupService;

    @ApiOperation("代码模板组分页查询以及简单eq查询")
    @GetMapping
    public R<PageInfo<CodeModuleGroup>> list(CodeModuleGroupQuery query) {
        return R.success(codeModuleGroupService.search(query));
    }

    @ApiOperation("代码模板组创建")
    @PostMapping
    public R<Boolean> create(@Validated(Create.class) @RequestBody CodeModuleGroupEditForm form, @ApiIgnore OperateInfo operateInfo) throws Exception {
        return R.success(codeModuleGroupService.save(TransformUtils.transform(form, CodeModuleGroup.class)));
    }

    @ApiOperation("根据id更新代码模板组")
    @PutMapping("{id}")
    public R<Boolean> update(@PathVariable String id, @Validated(Update.class) @RequestBody CodeModuleGroupEditForm form) throws Exception {
        form.setId(id);
        return R.success(codeModuleGroupService.updateById(TransformUtils.transform(form, CodeModuleGroup.class)));
    }

    @ApiOperation("代码模板组删除，根据ID集合(支持批量删除，如 /menu/1,2,3 逗号隔开 删除id为1和2和3的),需要注意的是不同游览器对url长度限制不一样，建议限制顶多删除一页的数据")
    @DeleteMapping("{ids}")
    public R<Boolean> delete(@PathVariable List<String> ids, @ApiIgnore OperateInfo operateInfo) {
        return R.success(codeModuleGroupService.removeByIds(ids));
    }

    @ApiOperation("根据ID获取代码模板组记录")
    @GetMapping("{id}")
    public R<CodeModuleGroup> searchById(@PathVariable String id) {
        return R.success(codeModuleGroupService.getById(id));
    }
}

package [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/][(${module.packageName})];

import com.act.framework.common.domain.base.OperateInfo;
import com.act.framework.common.domain.respone.R;
import com.act.framework.common.util.TransformUtils;
import com.act.framework.common.validation.group.Create;
import com.act.framework.common.validation.group.Update;
import com.github.pagehelper.PageInfo;
//todo:后面改成代码模板之间的依赖
import [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/]domain.form.edit.[(${model.className})]EditForm;
import [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/]service.[(${model.className})]Service;
import [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/]domain.entity.[(${model.className})];
import [(${model.packageName})].[# th:if="${model.moduleName}"][(${model.moduleName})].[/]domain.form.query.[(${model.className})]Query;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * [(${model.comment})]controller
 *
 * @author : [(${model.operatorName})]
 * @date : [(${model.createTime})]
 */
@Api("[(${model.comment})]管理")
@RequestMapping("[(${model.varName})]")
@RestController
public class [(${model.className})]Controller {

    @Resource
    private [(${model.className})]Service [(${model.varName})]Service;

    @ApiOperation("[(${model.comment})]分页查询以及简单eq查询")
    @GetMapping
    public R<PageInfo<[(${model.className})]>> list([(${model.className})]Query query) {
        return R.success([(${model.varName})]Service.search(query));
    }

    @ApiOperation("[(${model.comment})]创建")
    @PostMapping
    public R<Boolean> create(@Validated(Create.class) @RequestBody [(${model.className})]EditForm form, @ApiIgnore OperateInfo operateInfo) {
        return R.success([(${model.varName})]Service.save(TransformUtils.transform(form, [(${model.className})].class)));
    }

    @ApiOperation("根据id更新[(${model.comment})]")
    @PutMapping("{id}")
    public R<Boolean> update(@PathVariable [(${model.tableId.dataType.name})] id, @Validated(Update.class) @RequestBody [(${model.className})]EditForm form) {
        form.setId(id);
        return R.success([(${model.varName})]Service.updateById(TransformUtils.transform(form, [(${model.className})].class)));
    }

    @ApiOperation("[(${model.comment})]删除，根据ID集合(支持批量删除，如 /menu/1,2,3 逗号隔开 删除id为1和2和3的),需要注意的是不同游览器对url长度限制不一样，建议限制顶多删除一页的数据")
    @DeleteMapping("{ids}")
    public R<Boolean> delete(@PathVariable List<[(${model.tableId.dataType.name})]> ids, @ApiIgnore OperateInfo operateInfo) {
        return R.success([(${model.varName})]Service.removeByIds(ids));
    }

    @ApiOperation("根据ID获取[(${model.comment})]记录")
    @GetMapping("{id}")
    public R<[(${model.className})]> searchById(@PathVariable [(${model.tableId.dataType.name})] id) {
        return R.success([(${model.varName})]Service.getById(id));
    }
}

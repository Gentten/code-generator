package org.gentten.codegeneratorweb.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.gentten.codegeneratorweb.common.converter.DataConverter;
import org.gentten.codegeneratorweb.common.jdbc.MysqlJdbc;
import org.gentten.codegeneratorweb.domain.entity.CodeTemplate;
import org.gentten.codegeneratorweb.domain.entity.Field;
import org.gentten.codegeneratorweb.domain.entity.Model;
import org.gentten.codegeneratorweb.domain.form.edit.CodeGeneratorForm;
import org.gentten.codegeneratorweb.domain.metadata.Column;
import org.gentten.codegeneratorweb.domain.metadata.Table;
import org.gentten.codegeneratorweb.service.CodeModuleService;
import org.gentten.codegeneratorweb.utils.GeneratorUtils;
import org.gentten.codegeneratorweb.utils.ZipUtils;
import org.gentten.framework.common.exception.SysException;
import org.gentten.framework.common.util.CheckUtils;
import org.gentten.framework.common.util.StringUtils;
import org.gentten.framework.common.util.WebUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 代码生成控制器
 *
 * @author : duanzhiqiang
 * @date : 2019-09-09 16:50
 */
@Api("下载管理")
@Slf4j
@RestController
@RequestMapping("generator")
public class GeneratorController {

    @Resource
    private GeneratorUtils generatorUtils;

    @Resource
    private CodeModuleService codeModuleService;

    @PostMapping("/codeModuleGroup_1171316855797764097/{userId}")
    @ApiOperation("act-framework1.0.0增删改查，根据数据库连接生成代码并下载\n" +
            "（要求表有一个字段名为id的主键,默认生成实体是继承OperatorInfo，注意自行选择哪个基础实体,以及去掉和继承实体重复的字段)")
    public void generatorAndDownload(@PathVariable String userId, @Validated @RequestBody CodeGeneratorForm form, @ApiIgnore HttpServletResponse response) throws Exception {
        //连接数据库
        MysqlJdbc mysqlJdbc = new MysqlJdbc(form.getJdbcUrl() + "/" + form.getDbName(), form.getUsername(), form.getPassword());
        //获取表
        List<Table> tables = mysqlJdbc.getTables(form.getDbName());
        //选取特定的表
        Optional<Table> tableOptional = tables.stream().filter(table -> form.getTableName().equals(table.getName())).findAny();
        if (tableOptional.isPresent()) {
            //获取表字段信息
            Table table = tableOptional.get();
            List<Column> columns = mysqlJdbc.getColumns(form.getDbName(), table.getName());
            List<Field> fieldList = columns.stream().map(DataConverter::getFiledByColumn).collect(Collectors.toList());
            //获取主键为id列
            Optional<Field> tableIdOp = fieldList.stream().filter(field -> "id".equals(field.getName())).findAny();

            //获取需要导包的包名 需要去重
            List<String> needImport = fieldList.stream().map(field -> field.getDataType().getPackageName()).distinct().collect(Collectors.toList());
            //构建model
            Model model = Model.builder()
                    .className(StringUtils.capitalize(StringUtils.camelCaseName(table.getName())))
                    //去掉换行和回车符避免生成代码时被换行
                    .comment(table.getComment().replaceAll("[\\r|\\n]", " "))
                    .fields(fieldList)
                    .packageName(form.getPackageName())
                    .moduleName(form.getModuleName())
                    .varName(StringUtils.camelCaseName(table.getName()))
                    .tableId(tableIdOp.orElseThrow(() -> new SysException("表中没有为列名为id的主键")))
                    .build();
            model.setCreateTime(new Date());
            model.setOperatorName(String.format("%s by code-generator-web", userId));

            //暂时写死 todo：通过界面选择生成代码模块
            List<CodeTemplate> codeTemplates = codeModuleService.getByGroupId("1171316855797764097");
            CheckUtils.notEmpty(codeTemplates, "当前模块组没有关联模块或者模板组不存在");
            //生成代码
            generatorUtils.generatorCodeByModel(model, userId, "1171316855797764097", codeTemplates.toArray(new CodeTemplate[0]));
            //设置为文件下载
            WebUtils.setDownloadHeader(response, table.getName() + ".zip");
            //压缩
            ZipUtils.toZip(generatorUtils.getModuleSavePath(userId, "1171316855797764097"), response.getOutputStream());

        } else {
            throw new SysException(String.format("找不到指定的表:%s,请检查参数", form.getTableName()));
        }
    }


}

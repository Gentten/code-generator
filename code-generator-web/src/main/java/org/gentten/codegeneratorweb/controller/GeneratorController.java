package org.gentten.codegeneratorweb.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.gentten.codegeneratorweb.common.converter.DataConverter;
import org.gentten.codegeneratorweb.common.jdbc.MysqlJdbc;
import org.gentten.codegeneratorweb.domain.entity.CodeModule;
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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    private TemplateEngine templateEngine;

    @Resource
    private GeneratorUtils generatorUtils;

    @Resource
    private CodeModuleService codeModuleService;

    @PostMapping("/codeModuleGroup_1171316855797764097/{userId}")
    @ApiOperation("根据数据库连接，生成代码并下载")
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
                    .comment(table.getComment())
                    .fields(fieldList)
                    .packageName(form.getPackageName())
                    .moduleName(form.getModuleName())
                    .varName(StringUtils.camelCaseName(table.getName()))
                    .tableId(tableIdOp.orElseThrow(() -> new SysException("表中没有为列名为id的主键")))
                    .build();
            model.setCreateTime(new Date());
            model.setOperatorName("code-generator");

            List<CodeModule> codeModules = codeModuleService.getByGroupId("1171316855797764097");
            CheckUtils.notEmpty(codeModules, "当前模块组没有关联模块或者模板组不存在");
            //生成代码
            generatorUtils.generatorCode(codeModules, model, userId, "1171316855797764097");

            WebUtils.setDownloadHeader(response, table.getName() + ".zip");
            ZipUtils.toZip(generatorUtils.getModuleSavePath("1171316855797764097", userId), response.getOutputStream());

        } else {
            throw new SysException("表不存在");
        }
    }


}

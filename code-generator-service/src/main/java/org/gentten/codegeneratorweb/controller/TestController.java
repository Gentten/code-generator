package org.gentten.codegeneratorweb.controller;

import org.gentten.codegeneratorweb.common.converter.DataTypeConverter;
import org.gentten.codegeneratorweb.common.jdbc.MysqlJdbc;
import org.gentten.codegeneratorweb.domain.entity.CodeTemplate;
import org.gentten.codegeneratorweb.domain.entity.Field;
import org.gentten.codegeneratorweb.domain.entity.Model;
import org.gentten.codegeneratorweb.domain.metadata.Column;
import org.gentten.codegeneratorweb.domain.metadata.Table;
import org.gentten.framework.common.exception.SysException;
import org.gentten.framework.common.util.StringUtils;
import org.gentten.framework.common.util.WebUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : duanzhiqiang
 * @date : 2019-09-20 09:54
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Resource
    private TemplateEngine templateEngine;

    @GetMapping("/thymeleafTest/{template}")
    public void learn(HttpServletResponse response, @PathVariable String template) {
        //创建Context对象(存放Model)
        Context context = new Context();

        MysqlJdbc mysqlJdbc = new MysqlJdbc("jdbc:mysql://118.25.93.104:3306/code-generator", "root", "123jiayouA!");
        List<Table> tables = mysqlJdbc.getTables("code-generator");
        List<Column> columns = mysqlJdbc.getColumns("code-generator", "test");
        List<Field> fieldList = columns.stream().map(DataTypeConverter::getFiledByColumn).collect(Collectors.toList());
        //获取需要导包的包名 需要去重
        List<String> needImport = fieldList
                .stream()
                .filter(field -> field.getJavaType().getNeedImport())
                .map(field -> field.getJavaType().getImportPackages().split(";"))
                //扁平化
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        Optional<Table> tableOptional = tables.stream().filter(table -> "test".equals(table.getName())).findAny();
        //获取主键为id列
        Optional<Field> tableIdOp = fieldList.stream().filter(field -> "id".equals(field.getName())).findAny();
        tableOptional.ifPresent(table -> {
                    //1、领域对象
                    context.setVariable("model", Model.builder()
                            .className(StringUtils.capitalize(StringUtils.camelCaseName(table.getName())))
                            .comment(table.getComment())
                            .fields(fieldList)
                            .packageName("org.gentten.test")
                            .tableId(tableIdOp.orElseThrow(() -> new SysException("表中没有为列名为id的主键")))
                            .moduleName("xxxxx")
                            .build());
                    //2、需要导的包
                    context.setVariable("imports", needImport);
                    //3、代码生成的后一段包名 即代码模板生成代码放在领域对象报名下那个位置
                    context.setVariable("module", CodeTemplate.builder().packageName("test.entity").build());
                    //设置为文件下载
                    WebUtils.setDownloadHeader(response, template);

                    try (PrintWriter writer = response.getWriter()) {
                        templateEngine.process(template.substring(0,template.indexOf(".")), context, writer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

    }
}

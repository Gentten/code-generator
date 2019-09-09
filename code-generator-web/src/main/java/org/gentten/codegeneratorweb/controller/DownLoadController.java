package org.gentten.codegeneratorweb.controller;

import org.gentten.codegeneratorweb.common.converter.DataConverter;
import org.gentten.codegeneratorweb.common.jdbc.MysqlJdbc;
import org.gentten.codegeneratorweb.domain.entity.Field;
import org.gentten.codegeneratorweb.domain.entity.Model;
import org.gentten.codegeneratorweb.domain.metadata.Column;
import org.gentten.codegeneratorweb.domain.metadata.Table;
import org.gentten.framework.common.util.StringUtils;
import org.gentten.framework.common.util.WebUtils;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 下载
 *
 * @author : duanzhiqiang
 * @date : 2019-09-06 16:07
 */
@Api("下载管理")
@Controller
@RequestMapping("download")
public class DownLoadController {

    @Resource
    private TemplateEngine templateEngine;


    @GetMapping("/thymeleaf/{msg}")
    public void learn(@PathVariable String msg, HttpServletResponse response) throws IOException {
        //创建Context对象(存放Model)
        Context context = new Context();

        MysqlJdbc mysqlJdbc = new MysqlJdbc("jdbc:mysql://118.25.93.104:3306/code-generator", "root", "123jiayouA!");
        List<Table> tables = mysqlJdbc.getTables("code-generator");
        List<Column> columns = mysqlJdbc.getColumns("code-generator", "user");
        List<Field> fieldList = columns.stream().map(DataConverter::getFiledByColumn).collect(Collectors.toList());
        //获取需要导包的包名 需要去重
        List<String> needImport = fieldList.stream().map(field -> field.getDataType().getPackageName()).distinct().collect(Collectors.toList());
        Optional<Table> tableOptional = tables.stream().filter(table -> "user".equals(table.getName())).findAny();
        tableOptional.ifPresent(table -> {

                    context.setVariable("model", Model.builder()
                            .className(StringUtils.capitalize(StringUtils.camelCaseName(table.getName())))
                            .comment(table.getComment())
                            .fields(fieldList)
                            .packageName("org.gentten.test")
                            .moduleName("xxxxx")
                            .build());
                    context.setVariable("imports", needImport);
                    //设置为文件下载
                    WebUtils.setDownloadHeader(response, "TestEntity.java");

                    try (PrintWriter writer = response.getWriter()) {
                        templateEngine.process("Search", context, writer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

    }

}

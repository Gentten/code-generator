package org.gentten.codegeneratorweb.controller;

import org.gentten.codegeneratorweb.domain.entity.metadata.Field;
import org.gentten.codegeneratorweb.domain.entity.metadata.Model;
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
import java.util.ArrayList;

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

        ArrayList<Field> fields = new ArrayList<>();
        fields.add(Field.builder().comment("测试1").name("test1").dataType("String").build());
        fields.add(Field.builder().comment("测试2").name("test2").dataType("BigDecimal").build());
        fields.add(Field.builder().comment("测试3").name("test3").dataType("Long").build());
        fields.add(Field.builder().comment("测试4").name("test4").dataType("Date").build());

        context.setVariable("model", Model.builder().className("TestEntity").comment("模板生成测试").fields(fields).packageName("org.gentten.test").build());


        //设置为文件下载
        WebUtils.setDownloadHeader(response, "TestEntity.java");

        try (PrintWriter writer = response.getWriter()) {
            templateEngine.process("Entity", context, writer);
        }

    }


}

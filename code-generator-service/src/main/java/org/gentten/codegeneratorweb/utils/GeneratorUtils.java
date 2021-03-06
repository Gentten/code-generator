package org.gentten.codegeneratorweb.utils;

import lombok.extern.slf4j.Slf4j;
import org.gentten.codegeneratorweb.domain.entity.CodeTemplate;
import org.gentten.codegeneratorweb.domain.entity.Field;
import org.gentten.codegeneratorweb.domain.entity.Model;
import org.gentten.framework.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 代码生成工具类
 *
 * @author : duanzhiqiang
 * @date : 2019-09-09 17:09
 */
@Slf4j
@Component
public class GeneratorUtils {

    @Resource
    private TemplateEngine templateEngine;
    /**
     * 生成代码的保存位置
     */
    @Value("${generator.savePath:}")
    private String savePath;

    /**
     * 通过领域对象生成代码  /userId/id 为生成文件保存的路径
     *
     * @param model         领域对象
     * @param userId        用户id
     * @param id            id
     * @param codeTemplates 代码模板
     */
    public void generatorCodeByModel(Model model, String userId, String id, CodeTemplate... codeTemplates) {
        //创建Context对象(存放Model)
        Context context = new Context();
        //1、需要一个领域对象
        context.setVariable("model", model);
        //2、需要的导的包
        List<String> importFromFields = getImportFromFields(model.getFields());
        context.setVariable("imports", importFromFields);
        //3、创建之前先清空根临时目录
        FileUtils.clearDir(new File(getModuleSavePath(userId, id)));
        //4、遍历生成代码
        Arrays.asList(codeTemplates).forEach(codeModule -> {
            codeModule.setModel(model);
            generatorCode(codeModule, context, userId, id);
        });
    }

    private void generatorCode(CodeTemplate codeTemplate, Context context, String userId, String id) {
        //1、代码生成的后一段包名
        context.setVariable("module", codeTemplate);
        //2、获取生成代码保存目录 按用户名和模板或者模板id创建的临时目录
        String fileSavePath = getFileSavePath(codeTemplate, userId, id);
        //3、目录不存在则创建，生成文件需要父目录存在
        File baseSaveDir = new File(fileSavePath);
        FileUtils.mkdirs(baseSaveDir);
        //4、获取保存的文件名和后缀
        String saveName = getSaveName(codeTemplate);
        //5、生成代码
        try (FileWriter fileWriter = new FileWriter(fileSavePath + File.separator + saveName)) {
            templateEngine.process(codeTemplate.getCodeTemplateName(), context, fileWriter);
        } catch (IOException e) {
            log.error(String.format("代码生成失败：module %s", codeTemplate.toString()), e);
        }
    }

    private String getSaveName(CodeTemplate codeTemplate) {
        if (codeTemplate.getIsContainsName()) {
            return codeTemplate.getModel().getClassName() + codeTemplate.getCodeTemplateName() + "." + codeTemplate.getFileSuffix();
        } else {
            return codeTemplate.getModel().getClassName() + "." + codeTemplate.getFileSuffix();
        }
    }

    public static List<String> getImportFromFields(List<Field> fieldList) {

        return fieldList
                .stream()
                .filter(field -> field.getJavaType().getNeedImport())
                .map(field -> field.getJavaType().getImportPackages().split(";"))
                //扁平化
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 获取某个模块生成代码的保存路径 压缩、下载使用
     *
     * @param id     区分模块和模块组id
     * @param userId 用户id
     * @return 路径
     */
    public String getModuleSavePath(String userId, String id) {
        if (StringUtils.isBlank(savePath)) {
            log.info(new File(".").getAbsolutePath());
            savePath = "." + File.separator + "code";
        }

        return savePath
                + File.separator
                + userId
                + File.separator
                + id;
    }

    /**
     * 获取某个模块生成代码的保存路径（带包名和划分模块名），去保存使用
     *
     * @param codeTemplate ，codeModule
     * @param userId       用户id
     * @param id           模块或者模板组id 用于临时文件夹的区分
     * @return 路径
     */
    public String getFileSavePath(CodeTemplate codeTemplate, String userId, String id) {

        Model model = codeTemplate.getModel();
        StringBuffer res = new StringBuffer(getModuleSavePath(userId, id));
        //model 设置属于哪个module
        appendPackage(res, model.getPackageName());
        //代码划分模块
        appendModule(res, model.getModuleName());
        //module 设置生成之后多增加的包
        appendPackage(res, codeTemplate.getPackageName());
        return res.toString();
    }

    /**
     * 拼接上包名
     *
     * @param buffer      buffer
     * @param packageName 包名
     */
    private static void appendPackage(StringBuffer buffer, String packageName) {
        if (StringUtils.isNotBlank(packageName)) {

            String[] paths = packageName.split("\\.");
            for (String path : paths) {
                buffer.append(File.separator).append(path);
            }
        }
    }

    /**
     * 拼接上模块名
     *
     * @param buffer     buffer
     * @param moduleName 模块
     */
    private static void appendModule(StringBuffer buffer, String moduleName) {
        if (StringUtils.isNotBlank(moduleName)) {
            buffer.append(File.separator).append(moduleName);
        }
    }
}

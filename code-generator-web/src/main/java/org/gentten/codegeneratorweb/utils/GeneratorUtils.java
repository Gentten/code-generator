package org.gentten.codegeneratorweb.utils;

import lombok.extern.slf4j.Slf4j;
import org.gentten.codegeneratorweb.domain.entity.CodeModule;
import org.gentten.codegeneratorweb.domain.entity.Field;
import org.gentten.codegeneratorweb.domain.entity.Model;
import org.gentten.framework.common.exception.SysException;
import org.gentten.framework.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public void generatorCode(List<CodeModule> codeModules, Model model, String userId, String id) {
        //创建Context对象(存放Model)
        Context context = new Context();
        //1、需要一个领域对象
        context.setVariable("model", model);
        //2、需要的导的包
        List<String> importFromFields = getImportFromFields(model.getFields());
        context.setVariable("imports", importFromFields);

        codeModules.forEach(codeModule -> {
            codeModule.setModel(model);
            generatorCode(codeModule, context, userId, id);
        });
    }

    public void generatorCode(CodeModule codeModule, Context context, String userId, String id) {
        //3、代码生成的后一段包名
        context.setVariable("module", codeModule);
        //4、获取生成代码保存目录 按用户名和模板或者模板id创建的临时目录
        String packagePath = getFileSavePath(codeModule, userId, id);

        File file = new File(packagePath);
        //目录不存在则创建
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new SysException(String.format("创建目录失败:%s", file.getPath()));
            }
        }
        String saveName = getSaveName(codeModule);
        //生成代码
        try (FileWriter fileWriter = new FileWriter(packagePath + File.separator + saveName)) {
            templateEngine.process(codeModule.getCodeModuleName(), context, fileWriter);
        } catch (IOException e) {
            log.error(String.format("代码生成失败：module %s", codeModule.toString()), e);
        }
    }

    private String getSaveName(CodeModule codeModule) {
        if (codeModule.getIsContainsName()) {
            return codeModule.getModel().getClassName() + codeModule.getCodeModuleName() + ".java";
        } else {
            return codeModule.getModel().getClassName() + ".java";
        }
    }

    public static List<String> getImportFromFields(List<Field> fieldList) {

        return fieldList
                .stream()
                .filter(field -> field.getDataType().getNeedImport())
                .map(field -> field.getDataType().getPackageName())
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
    public String getModuleSavePath(String id, String userId) {
        if (StringUtils.isBlank(savePath)) {
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
     * @param codeModule ，codeModule
     * @param userId     用户id
     * @param id         模块或者模板组id 用于临时文件夹的区分
     * @return 路径
     */
    public String getFileSavePath(CodeModule codeModule, String userId, String id) {

        Model model = codeModule.getModel();
        StringBuffer res = new StringBuffer(getModuleSavePath(id, userId));
        //model 设置属于哪个module
        appendPackage(res, model.getPackageName());
        //代码划分模块
        appendModule(res, model.getModuleName());
        //module 设置生成之后多增加的包
        appendPackage(res, codeModule.getPackageName());
        return res.toString();
    }

    /**
     * 拼接上包名
     *
     * @param buffer      buffer
     * @param packageName 包名
     */
    public static void appendPackage(StringBuffer buffer, String packageName) {
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
    public static void appendModule(StringBuffer buffer, String moduleName) {
        if (StringUtils.isNotBlank(moduleName)) {
            buffer.append(File.separator).append(moduleName);
        }
    }

    /**
     * 拼接上entity保存路径根目录
     *
     * @param path path
     */
    public static String appendEntity(String path) {
        if (StringUtils.isNotBlank(path)) {
            path = path + File.separator + "domain" + File.separator + "entity";
        }
        return path;
    }

    /**
     * 拼接上表单保存路径根目录
     *
     * @param path path
     * @param type edit/query/search
     */
    public static String appendForm(String path, String type) {
        if (StringUtils.isNotBlank(path)) {
            path = path + File.separator + "domain" + File.separator + "form" + File.separator + type;
        }
        return path;
    }

    /**
     * 拼接上controller保存路径根目录
     *
     * @param path path
     */
    public static String appendController(String path) {
        if (StringUtils.isNotBlank(path)) {
            path = path + File.separator + "controller";
        }
        return path;
    }

    /**
     * 拼接上mapper保存路径根目录
     *
     * @param path path
     */
    public static String appendMapper(String path) {
        if (StringUtils.isNotBlank(path)) {
            path = path + File.separator + "controller";
        }
        return path;
    }

    /**
     * 拼接上Service保存路径根目录
     *
     * @param path path
     */
    public static String appendService(String path) {
        if (StringUtils.isNotBlank(path)) {
            path = path + File.separator + "service";
        }
        return path;
    }

    /**
     * 拼接上serviceImpl保存路径根目录
     *
     * @param path path
     */
    public static String appendServiceImpl(String path) {
        if (StringUtils.isNotBlank(path)) {
            path = path + File.separator + "service" + File.separator + "impl";
        }
        return path;
    }
}

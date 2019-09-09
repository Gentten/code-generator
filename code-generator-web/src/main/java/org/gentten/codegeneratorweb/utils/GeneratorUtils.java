package org.gentten.codegeneratorweb.utils;

import lombok.extern.slf4j.Slf4j;
import org.gentten.codegeneratorweb.domain.entity.Model;
import org.gentten.framework.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;


/**
 * 代码生成工具类
 *
 * @author : duanzhiqiang
 * @date : 2019-09-09 17:09
 */
@Slf4j
@Component
public class GeneratorUtils {
    /**
     * 生成代码的保存位置
     */
    @Value("${generator.savePath:}")
    private String savePath;

    /**
     * 获取某个模块生成代码的保存路径 压缩、下载使用
     *
     * @param model    ，model
     * @param userId   用户id
     * @param moduleId model id
     * @return 路径
     */
    public String getModuleSavePath(Model model, String userId, String moduleId) {
        if (StringUtils.isBlank(savePath)) {
            savePath = "." + File.separator + "code";
        }

        return savePath
                + File.separator
                + userId
                + File.separator
                + moduleId;
    }

    /**
     * 获取某个模块生成代码的保存路径（带包名和划分模块名），去保存使用
     *
     * @param model    ，model
     * @param userId   用户id
     * @param moduleId model id
     * @return 路径
     */
    public String getFileBaseSavePath(Model model, String userId, String moduleId) {

        StringBuffer res = new StringBuffer(getModuleSavePath(model, userId, moduleId));
        appendPackage(res, model.getPackageName());
        appendModule(res, model.getModuleName());
        return res.toString();
        // return Thread.currentThread().getContextClassLoader().getResource("").getPath();
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
}

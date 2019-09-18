package org.gentten.codegeneratorweb.utils;

import lombok.extern.slf4j.Slf4j;
import org.gentten.framework.common.exception.SysException;
import org.gentten.framework.common.util.EmptyUtils;
import org.springframework.lang.NonNull;

import java.io.File;


/**
 * 文件工具类
 *
 * @author : duanzhiqiang
 * @date : 2019-09-17 11:41
 */
@Slf4j
public class FileUtils {
    /**
     * 递归创建目录,创建失败则抛出异常
     *
     * @param file 需要创建的file
     */
    public static void mkdirs(@NonNull File file) {
        if (!file.mkdirs()) {
            if (!file.exists()) {
                throw new SysException(String.format("创建目录失败:%s，请联系管理员", file.getPath()));
            }
        }
    }

    /**
     * 删除目录或者文件包含目录
     *
     * @param file 需要删除文件
     */
    public static boolean delFile(@NonNull File file) {

        if (file.isFile()) {
            if (log.isDebugEnabled()) {
                log.debug("del {}", file.getAbsolutePath());
            }
            return file.delete();
        } else {
            File[] childFiles = file.listFiles();
            if (EmptyUtils.isNotEmpty(childFiles)) {
                for (File childFile : childFiles) {
                    delFile(childFile);
                }
            }
            //删除子目录文件和目录之后为空表示可以删除
            if (EmptyUtils.isEmpty(file.list())) {
                if (log.isDebugEnabled()) {
                    log.debug("del {}", file.getAbsolutePath());
                }
                return file.delete();
            }
        }
        return false;
    }

    /**
     * 清空目录下的所有目录和文件
     *
     * @param file 需要清空目录
     */
    public static boolean clearDir(@NonNull File file) {
        if (log.isDebugEnabled()) {
            log.debug("clear dir {}", file.getAbsolutePath());
        }
        if (file.isFile()) {
            if (log.isDebugEnabled()) {
                log.debug("file is file not dir");
            }
            return true;
        } else {
            File[] childFiles = file.listFiles();
            if (EmptyUtils.isNotEmpty(childFiles)) {
                for (File childFile : childFiles) {
                    delFile(childFile);
                }
            }
            //删除子目录文件和目录之后为空表示清空成功
            return EmptyUtils.isEmpty(file.list());
        }
    }

}

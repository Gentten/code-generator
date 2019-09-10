package org.gentten.codegeneratorweb.utils;

import lombok.extern.slf4j.Slf4j;
import org.gentten.framework.common.util.EmptyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩工具
 *
 * @author : duanzhiqiang
 * @date : 2019-09-09 22:49
 */
@Slf4j
public class ZipUtils {


    private static final int BUFFER_SIZE = 2 * 1024;


    /**
     * 压缩成ZIP
     *
     * @param srcDir 压缩文件夹路径
     * @param out    压缩文件输出流
     */
    public static void toZip(String srcDir, OutputStream out) {
        long start = System.currentTimeMillis();
        try (ZipOutputStream zos = new ZipOutputStream(out)) {
            File sourceFile = new File(srcDir);
            compress(sourceFile, zos, sourceFile.getName());
            long end = System.currentTimeMillis();
            log.info(String.format("压缩完成，耗时：%sms", end - start));
        } catch (Exception e) {
            log.info("压缩失败 ", e);
        }
    }


    /**
     * 递归压缩方法
     *
     * @param sourceFile 源文件
     * @param zos        zip输出流
     * @param name       压缩后的名称
     * @throws Exception y
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name) throws Exception {
        //文件
        if (sourceFile.isFile()) {
            byte[] buf = new byte[BUFFER_SIZE];
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            try (FileInputStream in = new FileInputStream(sourceFile)) {
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
            }
            // Complete the entry
            zos.closeEntry();
            return;
        }
        //目录
        File[] listFiles = sourceFile.listFiles();
        //空目录
        if (EmptyUtils.isEmpty(listFiles)) {
            // 需要保留原来的文件结构时,需要对空文件夹进行处理
            // 空文件夹的处理
            zos.putNextEntry(new ZipEntry(name + "/"));
            // 没有文件，不需要文件的copy
            zos.closeEntry();

        } else {//非空目录
            for (File file : listFiles) {
                // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                compress(file, zos, name + "/" + file.getName());

            }
        }
    }

}

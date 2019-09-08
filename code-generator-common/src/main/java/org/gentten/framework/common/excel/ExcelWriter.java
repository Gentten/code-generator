package org.gentten.framework.common.excel;

import com.github.pagehelper.ISelect;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Excel写入器
 *
 * @author : duanzhiqiang
 * @date : 2019-09-04 16:47
 */
public interface ExcelWriter {
    /**
     * 将查询结果的数据全部导出
     *
     * @param outputStream 输出流
     * @param select       分页查询接口 {@link com.github.pagehelper.ISelect}
     */
    void write(OutputStream outputStream, ISelect select) throws InterruptedException, IOException;

}

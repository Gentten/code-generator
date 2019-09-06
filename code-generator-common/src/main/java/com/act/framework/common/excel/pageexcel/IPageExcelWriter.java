package com.act.framework.common.excel.pageexcel;

import com.act.framework.common.excel.ExcelWriter;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;

/**
 * 抽象分页写
 *
 * @author : duanzhiqiang
 * @date : 2019-09-04 17:03
 */
public interface IPageExcelWriter extends ExcelWriter {
    int DEFAULT_BUFFER_SIZE = 3;
    /**
     * 默认的查询大小（每次默认一次性处理的数据）
     */
    int DEFAULT_SELECT_SIZE = 3000;

    /**
     * excel  是否在一个sheet里是否分sheet
     *
     * @return 返回true时，在一个页里，其他不分
     */
    boolean isManySheet();

    /**
     * 获取分页大小 当getListMode 为false时 也表示sheet页数据量大小
     *
     * @return 分页大小
     */
    default int getSheetSize() {
        return DEFAULT_SELECT_SIZE;
    }

    /**
     * 获取缓存size
     *
     * @return size
     */
    default int getBufferSize() {
        return DEFAULT_BUFFER_SIZE;
    }


    /***
     *
     *  下一个WriteSheet
     * @param pageNum 当前页号
     * @param lastWriteSheet 上个写的页
     * @return 下一个写的页
     */
    default WriteSheet getNextWriteSheet(int pageNum, WriteSheet lastWriteSheet) {
        return isManySheet() ? EasyExcel.writerSheet(pageNum).build() : lastWriteSheet;
    }
}

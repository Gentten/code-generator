package org.gentten.framework.common.excel.pageexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基于分页插件(page)和easyexcel进行大数据量excel导出
 * <p>
 * 生成消费者模式，一个生产者（一个原因是放入缓存的要保存有序，并且缓存大小为读取数据的三倍，并不需要多个线程）一个消费者，生产者先从数据库中获取数据，消费者OutputStream
 * <p>
 * //@Builder 生成的建造的模式，如果没有
 *
 * @author : duanzhiqiang
 * @date : 2019-09-04 15:47
 */
@Data
@Builder
@Slf4j
public class PageExcelWriter implements IPageExcelWriter {

    /**
     * 默认defaultPageSize条记录查询并写一次，避免将全部数据读到内存导致oom
     */
    private Integer selectSize;

    /**
     * 缓存倍数
     */
    private Integer bufferSize;
    /**
     * 是否按sheet 分页默认不分页  为true时分页 ，是否多sheet
     */
    private boolean isManySheet;
    /**
     * 导出的列头信息
     */
    private Class head;


    @Override
    public void write(OutputStream outputStream, ISelect select) throws InterruptedException, IOException {
        // 当前读页号
        AtomicInteger pageNumRead = new AtomicInteger(1);
        // 当前写页号
        AtomicInteger pageNumWrite = new AtomicInteger(1);
        // 读取缓存
        BlockingQueue<PageInfo> buffer = new ArrayBlockingQueue<>(getBufferSize());
        //获取写入，一个excel文件一个writer
        com.alibaba.excel.ExcelWriter excelWriter = EasyExcel.write(outputStream, head).build();
        // 创建一页用于写 一页一个WriteSheet
        WriteSheet writeSheet = EasyExcel.writerSheet(pageNumWrite.get()).build();

        //每次查询的页信息
        PageInfo onePage;
        //生产 一个生产者保证页进入队列的顺序，分页，此时
        PageInfoExecutor.submit(() -> {
            PageInfo pageInfo;
            try {
                //一直获取直到没有下一页
                do {
                    log.info("页号{}：开始读,当前缓存大小：{}", pageNumRead.get() - 1, buffer.size());
                    pageInfo = PageHelper.startPage(pageNumRead.getAndIncrement(), getSheetSize()).doSelectPageInfo(select);
                    buffer.put(pageInfo);
                    log.info("页号{}：读完成,当前缓存大小:{}", pageNumRead.get() - 1, buffer.size());

                } while (pageInfo.isHasNextPage());

            } catch (InterruptedException e) {
                log.error("put缓存阻塞被打断");
            }
        });
        //消费

        // 一直循环直到最后一页也写完
        do {
            log.info("页号{}：开始写,当前缓存大小：{}", pageNumWrite.get(), buffer.size());
            //从队列中拿
            onePage = buffer.take();
            excelWriter.write(onePage.getList(), writeSheet);
            log.info("页号{}：写完成,当前缓存大小：{}", pageNumWrite.get(), buffer.size());
            outputStream.flush();
            //下一页需要写的
            writeSheet = getNextWriteSheet(pageNumWrite.getAndIncrement(), writeSheet);
        }
        while (onePage.isHasNextPage());
        log.info("总条数" + onePage.getTotal());
        // 写完
        excelWriter.finish();
    }

    @Override
    public int getSheetSize() {
        return selectSize == null ? DEFAULT_SELECT_SIZE : selectSize;
    }

    @Override
    public int getBufferSize() {
        return bufferSize == null ? DEFAULT_BUFFER_SIZE : bufferSize;
    }
}

package com.act.framework.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;

/**
 * page信息转化 pagehelper 和page 之间的转化
 *
 * @author : duanzhiqiang
 * @date : 2019-07-04 19:05
 */
public class PageUtils {
    /**
     * PageHelper PageInfo 转 IPage
     */
    public static <T> IPage<T> pageTransfer(PageInfo<T> pageInfo) {
        Page<T> page = new Page<>(pageInfo.getPageNum(), pageInfo.getPageSize());
        page.setTotal(pageInfo.getTotal());
        page.setPages(pageInfo.getPages());
        page.setRecords(pageInfo.getList());
        return page;
    }

    /**
     * PageHelper IPage 转 PageInfo
     */
    public static <T> PageInfo<T> pageTransfer(IPage<T> iPage) {
        com.github.pagehelper.Page page = new com.github.pagehelper.Page((int) iPage.getCurrent(), (int) iPage.getSize());
        page.setTotal(iPage.getTotal());
        page.addAll(iPage.getRecords());

        PageInfo<T> pageInfo = new PageInfo<>(page);
        page.setTotal(pageInfo.getTotal());
        page.setPages(pageInfo.getPages());
        return pageInfo;
    }
}
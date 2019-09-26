package org.gentten.codegeneratorweb.utils;

import org.apache.ibatis.jdbc.SQL;

/**
 * 解析工具类
 *
 * @author : duanzhiqiang
 * @date : 2019-09-08 14:23
 */
public class ParseUtils {

    public static void main(String[] args) {

        new SQL() {
            {
                SELECT();
            }
        };
    }
}

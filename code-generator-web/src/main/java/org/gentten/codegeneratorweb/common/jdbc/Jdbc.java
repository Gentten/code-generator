package org.gentten.codegeneratorweb.common.jdbc;

import org.gentten.codegeneratorweb.domain.metadata.Column;
import org.gentten.codegeneratorweb.domain.metadata.Table;

import java.sql.Connection;
import java.util.List;

/**
 * jdbc
 *
 * @author : duanzhiqiang
 * @date : 2019-09-08 18:13
 */
public interface Jdbc {
    /**
     * 获取密码
     *
     * @return 密码
     */
    String getPassword();

    /**
     * 获取驱动
     *
     * @return 驱动
     */
    String getDriver();

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    String getUsername();

    /**
     * 返回测试的 SQL
     *
     * @return sql
     */
    default String getTestSql() {
        return "select 1";
    }

    /**
     * 返回url的样例
     *
     * @return string
     */
    String urlExample();

    /**
     * 测试连接
     *
     * @param conn 连接
     * @throws Exception 异常
     */
    default void validateConnection(Connection conn) throws Exception {
    }

    /**
     * 获取数据库
     *
     * @return 数据库
     */
    public List<String> getDatabases();

    /**
     * 获取数据库的表
     *
     * @param dbname 数据库名
     * @return 表集合
     */
    List<Table> getTables(String dbname);

    /**
     * 获取数据库的表的列
     *
     * @param dbname    数据库名
     * @param tableName 表名
     * @return 表列集合
     */
    List<Column> getColumns(String dbname, String tableName);
}

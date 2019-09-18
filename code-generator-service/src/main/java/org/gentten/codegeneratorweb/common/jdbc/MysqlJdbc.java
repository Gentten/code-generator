package org.gentten.codegeneratorweb.common.jdbc;

import lombok.AllArgsConstructor;
import org.gentten.framework.common.exception.SysException;
import org.gentten.framework.common.util.StringUtils;

import java.sql.Connection;

/**
 * mysql
 * 必须指定某个数据库，否则获取不到表信息
 *
 * @author : duanzhiqiang
 * @date : 2019-09-09 09:58
 */
@AllArgsConstructor
public class MysqlJdbc extends AbstractJdbc {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    public MysqlJdbc(String jdbcUrl, String username, String password) {
        super(JDBC_DRIVER, jdbcUrl, username, password);
    }

    @Override
    public String urlExample() {
        return "jdbc:mysql://localhost:3306";
    }

    @Override
    public void validateConnection(Connection conn) throws Exception {
        //当前数据库为空串
        if (StringUtils.isBlank(conn.getCatalog())) {
            throw new SysException("Mysql连接地址不正确");
        }
    }
}



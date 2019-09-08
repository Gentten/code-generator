package org.gentten.codegeneratorweb.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gentten.framework.common.exception.SysException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author : duanzhiqiang
 * @date : 2019-09-08 18:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Slf4j
public abstract class AbstractJdbc implements Jdbc {
    /**
     * 驱动
     */
    private String driver;
    /**
     * 地址
     */
    private String jdbcUrl;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    public Connection getConnection() throws Exception {
        Connection conn;
        if (log.isDebugEnabled()) {
            log.debug("接入数据源[URL:{}]", this.jdbcUrl);
        }
        try {
            Class.forName(getDriver());
            Properties props = new Properties();
            if (this.username != null) {
                props.setProperty("user", this.username);
            }
            if (this.password != null) {
                props.setProperty("password", this.password);
            }
            conn = DriverManager.getConnection(this.jdbcUrl, props);
            validateConnection(conn);
        } catch (ClassNotFoundException e) {
            throw new SysException(String.format("数据库连接失败，找不到驱动[%s]", this.driver), e);
        } catch (Exception e) {
            throw new SysException(String.format("数据库连接失败，具体错误：%s\n1、请检查连接地址参考:%s;\n2、请检查网络是否畅通;", e.getLocalizedMessage(), urlExample()), e);
        }
        return conn;
    }

}

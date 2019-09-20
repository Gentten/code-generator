package org.gentten.codegeneratorweb.common.jdbc;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gentten.codegeneratorweb.common.converter.DataConverter;
import org.gentten.codegeneratorweb.domain.metadata.Column;
import org.gentten.codegeneratorweb.domain.metadata.Table;
import org.gentten.framework.common.exception.SysException;
import org.gentten.framework.common.util.StringUtils;


import java.sql.*;
import java.util.List;
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
            conn = DriverManager.getConnection(this.jdbcUrl + "?useSSL=false", props);
            validateConnection(conn);
        } catch (ClassNotFoundException e) {
            throw new SysException(String.format("数据库连接失败，找不到驱动[%s]", this.driver), e);
        } catch (Exception e) {
            throw new SysException(String.format("数据库连接失败，具体错误：%s\n1、请检查连接地址参考:%s;\n2、请检查网络是否畅通;", e.getLocalizedMessage(), urlExample()), e);
        }
        return conn;
    }

    @Override
    public List<String> getDatabases() {
        List<String> dbs = Lists.newArrayList();
        try (Connection conn = getConnection()) {
            dbs.add(conn.getCatalog());
        } catch (Exception e) {
            throw new SysException(String.format("获取数据库失败，具体错误：%s", e.getLocalizedMessage()), e);
        }
        return dbs;
    }

    @Override
    public List<Table> getTables(String dbname) {
        List<Table> tables = Lists.newArrayList();
        try (Connection conn = getConnection()) {
            //获取数据库元数据
            DatabaseMetaData dbMetaData = conn.getMetaData();
            String catalog = conn.getCatalog();

            String schema = conn.getSchema();

            ResultSet resultSet = dbMetaData.getTables(catalog, schema, null, new String[]{"TABLE", "VIEW"});

            while (resultSet.next()) {
                tables.add(Table.builder()
                        .name(resultSet.getString("TABLE_NAME"))
                         //去掉换行和回车
                        .comment(resultSet.getString("REMARKS").replaceAll("[\\n|\\r]", " "))
                        .database(dbname)
                        .build());
            }
        } catch (Exception e) {
            throw new SysException(String.format("获取表失败，具体错误：%s", e.getLocalizedMessage()), e);
        }
        return tables;
    }

    @Override
    public List<Column> getColumns(String dbname, String tableName) {
        List<Column> columns = Lists.newArrayList();

        try (Connection conn = getConnection()) {

            DatabaseMetaData dbMetaData = conn.getMetaData();
            String catalog = StringUtils.isEmpty(dbname) ? conn.getCatalog() : dbname;
            String schema = conn.getSchema();
            ResultSet resultSet = dbMetaData.getColumns(catalog, schema, tableName, null);
            int index = 0;
            while (resultSet.next()) {
                Column column = Column.builder()
                        .index(index)
                        .dataType(DataConverter.toFrontType(resultSet.getInt("DATA_TYPE")))
                        //去掉换行和回车符
                        .comment(resultSet.getString("REMARKS").replaceAll("[\\n|\\r]", " "))
                        .name(resultSet.getString("COLUMN_NAME"))
                        .isNullable(resultSet.getString("IS_NULLABLE"))
                        .tableName(tableName)
                        .database(dbname)
                        .build();
                columns.add(column);
                index++;
            }
        } catch (Exception e) {
            throw new SysException(String.format("获取表结构失败，具体错误：%s", e.getLocalizedMessage()), e);
        }
        return columns;
    }

}

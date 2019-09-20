package org.gentten.codegeneratorweb.common.converter;

import org.gentten.codegeneratorweb.domain.entity.Field;
import org.gentten.codegeneratorweb.domain.enums.DataType;
import org.gentten.codegeneratorweb.domain.enums.JavaType;
import org.gentten.codegeneratorweb.domain.metadata.Column;
import org.gentten.framework.common.util.StringUtils;

import javax.xml.crypto.Data;
import java.sql.Types;

/**
 * 类型转换器
 *
 * @author : duanzhiqiang
 * @date : 2019-09-09 10:10
 */
public class DataConverter {
    /**
     * 数据库类型转成系统支持的类型
     *
     * @param dbType 数据库
     * @return DataType
     */
    public static DataType toFrontType(Integer dbType) {
        switch (dbType) {

            case Types.BIGINT:
                return DataType.LONG;
            case Types.FLOAT:
            case Types.REAL:
            case Types.DOUBLE:
            case Types.NUMERIC:
                return DataType.NUMERIC;
            case Types.INTEGER:
            case Types.BIT:
            case Types.TINYINT:
            case Types.SMALLINT:
                return DataType.INTEGER;
            case Types.DATE:
                return DataType.DATE;
            case Types.TIME:
                return DataType.TIME;
            case Types.TIMESTAMP:
                return DataType.DATETIME;
            case Types.BOOLEAN:
                return DataType.BOOLEAN;
            case Types.DECIMAL:
                return DataType.DECIMAL;
            default:
                return DataType.TEXT;
        }

    }

    public static JavaType toJavaType(DataType dataType) {
        switch (dataType) {
            case CHAR:
                return JavaType.CHARACTER;
            case BOOLEAN:
                return JavaType.BOOLEAN;
            case NUMERIC:
            case DECIMAL:
                return JavaType.BIG_DECIMAL;
            case TIME:
                return JavaType.TIME;
            case DATE:
                return JavaType.DATE;
            case LONG:
            case TIMESTAMP:
                return JavaType.LONG;
            case INTEGER:
                return JavaType.INTEGER;
            case DATETIME:
                return JavaType.DATE_TIME;
            default:
                return JavaType.STRING;
        }
    }

    /**
     * 列转字段
     *
     * @param column 列
     * @return 字段
     */
    public static Field getFiledByColumn(Column column) {
        return Field.builder()
                .comment(column.getComment())
                .dataType(DataConverter.toJavaType(column.getDataType()))
                .name(StringUtils.camelCaseName(column.getName()))
                .build();
    }

}

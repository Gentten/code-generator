package org.gentten.codegeneratorweb.domain.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 * 支持数据类型,用于展示和选择
 *
 * @author : duanzhiqiang
 * @date : 2019-09-06 15:32
 */
public enum DataType implements IEnum {
    /**
     * 数据的类型
     */
    LONG("bigint，长整型对应Long"),
    TEXT("text，对应String"),
    CHAR("char,对应Character"),
    DECIMAL("decimal,对应BigDecimal"),
    BOOLEAN("boolean,对应Boolean"),
    DATE("日期,对应Date 只有年月日"),
    TIME("时间,Date,只有时间"),
    DATETIME("日期时间,对应Date，年月日和时间"),
    TIMESTAMP("时间戳,对应Long"),
    INTEGER("整型类型,对应Integer"),
    //todo:暂时将浮点数字类型统一为对应BigDecimal
    NUMERIC("浮点数字类型,对应BigDecimal");

    /**
     * 说明，注释
     */
    private DataType(String comment) {

    }

    @Override
    public Serializable getValue() {
        return this.name();
    }
}

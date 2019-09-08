package org.gentten.codegeneratorweb.domain.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 * 数据类型
 *
 * @author : duanzhiqiang
 * @date : 2019-09-06 15:32
 */
public enum DataType implements IEnum {
    /**
     * 数据的类型
     */
    TEXT("text，对应String"),
    CHAR("char,对应Character"),
    DECIMAL("decimal,对应BigDecimal"),
    BOOLEAN("boolean,对应Boolean"),
    DATE("日期,对应Date"),
    TIME("时间,对应Time"),
    DATETIME("日期时间,对应Date"),
    TIMESTAMP("时间戳,对应Long"),
    NUMERIC("数字类型,对应BigDecimal");


    private String desc;

    private DataType(String desc) {
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return this.name();
    }
}

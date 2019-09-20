package org.gentten.codegeneratorweb.domain.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;


/**
 * 支持的java类型以及需要导的包，即将dataType转化为java对象,日期的格式分成三种
 *
 * @author : duanzhiqiang
 * @date : 2019-09-09 11:33
 */
public enum JavaType implements IEnum {
    /**
     * java类型
     */
    DATE("Date", "java.util.Date;com.fasterxml.jackson.annotation.JsonFormat;import org.springframework.format.annotation.DateTimeFormat", "yyyy-MM-dd"),
    DATE_TIME("Date", "java.util.Date;import com.fasterxml.jackson.annotation.JsonFormat;import org.springframework.format.annotation.DateTimeFormat", "yyyy-MM-dd HH:mm:ss"),
    TIME("Date", "java.util.Date;import com.fasterxml.jackson.annotation.JsonFormat;import org.springframework.format.annotation.DateTimeFormat", "HH:mm:ss"),
    STRING("String"),
    BIG_DECIMAL("BigDecimal", "java.math.BigDecimal"),
    BOOLEAN("Boolean"),
    CHARACTER("Character"),
    INTEGER("Integer"),
    LONG("Long");

    private String name;
    private String importPackages;
    private Boolean needImport;
    private String pattern;

    JavaType(String name, String importPackages, String pattern) {
        this.name = name;
        this.importPackages = importPackages;
        //指定了包名意味着生成实体需要导包
        this.needImport = true;
        this.pattern = pattern;
    }

    JavaType(String name, String importPackages) {
        this.name = name;
        this.importPackages = importPackages;
        //指定了包名意味着生成实体需要导包
        this.needImport = true;
    }

    JavaType(String name) {
        //不指定表名则不需要导包
        this.name = name;
        this.needImport = false;
    }

    public String getName() {
        return name;
    }

    public String getImportPackages() {
        return importPackages;
    }

    public Boolean getNeedImport() {
        return needImport;
    }

    public String getPattern() {
        return pattern;
    }

    @Override
    public Serializable getValue() {
        return this.name;
    }
}

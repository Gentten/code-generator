package org.gentten.codegeneratorweb.domain.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;


/**
 * 支持的java类型以及需要导的包，即将dataType转化为java对象
 *
 * @author : duanzhiqiang
 * @date : 2019-09-09 11:33
 */
public enum JavaType implements IEnum {
    /**
     * java类型
     */
    DATE("Date", "java.util.Date"),
    STRING("String"),
    BIG_DECIMAL("BigDecimal", "java.math.BigDecimal"),
    BOOLEAN("Boolean"),
    CHARACTER("Character"),
    INTEGER("Integer"),
    LONG("Long");

    private String name;
    private String packageName;
    private Boolean needImport;

    JavaType(String name, String packageName) {
        this.name = name;
        this.packageName = packageName;
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

    public String getPackageName() {
        return packageName;
    }

    public Boolean getNeedImport() {
        return needImport;
    }

    @Override
    public Serializable getValue() {
        return this.name;
    }
}

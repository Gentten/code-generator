package org.gentten.codegeneratorweb.aspect;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatis-plus 字段填充功能  实现公共字段自动填写
 * 前提需要用{@link  com.baomidou.mybatisplus.annotation.TableField}去标记填充的字段如： @TableField(.. fill = FieldFill.INSERT)
 * 1、填充更新时间和修改时间
 * 2、填充操作人信息 等权限认证实现
 *
 * @author : duanzhiqiang
 * @date : 2019-07-31 10:47
 */
@Component
public class FiledFillObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}

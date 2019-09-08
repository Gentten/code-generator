package org.gentten.framework.common.util;

import org.gentten.framework.common.annotation.Transform;
import org.gentten.framework.common.domain.base.BaseEntity;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * 表单转化工类  指定一个对象转为entity
 *
 * @author : duanzhiqiang
 * @date : 2019-07-30 21:42
 */
@Slf4j
public class TransformUtils {
    /**
     * 通过getter和setter方法 将一个bean 映射到另一bean上,找不到指定的则警告，不考虑source父类的字段
     * 没有指定转化到那个字段上，默认按同名字去映射
     *
     * @param source    源
     * @param destClass 目标类类型
     * @return 实体
     */
    public static <T extends BaseEntity> T transform(Object source, Class<T> destClass) {

        T destObject = null;
        try {
            destObject = destClass.getDeclaredConstructor().newInstance();
            // getDeclaredFields 只能获取当前的声明的字段，不包含父类
            Field[] declaredFields = source.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                String transformFieldName = getTransformField(field);
                String name = field.getName();
                //转化值
                ReflectionUtils.setFieldValue(destObject, transformFieldName, ReflectionUtils.getFieldValue(source, name));
            }
        } catch (Exception e) {
            log.error("转化失败：" + source.getClass() + "->" + destClass + "reason:" + e.getMessage());
        }
        return destObject;
    }

    /**
     * 当字段没有加{@link Transform } 注解或者注解为默认值，那么默认转化的字段名和当前名字一样
     * <p>
     * 不是默认值时转化到指定的字段
     *
     * @param field 字段
     * @return 需要转化到字段的名字
     */
    public static String getTransformField(Field field) {
        Transform annotation = field.getAnnotation(Transform.class);
        if (null == annotation || EmptyUtils.isEmpty(annotation.value())) {
            return field.getName();
        }
        return annotation.value();
    }
}

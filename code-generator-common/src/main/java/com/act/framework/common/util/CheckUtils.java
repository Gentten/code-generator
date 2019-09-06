package com.act.framework.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 * @author : duanzhiqiang
 * @date : 2019-07-04 19:05
 */
@Slf4j
public class CheckUtils {


    private CheckUtils() {
        super();
    }

    /**
     * 检测对象是否为null
     *
     * @param o       检测对象
     * @param message 错误提示
     */
    public static void checkForNull(Object o, String message) {
        if (o == null) {
            log.error(message);
            throw new NullPointerException(message);
        }
    }

    /**
     * 检测对象是否为empty
     *
     * @param o       检测对象
     * @param message 错误提示
     */
    public static void checkForEmpty(Object[] o, String message) {
        if (o.length == 0) {
            log.error(message);
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 检测对象是否为empty
     *
     * @param o       检测对象
     * @param message 错误提示
     */
    public static void checkForEmpty(Iterable<?> o, String message) {
        if (!o.iterator().hasNext()) {
            log.error(message);
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 检测对象是否为null或者empty
     *
     * @param collection 检测对象
     * @param message    错误提示
     */
    public static void notEmpty(Collection<?> collection, String message) {
        if ((collection == null) || (collection.isEmpty())) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 检测集合对象是否为null或者empty,不为空不为null则报错
     *
     * @param collection 检测对象
     * @param message    错误提示
     */
    public static void isEmpty(Collection<?> collection, String message) {
        if ((collection != null) && (!collection.isEmpty())) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 检测实例对象是否正确
     *
     * @param clazz 类名
     * @param obj   对象
     */
    public static void isInstanceOf(Class<?> clazz, Object obj) {
        isInstanceOf(clazz, obj, "");
    }

    /**
     * 检测数组对象是否为null或者empty
     *
     * @param array   检测对象
     * @param message 错误提示
     */
    public static void notEmpty(Object[] array, String message) {
        if ((array == null) || (array.length == 0)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 检测字符串中是否包含某一字符串
     *
     * @param textToSearch 需检测的字符串
     * @param substring    包含的字符串
     * @param message      错误提示
     */
    public static void notContain(String textToSearch, String substring, String message) {
        if (textToSearch.indexOf(substring) != -1) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 检测表达式是否正确
     *
     * @param expression 表达式
     * @param message    错误提示
     */
    public static void isTrue(boolean expression, String message) {
        if (!(expression)) throw new IllegalArgumentException(message);
    }

    /**
     * 检测实例对象是否正确
     *
     * @param clazz   类名
     * @param obj     检测对象
     * @param message 错误提示
     */
    public static void isInstanceOf(Class<?> clazz, Object obj, String message) {
        checkForNull(clazz, "The clazz to perform the instanceof assertion cannot be null");
        isTrue(clazz.isInstance(obj),
                message + "Object of class '" + ((obj != null) ? obj.getClass().getName() : "[null]")
                        + "' must be an instance of '" + clazz.getName() + "'");
    }

    /**
     * 检测select下拉框是否选中
     *
     * @param value 选择值
     * @return true 选中 false未选中
     */
    public static boolean isSelect(Object value) {
        if (null == value) {
            return false;
        }

        if (value instanceof Integer) {
            int val = (Integer) value;
            return -1 != val;
        } else if (value instanceof String) {
            String val = (String) value;
            return !"-1".equals(val) && !"".equals(val);
        } else {
            throw new IllegalArgumentException("value 参数非法，必须为字符或整形");
        }
    }

    public static boolean checkPassword(String password) {
        String regx = "(?!.*[\\u4E00-\\u9FA5\\s])(?!^[a-zA-Z]+$)(?!^[\\d]+$)(?!^[^a-zA-Z\\d]+$)^.{8,16}$";
        return StringUtils.isBlank(password) ? false : Pattern.compile(regx).matcher(password).matches();
    }
}

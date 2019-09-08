package org.gentten.framework.common.util;

import java.util.regex.Pattern;

/**
 * 正则表达式
 *
 * @author : duanzhiqiang
 * @date : 2019-07-04 19:05
 */

public class RegexUtils {
    //环境正则
    public static final String DEV_ENV = ".*dev$";
    public static final String TEST_ENV = ".*test$";
    public static final String PROD_ENV = ".*prod$";
    public static final String SORT_PATTERN = ".*_.*$";

    //参数校验
    public static final String USERNAME_4_20 = "^[a-zA-Z][a-zA-Z0-9_-]{3,19}$";//字母开头 4-20位
    public static final String NICKNAME_2_20 = "^[\\u4e00-\\u9fa5_a-zA-Z0-9_]{2,10}$";//2-10位 可以中文字母数字下划线
    public static final String NAME_2_30 = "^.{2,30}$";//2-30位 任意字符
    public static final String PASSWORD_6_20 = "^[\\u4e00-\\u9fa5_a-zA-Z0-9_]{2,20}$";
    public static final String MOBILE = "^(1)\\d{10}$";
    public static final String BANK_CARD = "^(622848)(\\d{9}|\\d{13})$";
    public static final String ID_CARD = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)";
    public static final String EXCEL = ".+\\.(xlsx|xls)";
    public static final String IMAGE = ".+\\.(jpg|jpeg|png)";
    public static final String EMAIL = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9_.-]+$";

    public static Boolean isDevelopmentEnvironment(String activeProfileName) {
        return Pattern.matches(DEV_ENV, activeProfileName);
    }

    public static Boolean isTestEnvironment(String activeProfileName) {
        return Pattern.matches(TEST_ENV, activeProfileName);
    }

    public static Boolean isProductionEnvironment(String activeProfileName) {
        return Pattern.matches(PROD_ENV, activeProfileName);
    }

    public static Boolean checkSortPattern(String str) {
        return Pattern.matches(SORT_PATTERN, str);
    }
}

package com.wonly.lib_base.utils;

import com.wonly.lib_base.constant.RegexConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:正则相关
 */
public final class RegexUtils {

    private RegexUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 简单验证手机号
     *
     * @param input
     * @return
     */
    public static boolean isMobileSimple(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_MOBILE_SIMPLE, input);
    }

    /**
     * 精确验证手机号
     *
     * @param
     * @return
     */
    public static boolean isMobileExact(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_MOBILE_EXACT, input);
    }

    /**
     * 验证电话号码
     *
     * @param input
     * @return
     */
    public static boolean isTel(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_TEL, input);
    }

    /**
     * 验证身份证号码 15 位
     *
     * @param input
     * @return
     */
    public static boolean isIDCard15(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_ID_CARD15, input);
    }

    /**
     * 简单验证身份证号码 18 位
     *
     * @param input
     * @return
     */
    public static boolean isIDCard18(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_ID_CARD18, input);
    }

    /**
     * 验证邮箱
     *
     * @param input
     * @return
     */
    public static boolean isEmail(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_EMAIL, input);
    }

    /**
     * 验证 URL
     *
     * @param input
     * @return
     */
    public static boolean isURL(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_URL, input);
    }

    /**
     * 验证汉字
     *
     * @param input
     * @return
     */
    public static boolean isZh(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_ZH, input);
    }

    /**
     * 验证用户名
     *
     * @param input
     * @return
     */
    public static boolean isUsername(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_USERNAME, input);
    }

    /**
     * 验证 yyyy-MM-dd 格式的日期校验，已考虑平闰年
     *
     * @param
     * @return
     */
    public static boolean isDate(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_DATE, input);
    }

    /**
     * 验证 IP 地址
     *
     * @param
     * @return
     */
    public static boolean isIP(final CharSequence input) {
        return isMatch(RegexConstants.REGEX_IP, input);
    }

    /**
     * 验证银卡卡号
     *
     * @param cardNo
     * @return
     */
    public static boolean isBankCard(String cardNo) {
        return isMatch(RegexConstants.REGEX_BANK_CARD, cardNo);
    }

    /**
     * 15位和18位身份证号码的正则表达式 身份证验证
     *
     * @param idCard
     * @return
     */
    public static boolean validateIdCard(String idCard) {
        // 15位和18位身份证号码的正则表达式
        return isMatch(RegexConstants.REGEX_ID_CARD15OR18, idCard);
    }

    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否匹配正则
     *
     * @param regex
     * @param input
     * @return
     */
    public static boolean isMatch(final String regex, final CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }

}

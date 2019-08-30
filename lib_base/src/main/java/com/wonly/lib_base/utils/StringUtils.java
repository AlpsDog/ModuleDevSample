package com.wonly.lib_base.utils;

/**
 * @Author: HSL
 * @Time: 2019/8/29 16:31
 * @E-mail: xxx@163.com
 * @Description: String工具类
 */
public final class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 格式化手机号
     *
     * @param phone
     * @return 139****9624
     */
    public static String formatPhone(String phone) {
        try {
            StringBuffer buffer = new StringBuffer(phone);
            buffer.replace(3, 7, "****");
            return buffer.toString();
        } catch (Exception e) {
            return ObjectUtils.isEmpty(phone) ? "未知用户" : phone;
        }
    }

    /**
     * 格式化手机号
     *
     * @param phone
     * @return 139 9999 9624
     */
    public static String formatPhone2(String phone) {
        try {
            StringBuffer buffer = new StringBuffer(phone);
            buffer.insert(3, " ");
            buffer.insert(8, " ");
            return buffer.toString();
        } catch (Exception e) {
            return ObjectUtils.isEmpty(phone) ? "未知用户" : phone;
        }
    }

    /**
     * 字符串是否为空
     *
     * @param s The string.
     * @return {@code true}: yes<br> {@code false}: no
     */
    public static boolean isEmpty(final CharSequence s) {
        return s == null || s.length() == 0;
    }

    /**
     * 字符串是否全为空格
     *
     * @param s
     * @return
     */
    public static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断两字符串是否相等
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean equals(final CharSequence s1, final CharSequence s2) {
        if (s1 == s2) return true;
        int length;
        if (s1 != null && s2 != null && (length = s1.length()) == s2.length()) {
            if (s1 instanceof String && s2 instanceof String) {
                return s1.equals(s2);
            } else {
                for (int i = 0; i < length; i++) {
                    if (s1.charAt(i) != s2.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两字符串忽略大小写是否相等
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean equalsIgnoreCase(final String s1, final String s2) {
        return s1 == null ? s2 == null : s1.equalsIgnoreCase(s2);
    }

    /**
     * null 转为长度为 0 的字符串
     *
     * @param s
     * @return
     */
    public static String null2Length0(final String s) {
        return s == null ? "" : s;
    }

    /**
     * 返回字符串长度
     *
     * @param s
     * @return
     */
    public static int length(final CharSequence s) {
        return s == null ? 0 : s.length();
    }

    /**
     * 首字母大写
     *
     * @param s
     * @return
     */
    public static String upperFirstLetter(final String s) {
        if (s == null || s.length() == 0) return "";
        if (!Character.isLowerCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param s
     * @return
     */
    public static String lowerFirstLetter(final String s) {
        if (s == null || s.length() == 0) return "";
        if (!Character.isUpperCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * 反转字符串
     *
     * @param s
     * @return
     */
    public static String reverse(final String s) {
        if (s == null) return "";
        int len = s.length();
        if (len <= 1) return s;
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }

}

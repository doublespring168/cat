package com.doublespring.common;


import com.alibaba.fastjson.JSON;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * 功能说明：
 * Author：Darcy
 * Date：2019-11-17 09:39
 */
public class U {


    public static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    public static final String EMPTY = "";

    private static final String LOCAL = "127.0.0.1,localhost,::1";
    private static final String TMP = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 功能说明：懒式Object转JSON字符串
     * Author：Darcy
     * Date：2019-08-26 16:25
     */
    public static Supplier<String> toString(Object object) {
        return () -> {
            if (object == null) {
                return "object为null";
            }
            return JSON.toJSONString(object);
        };
    }

    /**
     * 功能说明：Object转JSON字符串
     * Author：Darcy
     * Date：2019-08-26 16:25
     */
    public static String toJSS(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 功能说明：懒式byte数据转String方法
     * Author：Darcy
     * Date：2019-08-26 16:25
     */
    public static Supplier<String> toString(byte[] bytes) {
        return () -> {
            if (bytes == null) {
                return "bytes为null";
            }
            return new String(bytes, StandardCharsets.UTF_8);
        };
    }

    /**
     * 功能说明：懒式Throwable转JSON字符串
     * Author：Darcy
     * Date：2019-08-26 16:25
     */
    public static Supplier<String> toString(Throwable throwable) {
        return () -> {
            if (throwable == null) {
                return "throwable为null";
            }
            StringWriter sw = new StringWriter();
            throwable.printStackTrace(new PrintWriter(sw, true));
            return sw.toString();
        };
    }

    /**
     * 功能说明：将可变形参拼接为aaa[参数值1],bbb[参数值2],ccc[参数值3]格式字符串
     * Author：Darcy
     * Date：2018/7/16 下午4:22
     */
    public static Supplier<String> format(Object... msgs) {
        return () -> {

            if (msgs == null || msgs.length == 0) {
                return "msgs为null";
            }

            StringBuffer result = new StringBuffer();
            int len = msgs.length - 1, i = 0;
            Object lastObj = null;
            if (len % 2 == 0) {
                //说明当前参数是奇数个
                lastObj = msgs[len];
                len = len - 1;
            }

            for (; i <= len; i++) {
                if (i % 2 == 0) {
                    //说明当前参数是属性
                    result.append(msgs[i]).append("[");
                } else {
                    //说明当前参数是属性值
                    result.append(msgs[i] instanceof Supplier ? ((Supplier) msgs[i]).get() : msgs[i]).append("],");
                }
            }

            if (lastObj != null) {
                result.append(lastObj + ",");
            }

            result.replace(result.length() - 1, result.length(), "");
            String s = result.toString();
            return s;
        };
    }

    /**
     * 生成指定位数的随机数
     */
    public static String random(int length) {
        if (length <= 0) {
            return EMPTY;
        }

        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sbd.append(RANDOM.nextInt(10));
        }
        return sbd.toString();
    }

    public static String randomLetterAndNumber(int length) {
        if (length <= 0) {
            return EMPTY;
        }

        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sbd.append(TMP.charAt(RANDOM.nextInt(TMP.length())));
        }
        return sbd.toString();
    }


    /**
     * 传入的数不为 null 且 大于 0 就返回 true
     */
    public static boolean greater0(Number obj) {
        return obj != null && obj.doubleValue() > 0;
    }

    /**
     * 传入的数为 null 或 小于等于 0 就返回 true
     */
    public static boolean less0(Number obj) {
        return obj == null || obj.doubleValue() <= 0;
    }

    /**
     * 数值在指定的数区间时(包含边界)返回 true
     */
    public static boolean betweenBorder(Number num, Number min, Number max) {
        return num.doubleValue() >= min.doubleValue() && num.doubleValue() <= max.doubleValue();
    }


    /**
     * 是本地请求则返回 true
     */
    public static boolean isLocalRequest(String ip) {
        return LOCAL.contains(ip);
    }


}

package com.abc.myapp.utils;

import java.math.BigDecimal;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class BigDecimalUtils {

    /**
     * 小数点后保留n位小数, 转化为字符串返回
     */
    public static String getStringBigdecimal(BigDecimal bigDecimal, int scale, int roundingMode) {
        if (bigDecimal == null) {
            bigDecimal = new BigDecimal("0");
        }
        return bigDecimal.setScale(scale, roundingMode).toString();
    }

    /**
     * 小数点后保留n位小数后返回
     */
    public static BigDecimal getBigdecimal(BigDecimal bigDecimal, int scale, int roundingMode) {
        if (bigDecimal == null) {
            bigDecimal = new BigDecimal("0");
        }
        return bigDecimal.setScale(scale, roundingMode);
    }
}

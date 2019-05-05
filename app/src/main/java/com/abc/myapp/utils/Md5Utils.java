package com.abc.myapp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class Md5Utils {

    public static String md5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuilder buf = new StringBuilder("");
            for (byte aB : b) {
                i = aB;

                if (i < 0) {
                    i += 256;
                }

                if (i < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String md5WithSalt(String sourceStr, String salt) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte[] b = md.digest();
            int i;
            StringBuilder buf = new StringBuilder("");
            for (byte aB : b) {
                i = aB;

                if (i < 0) {
                    i += 256;
                }

                if (i < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();

            // 将第一次加密后的md5值后面拼接定盐
            result = result + salt;

            // 再次用md5加密
            MessageDigest md2 = MessageDigest.getInstance("MD5");
            md2.update(result.getBytes());
            byte[] b2 = md2.digest();
            int i2;
            StringBuilder buf2 = new StringBuilder("");
            for (byte aB : b2) {
                i2 = aB;

                if (i2 < 0) {
                    i2 += 256;
                }

                if (i2 < 16) {
                    buf2.append("0");
                }

                buf2.append(Integer.toHexString(i2));
            }

            result = buf2.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}

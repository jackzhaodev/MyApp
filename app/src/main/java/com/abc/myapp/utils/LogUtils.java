package com.abc.myapp.utils;

import android.util.Log;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class LogUtils {

    private static final int VERBOSE = 1;

    private static final int DEBUG = 2;

    private static final int INFO = 3;

    private static final int WARN = 4;

    private static final int ERROR = 5;

    private static final int NOTHING = 6;

    /**
     * 打release包时, 将LEVEL的值置为NOTHING, 不再打印log
     */
    private static int LEVEL = VERBOSE;

    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            Log.e(tag, msg);
        }
    }
}

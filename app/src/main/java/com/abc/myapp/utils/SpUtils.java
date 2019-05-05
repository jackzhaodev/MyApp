package com.abc.myapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class SpUtils {

    public static final String SP_USER_INFO = "sp_user_info";
    public static final String KEY_TOKEN = "key_token";
    public static final String KEY_HAD_LOGIN = "key_had_login";
    public static final String KEY_USER_ID = "key_user_id";
    public static final String KEY_USER_PHONE = "key_user_phone";
    public static final String KEY_USER_PORTRAIT = "key_user_portrait";
    public static final String SP_APP_INFO = "sp_app_info";
    public static final String KEY_HAD_SHOW_GUIDE_PAGE = "key_had_show_guide_page";
    public static final String KEY_USER_CID = "key_user_cid";

    /**
     * 存储boolean类型的数据
     */
    public static void putBooleanValue(Context context, String spName, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * 获取boolean类型的数据
     */
    public static boolean getBooleanValue(Context context, String spName, String key, boolean defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * 存储String类型的数据
     */
    public static void putStringValue(Context context, String spName, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 获取String类型的数据
     */
    public static String getStringValue(Context context, String spName, String key, String defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    /**
     * 存储Long类型的数据
     */
    public static void putLongValue(Context context, String spName, String key, Long value) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * 获取Long类型的数据
     */
    public static Long getLongValue(Context context, String spName, String key, Long defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.getLong(key, defaultValue);
    }

    /**
     * 存储int类型的数据
     */
    public static void putIntValue(Context context, String spName, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * 获取int类型的数据
     */
    public static int getIntValue(Context context, String spName, String key, int defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }

    /**
     * 存储Set<String>类型的数据
     */
    public static void putSetValue(Context context, String spName, String key, Set<String> value) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet(key, value);
        editor.apply();
    }

    /**
     * 获取Set<String>类型的数据
     */
    public static Set<String> getSetValue(Context context, String spName, String key, Set<String> defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.getStringSet(key, defaultValue);
    }
}

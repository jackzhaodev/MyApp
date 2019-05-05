package com.abc.myapp.utils;

import android.content.Context;
import android.content.Intent;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class IntentUtils {
    /**
     * 显式开启一个活动, 不用携带任何数据
     */
    public static void startActivity(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }
}

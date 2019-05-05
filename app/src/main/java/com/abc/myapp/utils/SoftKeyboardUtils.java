package com.abc.myapp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class SoftKeyboardUtils {

    /**
     * 隐藏软键盘
     */
    public static void hideSoftInputFromWindow(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && inputMethodManager.isActive()) {
            View currentFocus = ((Activity) context).getCurrentFocus();
            if (currentFocus != null) {
                inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
        }
    }
}

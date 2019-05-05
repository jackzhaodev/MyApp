package com.abc.myapp.utils;

import android.widget.EditText;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class TextViewUtils {

    /**
     * 获取EditText输入框中的文本内容
     */
    public static String getTextInput(EditText et) {
        return et.getText().toString().trim();
    }
}

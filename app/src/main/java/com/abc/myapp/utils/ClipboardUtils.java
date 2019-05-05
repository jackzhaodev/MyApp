package com.abc.myapp.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.HapticFeedbackConstants;
import android.view.View;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class ClipboardUtils {

    /**
     * 复制文本至剪切板
     */
    public static void copyToClipboard(View view, CharSequence text, Context context) {

        if (text == null) {
            return;
        }

        // 忽略系统是否设置了震动反馈, 任何时候都会给出震动反馈
        view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);

        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("", text);
        if (cm != null) {
            cm.setPrimaryClip(clip);
            ToastUtils.showShortToast(context, "复制成功");
        }
    }
}

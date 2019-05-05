package com.abc.myapp.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;

import androidx.core.content.ContextCompat;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class SpannableStringUtils {

    /**
     * 设置字体相对大小
     */
    public static void setRelativeSize(SpannableString ss, float textSize, int startIndex, int endIndex) {
        ss.setSpan(new RelativeSizeSpan(textSize), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 设置字体加粗
     */
    public static void setBold(SpannableString ss, int startIndex, int endIndex) {
        ss.setSpan(new StyleSpan(Typeface.BOLD), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 设置字体颜色
     *
     * @param colorId R.color.xxx
     */
    public static void setColor(SpannableString ss, Context context, int colorId, int startIndex, int endIndex) {
        ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, colorId)), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 设置下划线
     */
    public static void setUnderline(SpannableString ss, int startIndex, int endIndex) {
        ss.setSpan(new UnderlineSpan(), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 设置文本点击事件
     */
    public static void setOnClickListener(SpannableString ss, ClickableSpan clickableSpan, int startIndex, int endIndex) {
        ss.setSpan(clickableSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}

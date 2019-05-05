package com.abc.myapp.config;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class MyCountDownTimer extends CountDownTimer {

    /**
     * 显示倒计时文字的View, 一般是TextView
     */
    private TextView mTextView;

    /**
     * 倒计时中的文本颜色
     */
    private int mTextColorOnTick;

    /**
     * 正常的文本颜色
     */
    private int mTextColorNormal;

    /**
     * 倒计时中的文本
     */
    private String mTextOnTick;

    /**
     * 倒计时结束时的文本
     */
    private String mTextOnFinish;
    private Context mContext;

    /**
     * @param view              显示倒计时文字的View, 一般是TextView
     * @param millisInFuture    倒计时总时长
     * @param countDownInterval 倒计时间隔
     * @param textColorOnTick   倒计时中的文本颜色 R.color.xx
     * @param textColorNormal   倒计时结束时的文本 R.color.xx
     * @param textOnTick        倒计时中的文本单位
     * @param textOnFinish      倒计时结束时的文本
     * @param context           上下文
     */
    public MyCountDownTimer(TextView view, long millisInFuture, long countDownInterval,
                            int textColorOnTick, int textColorNormal, String textOnTick,
                            String textOnFinish, Context context) {
        super(millisInFuture, countDownInterval);

        this.mTextView = view;
        this.mTextColorOnTick = textColorOnTick;
        this.mTextColorNormal = textColorNormal;
        this.mTextOnFinish = textOnFinish;
        this.mTextOnTick = textOnTick;
        this.mContext = context;
    }

    @Override
    public void onTick(long millis) {
        String text = (millis / 1000) + 1 + mTextOnTick;
        mTextView.setText(text);

        // 文字的颜色设为倒计时时的颜色
        mTextView.setTextColor(ContextCompat.getColor(mContext, mTextColorOnTick));

        // 倒计时过程中, View不可点击, 文字在其上面显示
        mTextView.setEnabled(false);
        mTextView.setSelected(true);
    }

    @Override
    public void onFinish() {
        // 倒计时结束时, 重新设置View显示的文字, 文字颜色恢复为正常, View恢复可点击
        mTextView.setText(mTextOnFinish);

        mTextView.setTextColor(ContextCompat.getColor(mContext, mTextColorNormal));

        mTextView.setEnabled(true);
        mTextView.setSelected(false);
    }
}


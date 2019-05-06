package com.abc.myapp.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

import com.abc.myapp.R;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class MyClearEditText extends AppCompatEditText implements TextWatcher {

    /**
     * 清除按钮引用的图片资源
     */
    private Drawable mDrawable;

    private Context mContext;

    public MyClearEditText(Context context) {
        super(context);
        this.mContext = context;
    }

    public MyClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public MyClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        // 如果文本不为空, 显示删除按钮
        if (editable != null) {
            setDrawableVisible(editable.toString().trim().length() > 0);
        }
    }

    /**
     * 我们无法直接给EditText设置点击事件, 只能通过按下的位置来模拟clear点击事件
     * 当我们按下的位置在图标包括图标左边缘到控件右边缘的间距范围内均算有效
     * getWidth() 控件的宽度
     * event.getX() 手指抬起时水平方向的坐标, 改坐标是相对于控件本身而言的
     * getTotalPaddingRight() 图标左边缘距离控件右边缘的距离
     * getPaddingRight() 图标右边缘距离控件右边缘的距离
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (mDrawable != null) {
                // 控件左边缘到图标左边缘的距离
                int start = getWidth() - getTotalPaddingRight();
                // 控件左边缘到图标右边缘的距离
                int end = getWidth() - getPaddingRight();

                // 此处仅考虑了水平方向的触摸, 没有考虑竖直方向的触摸
                boolean xTouchable = (event.getX() > start) && (event.getX() < end);

                if (xTouchable) {
                    // 清除输入框中的文本
                    setText("");
                }
            }
        }

        // If a View that overrides onTouchEvent or uses an OnTouchListener
        // does not also implement performClick and call it when clicks are detected,
        // the View may not handle accessibility actions properly.
        // Logic handling the click actions should ideally be placed in
        // View#performClick as some accessibility services invoke performClick when a click action should occur.
        performClick();

        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    /**
     * 设置删除按钮
     */
    private void init() {
        // 获取drawableRight top start(0) top(1) end(2) bottom(3)
        mDrawable = getCompoundDrawablesRelative()[2];

        // 如果mDrawable为null, 说明xml中没有设置drawableEnd, 则使用R.drawable.clear这张图片
        if (mDrawable == null) {
            mDrawable = ContextCompat.getDrawable(mContext, R.drawable.ic_clear);
        }

        if (mDrawable == null) {
            return;
        }

        // 设置删除按钮的绘制范围
        mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());

        // 添加文本改变监听事件
        addTextChangedListener(this);

        // 删除按钮默认不可见
        setDrawableVisible(false);
    }

    private void setDrawableVisible(boolean visible) {
        Drawable drawable = visible ? mDrawable : null;
        setCompoundDrawablesRelative(getCompoundDrawablesRelative()[0],
                getCompoundDrawablesRelative()[1],
                drawable,
                getCompoundDrawablesRelative()[3]);
    }
}

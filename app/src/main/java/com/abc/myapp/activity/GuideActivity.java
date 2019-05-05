package com.abc.myapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.abc.myapp.R;
import com.abc.myapp.adapter.GuideViewPagerAdapter;
import com.abc.myapp.base.BaseActivity;
import com.abc.myapp.utils.IntentUtils;
import com.abc.myapp.utils.SpUtils;
import com.abc.myapp.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class GuideActivity extends BaseActivity {

    private List<View> mViewList = new ArrayList<>();

    private View.OnClickListener mOnClickListener = v -> {
        IntentUtils.startActivity(mContext, MainActivity.class);
        // 将已展示过引导页标志置为true
        SpUtils.putBooleanValue(mContext, SpUtils.SP_APP_INFO, SpUtils.KEY_HAD_SHOW_GUIDE_PAGE, true);
        // 关闭自己
        finish();
    };

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            // 共3张引导页
            if (position == 2) {
                View view = mViewList.get(2);
                ImageView imageView = view.findViewById(R.id.iv_guide_three);
                imageView.setOnClickListener(mOnClickListener);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected int setLayout() {
        return R.layout.activity_guide;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        // 设置为全屏模式
        StatusBarUtils.setFullScreen(mContext);

        ViewPager viewPager = findViewById(R.id.view_pager);

        View viewOne = View.inflate(mContext, R.layout.guide_layout_one, null);
        View viewTwo = View.inflate(mContext, R.layout.guide_layout_two, null);
        View viewThree = View.inflate(mContext, R.layout.guide_layout_three, null);
        mViewList.add(viewOne);
        mViewList.add(viewTwo);
        mViewList.add(viewThree);

        viewPager.setAdapter(new GuideViewPagerAdapter(mViewList));
        viewPager.addOnPageChangeListener(mOnPageChangeListener);
    }
}

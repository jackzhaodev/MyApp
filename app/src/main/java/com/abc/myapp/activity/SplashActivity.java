package com.abc.myapp.activity;

import android.os.Bundle;
import android.os.Handler;

import com.abc.myapp.R;
import com.abc.myapp.base.BaseActivity;
import com.abc.myapp.utils.IntentUtils;
import com.abc.myapp.utils.SpUtils;
import com.abc.myapp.utils.StatusBarUtils;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected int setLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        // 设置为全屏模式
        StatusBarUtils.setFullScreen(mContext);

        // 获取是否已展示过引导页
        final boolean hadShowGuidePage = SpUtils.getBooleanValue(mContext, SpUtils.SP_APP_INFO, SpUtils.KEY_HAD_SHOW_GUIDE_PAGE, false);

        // 1秒后跳转到引导界面或住界面, 并关闭自己
        new Handler().postDelayed(() -> {
            // 跳转到引导页或登录页
            if (hadShowGuidePage) {
                IntentUtils.startActivity(mContext, MainActivity.class);
            } else {
                IntentUtils.startActivity(mContext, GuideActivity.class);
            }
            finish();
        }, 1000);
    }
}

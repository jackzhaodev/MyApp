package com.abc.myapp.config;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDexApplication;

import com.abc.myapp.utils.ConstantUtils;
import com.abc.myapp.utils.SpUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 * 继承自MultiDexApplication, 为5.0以下应用配置方法数超过64K
 */
public class MyApplication extends MultiDexApplication {

    //static 代码段可以防止内存泄露
    static {
        //设置全局的RefreshHeader构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                ClassicsHeader header = new ClassicsHeader(context);
                return header;
            }
        });
        //设置全局的RefreshFooter构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                ClassicsFooter footer = new ClassicsFooter(context);
                layout.setEnableLoadMoreWhenContentNotFull(false);
                layout.setEnableAutoLoadMore(false);
                return footer;
            }
        });
    }

    /**
     * MyApplication实例
     */
    private static MyApplication sMyApplication;
    /**
     * 全局上下文
     */
    private static Context mAppContext;

    /**
     * 获取MyApplication实例
     */
    public static MyApplication getInstance() {
        return sMyApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppContext = getApplicationContext();
        sMyApplication = this;
    }

    public static Context getAppContext() {
        return mAppContext;
    }

    /**
     * 获取登录状态
     */
    public boolean hadLogin() {
        return SpUtils.getBooleanValue(mAppContext, SpUtils.SP_USER_INFO, SpUtils.KEY_HAD_LOGIN, false);
    }

    /**
     * 获取token
     */
    public String getToken() {
        return SpUtils.getStringValue(mAppContext, SpUtils.SP_USER_INFO, SpUtils.KEY_TOKEN, "");
    }

    /**
     * 获取用户id
     */
    public long getUserId() {
        return SpUtils.getLongValue(mAppContext, SpUtils.SP_USER_INFO, SpUtils.KEY_USER_ID, -1L);
    }

    /**
     * 获取用户手机号码
     */
    public String getUserPhone() {
        return SpUtils.getStringValue(mAppContext, SpUtils.SP_USER_INFO, SpUtils.KEY_USER_PHONE, "");
    }

    /**
     * 获取用户头像
     */
    public String getUserPortrait() {
        return SpUtils.getStringValue(mAppContext, SpUtils.SP_USER_INFO, SpUtils.KEY_USER_PORTRAIT, "");
    }
}

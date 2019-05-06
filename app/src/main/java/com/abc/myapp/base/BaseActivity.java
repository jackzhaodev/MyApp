package com.abc.myapp.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.abc.myapp.R;
import com.abc.myapp.activity.MainActivity;
import com.abc.myapp.utils.ActivityManageUtils;
import com.abc.myapp.utils.ConstantUtils;
import com.abc.myapp.utils.IntentUtils;
import com.abc.myapp.utils.SpUtils;
import com.abc.myapp.utils.ToastUtils;
import com.abc.myapp.view.MyLoadingDialog;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 上下文
     */
    public Context mContext;
    private LocalBroadcastManager mManager;
    private OfflineReceiver mReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        // 将当前Activity添加到一个List中
        ActivityManageUtils.addThisActivity(this);
        // 设置布局文件
        setContentView(setLayout());
        init(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 从List中移除当前Activity
        ActivityManageUtils.removeThisActivity(this);
    }

    protected abstract int setLayout();

    protected abstract void init(Bundle savedInstanceState);

    /**
     * 设置Toolbar
     */
    public void setToolbar(String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            // 设置title
            if (actionBar != null) {
                actionBar.setTitle(title);
            }
            // 设置导航栏图标
            toolbar.setNavigationIcon(R.drawable.ic_back);
            // 设置导航栏图标的点击事件
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 关闭当前活动界面
                    finish();
                }
            });
        }
    }

    /**
     * 设置Toolbar
     */
    public void setToolbar(int stringRes, int drawableRes) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            // 设置title
            if (actionBar != null) {
                actionBar.setTitle(stringRes);
            }
            // 设置导航栏图标
            toolbar.setNavigationIcon(drawableRes);
            // 设置导航栏图标的点击事件
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 关闭当前活动界面
                    finish();
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mManager = LocalBroadcastManager.getInstance(this);
        mReceiver = new OfflineReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConstantUtils.OFFLINE_RECEIVER);
        mManager.registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mManager.unregisterReceiver(mReceiver);
    }

    public class OfflineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int code = intent.getIntExtra("code", -1);
            int i = 0;
            i++;
            if (i == 1) {
                mManager.unregisterReceiver(mReceiver);
                switch (code) {
                    case 700:
                        // token过期
                        new AlertDialog.Builder(mContext)
                                .setTitle("登录通知")
                                .setMessage("您当前的登录状态已失效, 请重新登录")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        loginOut();
                                    }
                                })
                                .setCancelable(false)
                                .create()
                                .show();
                        break;
                    case 701:
                        // 其他设备登录
                        new AlertDialog.Builder(mContext)
                                .setTitle("下线通知")
                                .setMessage("您的账号已在其他设备登录")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        loginOut();
                                    }
                                })
                                .setCancelable(false)
                                .create()
                                .show();
                        break;
                    default:
                        break;
                }

            }
        }
    }

    private void loginOut() {
        MyLoadingDialog loadingDialog = new MyLoadingDialog.Builder(mContext).create();
        loadingDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismiss();

                // 将用户登录状态标记为false
                SpUtils.putBooleanValue(mContext, SpUtils.SP_USER_INFO, SpUtils.KEY_HAD_LOGIN, false);
                // 清空sp中的token信息
                SpUtils.putStringValue(mContext, SpUtils.SP_USER_INFO, SpUtils.KEY_TOKEN, "");
                // 清空sp中的userInfo信息
                SpUtils.putStringValue(mContext, SpUtils.SP_USER_INFO, SpUtils.KEY_USER_PHONE, "");
                SpUtils.putLongValue(mContext, SpUtils.SP_USER_INFO, SpUtils.KEY_USER_ID, -1L);
                // 关闭所有界面
                ActivityManageUtils.finishAllActivity();
                // 跳转到MainActivity或LoginActivity界面
                IntentUtils.startActivity(mContext, MainActivity.class);
                // 提示用户
                ToastUtils.showShortToast(mContext, "退出登录成功");
            }
        }, 500);
    }
}

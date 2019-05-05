package com.abc.myapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import com.abc.myapp.R;
import com.abc.myapp.base.BaseActivity;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class H5Activity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ll)
    LinearLayout mLl;

    private AgentWeb mAgentWeb;

    public static void actionStart(Context context, String title, String url) {
        Intent intent = new Intent(context, H5Activity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_h5;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");

        setToolbar(title);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) mLl, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url);
    }


    @Override
    protected void onPause() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (mAgentWeb != null) {
            // 点击返回键时, 如果网页可以回退, 实现网页回退; 如果网页不能回退, 执行默认操作
            if (!mAgentWeb.back()) {
                super.onBackPressed();
            }
        }
    }

}

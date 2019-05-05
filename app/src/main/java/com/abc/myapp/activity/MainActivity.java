package com.abc.myapp.activity;

import android.os.Bundle;

import com.abc.myapp.R;
import com.abc.myapp.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        // 设置Toolbar
        setToolbar("MainActivity");
    }
}

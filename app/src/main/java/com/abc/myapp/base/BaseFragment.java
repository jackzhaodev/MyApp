package com.abc.myapp.base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public abstract class BaseFragment extends Fragment {

    /**
     * 当前碎片所依附活动的上下文
     */
    public Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }
}

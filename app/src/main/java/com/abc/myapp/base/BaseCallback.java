package com.abc.myapp.base;

import android.content.Context;

import androidx.annotation.NonNull;

import com.abc.myapp.R;
import com.abc.myapp.entity.CommonResponse;
import com.abc.myapp.utils.ConstantUtils;
import com.abc.myapp.utils.LogUtils;
import com.abc.myapp.utils.ToastUtils;
import com.abc.myapp.view.MyLoadingDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public abstract class BaseCallback<T> implements Callback<T> {

    private Context mContext;
    private MyLoadingDialog mLoadingDialog;

    protected BaseCallback(Context context, MyLoadingDialog loadingDialog) {
        this.mContext = context;
        this.mLoadingDialog = loadingDialog;
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        // 消失加载框
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
        CommonResponse body = (CommonResponse) response.body();
        if (body == null) {
            LogUtils.d(ConstantUtils.MY_DEBUG, "BaseCallback onResponse() body == null");
            return;
        }
        // 如果接口调用未成功, 提示用户
        // token过期与其他设备登录接口已在OkHttp拦截器中统一处理, 这里无需再处理
        if (body.getCode() != ConstantUtils.OK
                && body.getCode() != ConstantUtils.TOKEN_EXPIRED
                && body.getCode() != ConstantUtils.OTHER_DEVICE_LOGIN) {
            ToastUtils.showLongToast(mContext, body.getMsg());
            return;
        }
        // body.getData()有时候可能为null, 因此不再统一做为null时return的处理, 根据具体返回情况具体处理
        // 接口调用成功下的操作
        onSuccess(call, response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        // 消失加载框
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
        ToastUtils.showLongToast(mContext, mContext.getString(R.string.request_failure));
        LogUtils.d(ConstantUtils.MY_DEBUG, "BaseCallback onFailure() " + t.getMessage());
    }

    /**
     * 接口调用成功下的操作
     */
    public abstract void onSuccess(Call<T> call, Response<T> response);
}
package com.abc.myapp.view;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.abc.myapp.R;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class MyLoadingDialog extends Dialog {

    public MyLoadingDialog(Context context) {
        super(context);
    }

    private MyLoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {

        private Context context;
        private String message;
        private boolean isShowMessage = true;
        private boolean isCancelable = true;
        private boolean isCancelOutside = true;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 设置提示信息
         */
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * 设置是否显示提示信息
         */
        public Builder setShowMessage(boolean isShowMessage) {
            this.isShowMessage = isShowMessage;
            return this;
        }

        /**
         * 设置是否可以按返回键取消
         */
        public Builder setCancelable(boolean isCancelable) {
            this.isCancelable = isCancelable;
            return this;
        }

        /**
         * 设置是否可以取消
         */
        public Builder setCancelOutside(boolean isCancelOutside) {
            this.isCancelOutside = isCancelOutside;
            return this;
        }

        public MyLoadingDialog create() {
            View view = View.inflate(context, R.layout.dialog_loading, null);
            MyLoadingDialog myLoadingDialog = new MyLoadingDialog(context, R.style.MyLoadingDialogStyle);
            TextView msgText = view.findViewById(R.id.tv_tip);
            if (isShowMessage) {
                if (TextUtils.isEmpty(message)) {
                    message = "加载中";
                }
                msgText.setText(message);
            } else {
                msgText.setVisibility(View.GONE);
            }
            myLoadingDialog.setContentView(view);
            myLoadingDialog.setCancelable(isCancelable);
            myLoadingDialog.setCanceledOnTouchOutside(isCancelOutside);
            return myLoadingDialog;
        }
    }
}


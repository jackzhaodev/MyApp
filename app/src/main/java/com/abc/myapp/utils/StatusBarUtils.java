package com.abc.myapp.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class StatusBarUtils {

    /**
     * 4.4及以上版本, 设置透明状态栏.
     * <p>
     * 这种效果下, 界面内容布局会侵入状态栏
     * 4.4全透明, 5.0及以上半透明
     * <p>
     * 这种效果一般适用于界面顶部直接是图片或轮播图或者整个界面都为一张图片的情况
     * <p>
     * 在其他情况下(如在界面中使用了Toolbar或自定义Toolbar),
     * 可以在界面根布局中添加android:fitsSystemWindows="true"这个属性来解决布局侵入状态栏的问题,
     * 但一般不建议在使用了Toolbar的界面实现透明状态栏效果
     * <p>
     * 默认的Theme.AppCompat.Light.DarkActionBar不会侵入状态栏,
     * 但是设置Theme.AppCompat.Light.NoActionBar后使用Toolbar替代却会侵入状态栏
     * <p>
     * Android Studio中的部分模拟器无法显示这种效果
     */
    public static void setTranslucentStatusBarOnAndAboveKitkat(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = ((Activity) context).getWindow();
            int flag = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            window.addFlags(flag);
        }
    }

    /**
     * 4.4及以上版本, 同时设置透明状态栏和透明导航栏
     * 这种情况下, 界面内容布局会侵入状态栏和导航栏
     * 4.4全透明, 5.0及以上半透明
     */
    public static void setBothTranslucentStatusBarAndNavigationBarOnAndAboveKitkat(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = ((Activity) context).getWindow();
            int flag = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            window.addFlags(flag);
        }
    }

    /**
     * 5.0及以上版本, 设置透明状态栏, 并实现界面内容布局侵入状态栏,
     * 透明效果为全透明
     * <p>
     * 这种效果一般适用于界面顶部直接是图片或轮播图或者整个界面都为一张图片的情况
     * <p>
     * 在其他情况下(如在界面中使用了Toolbar或自定义Toolbar),
     * 可以在界面根布局中添加android:fitsSystemWindows="true"这个属性来解决布局侵入状态栏的问题
     * 但一般不建议在使用了Toolbar的界面实现透明状态栏效果
     */
    public static void setTranslucentStatusBarOnAndAboveLollipop(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((Activity) context).getWindow();
            View decorView = window.getDecorView();
            // 下面两个flag一起使用, 表示会让应用的主体内容占用系统的状态栏空间
            int flag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(flag);
            // 将状态栏设置为透明色
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 5.0及以上, 同时设置透明状态栏和透明导航栏
     * 这种情况下, 界面布局会侵入状态栏和导航栏
     * 透明效果为全透明
     */
    public static void setBothTranslucentStatusBarAndNavigationBarOnAndAboveLollipop(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((Activity) context).getWindow();
            View decorView = window.getDecorView();
            // 下面两个flag一起使用, 表示会让应用的主体内容占用系统的状态栏空间
            int flag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(flag);
            // 将状态栏设置为透明色
            window.setStatusBarColor(Color.TRANSPARENT);
            // 将导航栏设置为透明色
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 6.0及以上版本, 实现状态栏黑色文字和图标的效果(默认是白色文字+白色图标)
     * <p>
     * 实现这样的效果会导致界面内容区域侵入状态栏
     * <p>
     * 状态栏黑色文字和图标效果一般和白色状态栏搭配使用, 但也可单独使用
     * <p>
     * 在使用Toolbar或自定义Toolbar为的, 需要在界面根布局中添加android:fitsSystemWindows="true"属性来解决布局侵入状态栏的问题
     * 但一般不建议在使用了Toolbar的界面使用此代码
     * <p>
     * 如果在界面顶部就是图片或轮播图, 或界面为一张图片时, 则不需要设置android:fitsSystemWindows="true"属性,
     * 如果再设置了window.setStatusBarColor(Color.TRANSPARENT), 就会实现图片侵入状态栏,但状态栏文字和图标为黑色的效果了
     */
    public static void setLightStatusBarOnAndAboveM(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = ((Activity) context).getWindow();
            int flag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            window.getDecorView().setSystemUiVisibility(flag);
        }
    }

    /**
     * 4.4版本, 创建一个View覆盖状态栏, 以达到状态栏着色的目的
     */
    public static void addStatusBarView(Context context, int color) {
        Window window = ((Activity) context).getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ViewGroup systemContent = ((Activity) context).findViewById(android.R.id.content);

        View statusBarView = new View(context);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                StatusBarUtils.getStatusBarHeight(context));

        statusBarView.setBackgroundColor(color);
        systemContent.getChildAt(0).setFitsSystemWindows(true);
        systemContent.addView(statusBarView, 0, lp);
    }

    /**
     * 5.0及以上版本, 设置状态栏颜色
     */
    public static void setStatusBarColor(Context context, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((Activity) context).getWindow();
            window.setStatusBarColor(color);
        }
    }

    /**
     * 隐藏系统状态栏, 状态栏重新显示时为透明的(5.0及以上)
     * <p>
     * 使用这种方法, 从屏幕顶部下拉, 即可重新显示状态栏, 4.4仍是黑色的状态栏, 5.0及以上为透明状态栏,
     * 但在Android Studio 5.0的模拟器上不知为什么无法实现下拉显示状态栏的效果;
     * <p>
     * 点击屏幕任意位置可以消失状态栏, 或者短时间内状态栏也会自动重新消失
     */
    public static void hideStatusBarWithColorTranslucent(Context context) {
        Window window = ((Activity) context).getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 根据Android的设计建议, ActionBar是不应该独立于状态栏而单独显示的, 因此状态栏如果隐藏了, 同时也需要将ActionBar进行隐藏
        ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();
        if (actionBar != null) {
            // 隐藏ActionBar
            actionBar.hide();
        }
    }

    /**
     * 隐藏系统状态栏, 状态栏重新显示时颜色不变
     * <p>
     * 这种情况下, 从屏幕顶部下拉, 即可重新显示状态栏, 下拉之后除非退出当前界面, 否则无法重新隐藏系统状态栏,
     * 状态栏的颜色仍是原来的颜色, 并不会变为透明的;
     * <p>
     * 点击屏幕无法实现显示状态栏, 只有从屏幕顶部下拉才可
     */
    public static void hideStatusBarWithColorNoChange(Context context) {
        Window window = ((Activity) context).getWindow();
        View decorView = window.getDecorView();
        // 通过设置下列flag, 隐藏系统状态栏
        int flag = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(flag);

        // 根据Android的设计建议, ActionBar是不应该独立于状态栏而单独显示的, 因此状态栏如果隐藏了, 同时也需要将ActionBar进行隐藏
        ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();
        if (actionBar != null) {
            // 隐藏ActionBar
            actionBar.hide();
        }
    }

    /**
     * 同时隐藏系统状态栏和导航栏, 二者重新显示时颜色不变
     * <p>
     * 这种情况下, 触摸屏幕任意位置, 或者从屏幕顶部下拉或底部上拉, 都会显示原先的状态栏或导航栏,
     * 并且只有退出当前界面再重新进入, 否则无法再次隐藏它们
     */
    public static void hideBothStatusBarAndNavigationBar(Context context) {
        Window window = ((Activity) context).getWindow();
        View decorView = window.getDecorView();
        // 通过设置下列flag, 隐藏系统状态栏
        int flag = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(flag);

        // 根据Android的设计建议, ActionBar是不应该独立于状态栏而单独显示的, 因此状态栏如果隐藏了, 同时也需要将ActionBar进行隐藏
        ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();
        if (actionBar != null) {
            // 隐藏ActionBar
            actionBar.hide();
        }
    }

    /**
     * 实现沉浸式全屏模式
     * <p>
     * 这种情况下, 界面默认是全屏的, 状态栏和导航栏都不会显示,
     * 当需要状态栏或导航栏时, 从屏幕上方下拉或下方上拉, 状态栏和导航栏就会显示出来, 并且是透明的, 并且界面在二者下方,
     * 一段时间内没有操作的话, 状态栏和导航栏又会隐藏, 重新回到全屏状态.
     * <p>
     * 按返回键退出此洁面时, 也会显示状态栏和导航栏
     */
    //    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//
//        if (hasFocus && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            View decorView = getWindow().getDecorView();
//            int flag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
//            decorView.setSystemUiVisibility(flag);
//        }
//    }
    public static void setFullScreen(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = ((Activity) context).getWindow();
            View decorView = window.getDecorView();
            int flag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(flag);
        }
    }

    /**
     * 隐藏系统ActionBar
     */
    public static void hideActionBar(Context context) {
        ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    /**
     * 获取状态栏高度
     * <p>
     * 使用实例:
     * 拉伸View一个状态栏高度
     * view.setPadding(0,statusBarHeight,0,0);
     */
    public static int getStatusBarHeight(Context context) {
        int height = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = context.getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }


    /**
     * 4.4及以上版本, 设置透明导航栏
     * 这种情况下, 界面内容布局会侵入状态栏和导航栏
     * 4.4全透明, 5.0及以上半透明
     */
    public static void setTranslucentNavigationBarOnAndAboveKitkat(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = ((Activity) context).getWindow();
            int flag = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            window.addFlags(flag);
        }
    }

    /**
     * 5.0及以上, 实现透明导航栏
     * 这种情况下, 界面布局会侵入导航栏和状态栏
     * 透明效果为全透明
     */
    public static void setTranslucentNavigationBarOnAndAboveLollipop(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((Activity) context).getWindow();
            View decorView = window.getDecorView();
            // 下面两个flag一起使用, 表示会让应用的主体内容占用系统的状态栏空间
            int flag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(flag);
            // 将导航栏设置为透明色
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 隐藏导航栏, 导航栏重新显示时颜色不变
     * <p>
     * 这种情况下, 点击屏幕任意位置, 或者从屏幕底部向上拉, 都会出现原来的导航栏,
     * 并且只有退出当前界面重新进入, 否则无法再次隐藏导航栏
     */
    public static void hideNavigationBar(Context context) {
        Window window = ((Activity) context).getWindow();
        View decorView = window.getDecorView();
        int flag = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(flag);
    }

    /**
     * 5.0及以上版本, 设置导航栏颜色
     */
    public static void setNavigationBarColor(Context context, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((Activity) context).getWindow();
            window.setNavigationBarColor(color);
        }
    }
}

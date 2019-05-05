package com.abc.myapp.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class ActivityManageUtils {

    private static List<Activity> sActivityList = new ArrayList<>();

    /**
     * 添加活动到List中
     */
    public static void addThisActivity(Activity activity) {
        sActivityList.add(activity);
    }

    /**
     * 从List中移除活动
     */
    public static void removeThisActivity(Activity activity) {
        sActivityList.remove(activity);
    }

    /**
     * 清空List中的所有活动
     */
    public static void finishAllActivity() {
        for (Activity activity : sActivityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}

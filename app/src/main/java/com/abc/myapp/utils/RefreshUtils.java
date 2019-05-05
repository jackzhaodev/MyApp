package com.abc.myapp.utils;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class RefreshUtils {

    /**
     * 停止下拉刷新
     *
     * @param refreshLayout SmartRefreshLayout
     */
    public static void finishRefresh(SmartRefreshLayout refreshLayout) {
        if (refreshLayout != null && refreshLayout.getState() == RefreshState.Refreshing) {
            refreshLayout.finishRefresh();
        }
    }

    /**
     * 停止上拉加载
     *
     * @param refreshLayout SmartRefreshLayout
     */
    public static void finishLoadMore(SmartRefreshLayout refreshLayout) {
        if (refreshLayout != null && refreshLayout.getState() == RefreshState.Loading) {
            refreshLayout.finishLoadMore();
        }
    }

    /**
     * 停止下拉刷新或上拉加载
     */
    public static void finishRefreshOrLoadMore(SmartRefreshLayout refreshLayout) {
        if (refreshLayout != null && refreshLayout.getState() == RefreshState.Refreshing) {
            refreshLayout.finishRefresh();
        }
        if (refreshLayout != null && refreshLayout.getState() == RefreshState.Loading) {
            refreshLayout.finishLoadMore();
        }
    }
}

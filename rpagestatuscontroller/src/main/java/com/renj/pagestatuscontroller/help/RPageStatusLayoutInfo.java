package com.renj.pagestatuscontroller.help;

import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;

import com.renj.pagestatuscontroller.annotation.RPageStatus;
import com.renj.pagestatuscontroller.annotation.RPageStatusEvent;
import com.renj.pagestatuscontroller.listener.OnRPageEventListener;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：itrenjunhua@163.com
 * <p>
 * 创建时间：2019-06-20   16:34
 * <p>
 * 描述：状态页面信息封装
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RPageStatusLayoutInfo {
    @RPageStatus
    @IntRange(from = 0, to = 5)
    public int pageStatus;
    @LayoutRes
    public int layoutId;
    @RPageStatusEvent
    public int rPageStatusEvent;
    @IdRes
    public int viewId;
    public int[] viewIds;
    public boolean showLoading;
    public OnRPageEventListener onRPageEventListener;

    public RPageStatusLayoutInfo(@RPageStatus int pageStatus, @LayoutRes int layoutId, @RPageStatusEvent int rPageStatusEvent) {
        this.pageStatus = pageStatus;
        this.layoutId = layoutId;
        this.rPageStatusEvent = rPageStatusEvent;
    }

    public RPageStatusLayoutInfo(@RPageStatus int pageStatus, @LayoutRes int layoutId, @RPageStatusEvent int rPageStatusEvent, @IdRes int viewId, boolean showLoading, OnRPageEventListener onRPageEventListener) {
        this.pageStatus = pageStatus;
        this.layoutId = layoutId;
        this.rPageStatusEvent = rPageStatusEvent;
        this.viewId = viewId;
        this.showLoading = showLoading;
        this.onRPageEventListener = onRPageEventListener;
    }

    public RPageStatusLayoutInfo(@RPageStatus int pageStatus, @LayoutRes int layoutId, @RPageStatusEvent int rPageStatusEvent, @IdRes int[] viewIds, boolean showLoading, OnRPageEventListener onRPageEventListener) {
        this.pageStatus = pageStatus;
        this.layoutId = layoutId;
        this.rPageStatusEvent = rPageStatusEvent;
        this.viewIds = viewIds;
        this.showLoading = showLoading;
        this.onRPageEventListener = onRPageEventListener;
    }
}

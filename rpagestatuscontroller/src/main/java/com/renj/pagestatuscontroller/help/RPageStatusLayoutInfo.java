package com.renj.pagestatuscontroller.help;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

import com.renj.pagestatuscontroller.annotation.RPageStatus;
import com.renj.pagestatuscontroller.annotation.RPageStatusEvent;
import com.renj.pagestatuscontroller.listener.OnRPageEventListener;
import com.renj.pagestatuscontroller.listener.OnRPageInflateFinishListener;

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
    public int pageStatus;
    @LayoutRes
    public int layoutId;
    @RPageStatusEvent
    public int rPageStatusEvent;
    @IdRes
    public int viewId;
    public int[] viewIds;
    public int[] goneViewIds;
    public boolean showLoading;
    public OnRPageEventListener onRPageEventListener;
    public OnRPageInflateFinishListener onRPageInflateFinishListener;

    public RPageStatusLayoutInfo(RPageStatusLayoutInfo rPageStatusLayoutInfo) {
        if (null != rPageStatusLayoutInfo) {
            this.pageStatus = rPageStatusLayoutInfo.pageStatus;
            this.layoutId = rPageStatusLayoutInfo.layoutId;
            this.rPageStatusEvent = rPageStatusLayoutInfo.rPageStatusEvent;
            this.viewId = rPageStatusLayoutInfo.viewId;
            this.viewIds = rPageStatusLayoutInfo.viewIds;
            this.goneViewIds = rPageStatusLayoutInfo.goneViewIds;
            this.showLoading = rPageStatusLayoutInfo.showLoading;
            this.onRPageEventListener = rPageStatusLayoutInfo.onRPageEventListener;
            this.onRPageInflateFinishListener = rPageStatusLayoutInfo.onRPageInflateFinishListener;
        }
    }

    public RPageStatusLayoutInfo(@RPageStatus int pageStatus, @LayoutRes int layoutId,
                                 @RPageStatusEvent int rPageStatusEvent) {
        this.pageStatus = pageStatus;
        this.layoutId = layoutId;
        this.rPageStatusEvent = rPageStatusEvent;
    }
}

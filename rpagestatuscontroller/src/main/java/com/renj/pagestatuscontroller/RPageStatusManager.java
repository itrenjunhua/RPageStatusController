package com.renj.pagestatuscontroller;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.SparseArray;

import com.renj.pagestatuscontroller.annotation.RPageStatus;
import com.renj.pagestatuscontroller.annotation.RPageStatusEvent;
import com.renj.pagestatuscontroller.help.RPageStatusLayoutInfo;
import com.renj.pagestatuscontroller.listener.OnRPageEventListener;
import com.renj.pagestatuscontroller.utils.RPageStatusUtils;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：itrenjunhua@163.com
 * <p>
 * 创建时间：2019-06-20   14:45
 * <p>
 * 描述：全局管理类，配置全局状态页面
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RPageStatusManager implements IRPageStatusConfig<RPageStatusManager> {
    private final static RPageStatusManager INSTANCE = new RPageStatusManager();
    final static SparseArray<RPageStatusLayoutInfo> mRPageStatusLayoutArray = new SparseArray<>();

    private RPageStatusManager() {
    }

    public static RPageStatusManager getInstance() {
        return INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RPageStatusManager addPageStatusView(@RPageStatus int pageStatus, @LayoutRes int layoutId) {
        RPageStatusUtils.checkAddContentStatusPage(pageStatus);
        RPageStatusLayoutInfo rPageStatusLayoutInfo = new RPageStatusLayoutInfo(pageStatus, layoutId, RPageStatusEvent.NO_CLICK);
        mRPageStatusLayoutArray.put(pageStatus, rPageStatusLayoutInfo);
        return INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RPageStatusManager registerOnRPageEventListener(int pageStatus, @IdRes int viewId,
                                                           @Nullable OnRPageEventListener onRPageEventListener) {
        return registerOnRPageEventListener(pageStatus, true, viewId, onRPageEventListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RPageStatusManager registerOnRPageEventListener(int pageStatus, boolean showLoading, @IdRes int viewId,
                                                           @Nullable OnRPageEventListener onRPageEventListener) {
        RPageStatusUtils.checkAddContentStatusPage(pageStatus);
        RPageStatusLayoutInfo rPageStatusLayoutInfo = mRPageStatusLayoutArray.get(pageStatus);
        if (!RPageStatusUtils.isNull(rPageStatusLayoutInfo, onRPageEventListener)) {
            rPageStatusLayoutInfo.viewId = viewId;
            rPageStatusLayoutInfo.showLoading = showLoading;
            rPageStatusLayoutInfo.rPageStatusEvent = RPageStatusEvent.SINGLE_VIEW_CLICK;
            rPageStatusLayoutInfo.onRPageEventListener = onRPageEventListener;
            mRPageStatusLayoutArray.put(pageStatus, rPageStatusLayoutInfo);
        }
        return INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RPageStatusManager registerOnRPageEventListener(int pageStatus, @IdRes int[] viewIds,
                                                           @Nullable OnRPageEventListener onRPageEventListener) {
        return registerOnRPageEventListener(pageStatus, true, viewIds, onRPageEventListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RPageStatusManager registerOnRPageEventListener(int pageStatus, boolean showLoading, @IdRes int[] viewIds,
                                                           @Nullable OnRPageEventListener onRPageEventListener) {
        RPageStatusUtils.checkAddContentStatusPage(pageStatus);
        RPageStatusLayoutInfo rPageStatusLayoutInfo = mRPageStatusLayoutArray.get(pageStatus);
        if (!RPageStatusUtils.isNull(rPageStatusLayoutInfo, onRPageEventListener)) {
            rPageStatusLayoutInfo.viewIds = viewIds;
            rPageStatusLayoutInfo.showLoading = showLoading;
            rPageStatusLayoutInfo.rPageStatusEvent = RPageStatusEvent.MORE_VIEW_CLICK;
            rPageStatusLayoutInfo.onRPageEventListener = onRPageEventListener;
            mRPageStatusLayoutArray.put(pageStatus, rPageStatusLayoutInfo);
        }
        return INSTANCE;
    }
}

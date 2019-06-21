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
 * 邮箱：renjunhua@anlovek.com
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
     * 增加状态页面布局
     *
     * @param pageStatus 页面状态 {@link RPageStatus}
     * @param layoutId   布局资源id
     * @return
     */
    @Override
    public RPageStatusManager addPageStatusView(@RPageStatus int pageStatus, @LayoutRes int layoutId) {
        RPageStatusUtils.checkAddContentStatusPage(pageStatus);
        RPageStatusLayoutInfo rPageStatusLayoutInfo = new RPageStatusLayoutInfo(pageStatus, layoutId, RPageStatusEvent.NO_CLICK);
        mRPageStatusLayoutArray.put(pageStatus, rPageStatusLayoutInfo);
        return this;
    }

    /**
     * 增加状态页面布局
     *
     * @param pageStatus           页面状态 {@link RPageStatus}
     * @param layoutId             布局资源id
     * @param viewId               布局文件中有点击事件的View的id
     * @param onRPageEventListener 点击事件回调监听
     * @return
     */
    @Override
    public RPageStatusManager addPageStatusView(@RPageStatus int pageStatus, @LayoutRes int layoutId, @IdRes int viewId, @Nullable OnRPageEventListener onRPageEventListener) {
        RPageStatusUtils.checkAddContentStatusPage(pageStatus);
        RPageStatusLayoutInfo rPageStatusLayoutInfo = new RPageStatusLayoutInfo(pageStatus, layoutId, RPageStatusEvent.SINGLE_VIEW_CLICK, viewId, onRPageEventListener);
        mRPageStatusLayoutArray.put(pageStatus, rPageStatusLayoutInfo);
        return this;
    }

    /**
     * 增加状态页面布局
     *
     * @param pageStatus           页面状态 {@link RPageStatus}
     * @param layoutId             布局资源id
     * @param viewIds              布局文件中有点击事件的View的id集合
     * @param onRPageEventListener 点击事件回调监听
     * @return
     */
    @Override
    public RPageStatusManager addPageStatusView(@RPageStatus int pageStatus, @LayoutRes int layoutId, @IdRes int[] viewIds, @Nullable OnRPageEventListener onRPageEventListener) {
        RPageStatusUtils.checkAddContentStatusPage(pageStatus);
        RPageStatusLayoutInfo rPageStatusLayoutInfo = new RPageStatusLayoutInfo(pageStatus, layoutId, RPageStatusEvent.MORE_VIEW_CLICK, viewIds, onRPageEventListener);
        mRPageStatusLayoutArray.put(pageStatus, rPageStatusLayoutInfo);
        return this;
    }
}

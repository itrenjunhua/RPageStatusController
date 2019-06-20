package com.renj.pagestatuscontroller;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.View;

import com.renj.pagestatuscontroller.annotation.RPageStatus;
import com.renj.pagestatuscontroller.annotation.RPageStatusEvent;
import com.renj.pagestatuscontroller.listener.IRPageEventListener;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：renjunhua@anlovek.com
 * <p>
 * 创建时间：2019-06-20   14:45
 * <p>
 * 描述：页面状态控制类，包括配置独立的状态页面以及修改页面状态
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RPageStatusController implements IRPageStatusController<RPageStatusController> {
    private SparseArray<RPageStatusLayoutInfo> mRPageStatusLayoutArray = new SparseArray<>();

    private RPageStatusController() {
    }

    public static RPageStatusController get() {
        return new RPageStatusController();
    }

    /**
     * 绑定 {@link Activity}
     *
     * @param activity {@link Activity}
     * @return
     */
    @Override
    public RPageStatusController bind(@NonNull Activity activity) {
        return null;
    }

    /**
     * 绑定 {@link Fragment}
     *
     * @param fragment {@link Fragment}
     * @param view     {@link Fragment}根View
     * @return
     */
    @Override
    public RPageStatusController bind(@NonNull Fragment fragment, @NonNull View view) {
        return null;
    }

    /**
     * 绑定 {@link android.app.Fragment}
     *
     * @param fragment {@link android.app.Fragment}
     * @param view     {@link android.app.Fragment}根View
     * @return
     */
    @Override
    public RPageStatusController bind(@NonNull android.app.Fragment fragment, @NonNull View view) {
        return null;
    }

    /**
     * 绑定到 {@link View}
     *
     * @param view {@link View}
     * @return
     */
    @Override
    public RPageStatusController bind(@NonNull View view) {
        return null;
    }

    /**
     * 修改页面状态
     *
     * @param pageStatus 状态值
     */
    @Override
    public void changePageStatus(int pageStatus) {

    }

    /**
     * 增加状态页面布局
     *
     * @param pageStatus 页面状态 {@link RPageStatus}
     * @param layoutId   布局资源id
     * @return
     */
    @Override
    public RPageStatusController addPageStatusView(@RPageStatus int pageStatus, @LayoutRes int layoutId) {
        RPageStatusLayoutInfo rPageStatusLayoutInfo = new RPageStatusLayoutInfo(pageStatus, layoutId, RPageStatusEvent.NO_CLICK);
        mRPageStatusLayoutArray.put(pageStatus, rPageStatusLayoutInfo);
        return this;
    }

    /**
     * 增加状态页面布局
     *
     * @param pageStatus          页面状态 {@link RPageStatus}
     * @param layoutId            布局资源id
     * @param viewId              布局文件中有点击事件的View的id
     * @param irPageEventListener 点击事件回调监听
     * @return
     */
    @Override
    public RPageStatusController addPageStatusView(@RPageStatus int pageStatus, @LayoutRes int layoutId, @IdRes int viewId, @Nullable IRPageEventListener irPageEventListener) {
        RPageStatusLayoutInfo rPageStatusLayoutInfo = new RPageStatusLayoutInfo(pageStatus, layoutId, RPageStatusEvent.SINGLE_VIEW_CLICK, viewId, irPageEventListener);
        mRPageStatusLayoutArray.put(pageStatus, rPageStatusLayoutInfo);
        return this;
    }

    /**
     * 增加状态页面布局
     *
     * @param pageStatus          页面状态 {@link RPageStatus}
     * @param layoutId            布局资源id
     * @param viewIds             布局文件中有点击事件的View的id集合
     * @param irPageEventListener 点击事件回调监听
     * @return
     */
    @Override
    public RPageStatusController addPageStatusView(@RPageStatus int pageStatus, @LayoutRes int layoutId, @IdRes int[] viewIds, @Nullable IRPageEventListener irPageEventListener) {
        RPageStatusLayoutInfo rPageStatusLayoutInfo = new RPageStatusLayoutInfo(pageStatus, layoutId, RPageStatusEvent.MORE_VIEW_CLICK, viewIds, irPageEventListener);
        mRPageStatusLayoutArray.put(pageStatus, rPageStatusLayoutInfo);
        return this;
    }
}

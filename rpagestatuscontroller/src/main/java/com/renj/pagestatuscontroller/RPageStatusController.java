package com.renj.pagestatuscontroller;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.renj.pagestatuscontroller.annotation.RPageStatus;
import com.renj.pagestatuscontroller.annotation.RPageStatusEvent;
import com.renj.pagestatuscontroller.help.RPageStatusHelp;
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
 * 描述：页面状态控制类，包括配置独立的状态页面以及修改页面状态
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RPageStatusController implements IRPageStatusController<RPageStatusController> {
    private SparseArray<RPageStatusLayoutInfo> mRPageStatusLayoutArray = new SparseArray<>();
    private RPageStatusHelp mRPageStatusHelp;

    private RPageStatusController() {
        RPageStatusUtils.deepCopyRPageStatusLayoutInfo(RPageStatusManager.mRPageStatusLayoutArray, mRPageStatusLayoutArray);
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
    public void bind(@NonNull Activity activity) {
        checkBindingStatus();
        RPageStatusUtils.checkParams(activity);
        ViewGroup contentView = activity.findViewById(android.R.id.content);
        if (RPageStatusUtils.isNull(contentView))
            throw new IllegalStateException("bind activity failed: cannot find contentView.");
        View targetView = null;
        if (contentView.getChildCount() > 0)
            targetView = contentView.getChildAt(0);
        if (RPageStatusUtils.isNull(targetView))
            throw new IllegalStateException("bind activity failed: bind() method should be after the setContentView() method.");
        mRPageStatusHelp = new RPageStatusHelp(activity, this, activity, contentView, targetView);
        mRPageStatusHelp.bindActivity();
    }

    /**
     * 绑定 {@link Fragment}
     *
     * @param fragment {@link Fragment}
     * @param view     {@link Fragment}根View
     * @return
     */
    @Override
    public View bind(@NonNull Fragment fragment, @NonNull View view) {
        checkBindingStatus();
        RPageStatusUtils.checkParams(fragment, view);
        mRPageStatusHelp = new RPageStatusHelp(fragment.getActivity(), this, fragment, null, view);
        return mRPageStatusHelp.bindFragmentSupport();
    }

    /**
     * 绑定 {@link android.app.Fragment}
     *
     * @param fragment {@link android.app.Fragment}
     * @param view     {@link android.app.Fragment}根View
     * @return
     */
    @Override
    public View bind(@NonNull android.app.Fragment fragment, @NonNull View view) {
        checkBindingStatus();
        RPageStatusUtils.checkParams(fragment, view);
        mRPageStatusHelp = new RPageStatusHelp(fragment.getActivity(), this, fragment, null, view);
        return mRPageStatusHelp.bindFragment();
    }

    /**
     * 绑定到 {@link View}
     *
     * @param view {@link View}
     * @return
     */
    @Override
    public void bind(@NonNull View view) {
        checkBindingStatus();
        RPageStatusUtils.checkParams(view);
        ViewGroup parentView = (ViewGroup) view.getParent();
        if (RPageStatusUtils.isNull(parentView))
            throw new IllegalStateException("bind view failed: cannot find parent view.");
        mRPageStatusHelp = new RPageStatusHelp(view.getContext(), this, view, parentView, view);
        mRPageStatusHelp.bindView();
    }

    /**
     * 重置某一状态的事件监听，点击时会将页面修改为 {@link RPageStatus#LOADING} 状态，
     * 如果不需要修改，请调用 {@link #resetOnRPageEventListener(int, boolean, OnRPageEventListener)} 方法修改<br/>
     * <b>生效条件：<br/>
     * 1.配置了该状态（调用了相关方法，设置了全局或独立的该状态的状态页面）<br/>
     * 2.还没有调用过 {@link #changePageStatus(int)} 方法设置该状态</b>
     *
     * @param pageStatus           需要重置监听事件的状态
     * @param onRPageEventListener 监听事件对象
     * @return
     */
    @Override
    public RPageStatusController resetOnRPageEventListener(@RPageStatus int pageStatus, OnRPageEventListener onRPageEventListener) {
        return resetOnRPageEventListener(pageStatus, true, onRPageEventListener);
    }

    /**
     * 重置某一状态的事件监听。<br/>
     * <b>生效条件：<br/>
     * 1.配置了该状态（调用了相关方法，设置了全局或独立的该状态的状态页面）<br/>
     * 2.还没有调用过 {@link #changePageStatus(int)} 方法设置该状态</b>
     *
     * @param pageStatus           需要重置监听事件的状态
     * @param showLoading          点击时是否自动显示成 {@link RPageStatus#LOADING} 状态
     * @param onRPageEventListener 监听事件对象
     * @return
     */
    @Override
    public RPageStatusController resetOnRPageEventListener(int pageStatus, boolean showLoading, OnRPageEventListener onRPageEventListener) {
        RPageStatusLayoutInfo rPageStatusLayoutInfo = mRPageStatusLayoutArray.get(pageStatus);
        if (!RPageStatusUtils.isNull(rPageStatusLayoutInfo, onRPageEventListener)) {
            rPageStatusLayoutInfo.onRPageEventListener = onRPageEventListener;
            rPageStatusLayoutInfo.showLoading = showLoading;
        }

        return this;
    }

    private void checkBindingStatus() {
        if (!RPageStatusUtils.isNull(mRPageStatusHelp))
            throw new IllegalStateException("Cannot repeat binding.");
    }

    /**
     * 修改页面状态
     *
     * @param pageStatus 状态值
     */
    @Override
    public void changePageStatus(@RPageStatus int pageStatus) {
        if (RPageStatusUtils.isNull(mRPageStatusHelp))
            throw new IllegalStateException("nothing bind.please call bind() method.");

        mRPageStatusHelp.changePageStatus(pageStatus, mRPageStatusLayoutArray);
    }

    /**
     * 获取当前的页面状态
     *
     * @return
     */
    @Override
    public int getCurrentPageStatus() {
        return mRPageStatusHelp.getCurrentPageStatus();
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
        RPageStatusUtils.checkAddContentStatusPage(pageStatus);
        RPageStatusLayoutInfo rPageStatusLayoutInfo = new RPageStatusLayoutInfo(pageStatus, layoutId, RPageStatusEvent.NO_CLICK);
        mRPageStatusLayoutArray.put(pageStatus, rPageStatusLayoutInfo);
        return this;
    }

    /**
     * 增加状态页面布局，点击时会将页面修改为 {@link RPageStatus#LOADING} 状态，
     * 如果不需要修改，请调用 {@link #addPageStatusView(int, int, int, boolean, OnRPageEventListener)} 方法修改
     *
     * @param pageStatus           页面状态 {@link RPageStatus}
     * @param layoutId             布局资源id
     * @param viewId               布局文件中有点击事件的View的id
     * @param onRPageEventListener 点击事件回调监听
     * @return
     */
    @Override
    public RPageStatusController addPageStatusView(int pageStatus, int layoutId, int viewId, @Nullable OnRPageEventListener onRPageEventListener) {
        return addPageStatusView(pageStatus, layoutId, viewId, true, onRPageEventListener);
    }


    /**
     * 增加状态页面布局
     *
     * @param pageStatus           页面状态 {@link RPageStatus}
     * @param layoutId             布局资源id
     * @param viewId               布局文件中有点击事件的View的id
     * @param showLoading          点击时是否自动显示成 {@link RPageStatus#LOADING} 状态
     * @param onRPageEventListener 点击事件回调监听
     * @return
     */
    @Override
    public RPageStatusController addPageStatusView(int pageStatus, int layoutId, int viewId, boolean showLoading, @Nullable OnRPageEventListener onRPageEventListener) {
        RPageStatusUtils.checkAddContentStatusPage(pageStatus);
        RPageStatusLayoutInfo rPageStatusLayoutInfo = new RPageStatusLayoutInfo(pageStatus, layoutId, RPageStatusEvent.SINGLE_VIEW_CLICK, viewId, showLoading, onRPageEventListener);
        mRPageStatusLayoutArray.put(pageStatus, rPageStatusLayoutInfo);
        return this;
    }

    /**
     * 增加状态页面布局，点击时会将页面修改为 {@link RPageStatus#LOADING} 状态，
     * 如果不需要修改，请调用 {@link #addPageStatusView(int, int, int[], boolean, OnRPageEventListener)} 方法修改
     *
     * @param pageStatus           页面状态 {@link RPageStatus}
     * @param layoutId             布局资源id
     * @param viewIds              布局文件中有点击事件的View的id集合
     * @param onRPageEventListener 点击事件回调监听
     * @return
     */
    @Override
    public RPageStatusController addPageStatusView(int pageStatus, int layoutId, int[] viewIds, @Nullable OnRPageEventListener onRPageEventListener) {
        return addPageStatusView(pageStatus, layoutId, viewIds, true, onRPageEventListener);
    }


    /**
     * 增加状态页面布局
     *
     * @param pageStatus           页面状态 {@link RPageStatus}
     * @param layoutId             布局资源id
     * @param viewIds              布局文件中有点击事件的View的id集合
     * @param showLoading          点击时是否自动显示成 {@link RPageStatus#LOADING} 状态
     * @param onRPageEventListener 点击事件回调监听
     * @return
     */
    @Override
    public RPageStatusController addPageStatusView(int pageStatus, int layoutId, int[] viewIds, boolean showLoading, @Nullable OnRPageEventListener onRPageEventListener) {
        RPageStatusUtils.checkAddContentStatusPage(pageStatus);
        RPageStatusLayoutInfo rPageStatusLayoutInfo = new RPageStatusLayoutInfo(pageStatus, layoutId, RPageStatusEvent.MORE_VIEW_CLICK, viewIds, showLoading, onRPageEventListener);
        mRPageStatusLayoutArray.put(pageStatus, rPageStatusLayoutInfo);
        return this;
    }
}

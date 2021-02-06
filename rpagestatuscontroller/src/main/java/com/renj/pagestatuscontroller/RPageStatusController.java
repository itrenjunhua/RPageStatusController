package com.renj.pagestatuscontroller;

import android.app.Activity;
import android.support.annotation.IdRes;
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
import com.renj.pagestatuscontroller.listener.OnRPageInflateFinishListener;
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
     * {@inheritDoc}
     */
    @Override
    public RPageStatusController addPageStatusView(@RPageStatus int pageStatus, @LayoutRes int layoutId) {
        RPageStatusUtils.checkAddContentStatusPage(pageStatus);
        RPageStatusLayoutInfo rPageStatusLayoutInfo = new RPageStatusLayoutInfo(pageStatus, layoutId, RPageStatusEvent.NO_CLICK);
        mRPageStatusLayoutArray.put(pageStatus, rPageStatusLayoutInfo);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RPageStatusController registerOnRPageEventListener(int pageStatus, @IdRes int viewId,
                                                              @Nullable OnRPageEventListener onRPageEventListener) {
        return registerOnRPageEventListener(pageStatus, true, viewId, onRPageEventListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RPageStatusController registerOnRPageEventListener(int pageStatus, boolean showLoading, @IdRes int viewId,
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
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RPageStatusController registerOnRPageEventListener(int pageStatus, @IdRes int[] viewIds,
                                                              @Nullable OnRPageEventListener onRPageEventListener) {
        return registerOnRPageEventListener(pageStatus, true, viewIds, onRPageEventListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RPageStatusController registerOnRPageEventListener(int pageStatus, boolean showLoading, @IdRes int[] viewIds,
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
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind(@NonNull Activity activity) {
        RPageStatusUtils.checkBindingStatus(mRPageStatusHelp);
        RPageStatusUtils.checkParams(activity);
        ViewGroup contentView = activity.findViewById(android.R.id.content);
        if (RPageStatusUtils.isNull(contentView))
            throw new IllegalStateException("RPageStatusController Exception: bind activity failed: cannot find contentView.");
        View targetView = null;
        if (contentView.getChildCount() > 0)
            targetView = contentView.getChildAt(0);
        if (RPageStatusUtils.isNull(targetView))
            throw new IllegalStateException("RPageStatusController Exception: bind activity failed: bind() method should be after the setContentView() method.");
        mRPageStatusHelp = new RPageStatusHelp(activity, this, activity, contentView, targetView);
        mRPageStatusHelp.bindActivity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View bind(@NonNull Fragment fragment, @NonNull View view) {
        RPageStatusUtils.checkBindingStatus(mRPageStatusHelp);
        RPageStatusUtils.checkParams(fragment, view);
        mRPageStatusHelp = new RPageStatusHelp(fragment.getActivity(), this, fragment, null, view);
        return mRPageStatusHelp.bindFragmentSupport();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View bind(@NonNull android.app.Fragment fragment, @NonNull View view) {
        RPageStatusUtils.checkBindingStatus(mRPageStatusHelp);
        RPageStatusUtils.checkParams(fragment, view);
        mRPageStatusHelp = new RPageStatusHelp(fragment.getActivity(), this, fragment, null, view);
        return mRPageStatusHelp.bindFragment();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bind(@NonNull View view) {
        RPageStatusUtils.checkBindingStatus(mRPageStatusHelp);
        RPageStatusUtils.checkParams(view);
        ViewGroup parentView = (ViewGroup) view.getParent();
        if (RPageStatusUtils.isNull(parentView))
            throw new IllegalStateException("RPageStatusController Exception: bind view failed: cannot find parent view.");
        mRPageStatusHelp = new RPageStatusHelp(view.getContext(), this, view, parentView, view);
        mRPageStatusHelp.bindView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changePageStatus(@RPageStatus int pageStatus) {
        if (RPageStatusUtils.isNull(mRPageStatusHelp))
            throw new IllegalStateException("RPageStatusController Exception: nothing bind.please call bind() method.");

        if (RPageStatus.CONTENT != pageStatus) {
            RPageStatusLayoutInfo rPageStatusLayoutInfo = mRPageStatusLayoutArray.get(pageStatus, null);
            if (RPageStatusUtils.isNull(rPageStatusLayoutInfo) || rPageStatusLayoutInfo.layoutId == 0)
                throw new IllegalStateException("RPageStatusController Exception: No layout resources are set for the \"" + pageStatus + "\" state.");
        }

        mRPageStatusHelp.changePageStatus(pageStatus, mRPageStatusLayoutArray);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RPageStatusController registerOnRPageInflateFinishListener(int pageStatus, OnRPageInflateFinishListener onRPageInflateFinishListener) {
        RPageStatusLayoutInfo rPageStatusLayoutInfo = mRPageStatusLayoutArray.get(pageStatus);
        if (!RPageStatusUtils.isNull(rPageStatusLayoutInfo, onRPageInflateFinishListener)) {
            rPageStatusLayoutInfo.onRPageInflateFinishListener = onRPageInflateFinishListener;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RPageStatusController goneView(int pageStatus, int[] viewIds) {
        RPageStatusLayoutInfo rPageStatusLayoutInfo = mRPageStatusLayoutArray.get(pageStatus);
        if (!RPageStatusUtils.isNull(rPageStatusLayoutInfo, viewIds)) {
            rPageStatusLayoutInfo.goneViewIds = viewIds;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentPageStatus() {
        return mRPageStatusHelp.getCurrentPageStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View getStatusRootView(@RPageStatus int pageStatus) {
        return mRPageStatusHelp.getStatusRootView(pageStatus);
    }
}

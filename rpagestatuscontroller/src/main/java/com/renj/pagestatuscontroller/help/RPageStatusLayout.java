package com.renj.pagestatuscontroller.help;

import android.content.Context;
import androidx.annotation.NonNull;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;

import com.renj.pagestatuscontroller.IRPageStatusController;
import com.renj.pagestatuscontroller.R;
import com.renj.pagestatuscontroller.annotation.RPageStatus;
import com.renj.pagestatuscontroller.annotation.RPageStatusEvent;
import com.renj.pagestatuscontroller.listener.OnRPageEventListener;
import com.renj.pagestatuscontroller.utils.RPageStatusUtils;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：itrenjunhua@163.com
 * <p>
 * 创建时间：2019-06-20   14:50
 * <p>
 * 描述：页面状态布局控制控件
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RPageStatusLayout extends FrameLayout {
    // 初始化的页面信息
    private SparseArray<ViewStub> mPageStatusViewStubArray = new SparseArray<>();
    private SparseArray<View> mPageStatusViewArray = new SparseArray<>();
    // 绑定信息
    private RPageStatusBindInfo mRPageStatusBindInfo;
    private IRPageStatusController mRPageStatusController;
    private int currentPageStatus = -1;

    public RPageStatusLayout(@NonNull Context context, @NonNull IRPageStatusController irPageStatusController) {
        super(context);
        this.mRPageStatusController = irPageStatusController;
        initView(context);
    }

    private void initView(Context context) {
        View pageStatusView = LayoutInflater.from(context).inflate(R.layout.r_page_status_layout, this, true);
        mPageStatusViewStubArray.put(RPageStatus.LOADING, (ViewStub) pageStatusView.findViewById(R.id.loading_view));
        mPageStatusViewStubArray.put(RPageStatus.EMPTY, (ViewStub) pageStatusView.findViewById(R.id.empty_view));
        mPageStatusViewStubArray.put(RPageStatus.NET_WORK, (ViewStub) pageStatusView.findViewById(R.id.net_work_view));
        mPageStatusViewStubArray.put(RPageStatus.ERROR, (ViewStub) pageStatusView.findViewById(R.id.error_view));
        mPageStatusViewStubArray.put(RPageStatus.UN_KNOWN, (ViewStub) pageStatusView.findViewById(R.id.un_known_view));
    }

    public void bindActivity(@NonNull RPageStatusBindInfo rPageStatusBindInfo) {
        mRPageStatusBindInfo = rPageStatusBindInfo;
        View contentView = rPageStatusBindInfo.targetView;
        ViewGroup.LayoutParams contentViewLayoutParams = contentView.getLayoutParams();
        rPageStatusBindInfo.parentView.removeView(contentView);
        this.addView(contentView);
        rPageStatusBindInfo.parentView.addView(this, contentViewLayoutParams);
    }

    public View bindFragment(@NonNull RPageStatusBindInfo rPageStatusBindInfo) {
        mRPageStatusBindInfo = rPageStatusBindInfo;
        View contentView = rPageStatusBindInfo.targetView;
        ViewGroup.LayoutParams contentViewLayoutParams = contentView.getLayoutParams();
        this.addView(contentView);
        if (!RPageStatusUtils.isNull(contentViewLayoutParams))
            this.setLayoutParams(contentViewLayoutParams);
        return this;
    }


    public View bindFragmentSupport(@NonNull RPageStatusBindInfo rPageStatusBindInfo) {
        mRPageStatusBindInfo = rPageStatusBindInfo;
        View contentView = rPageStatusBindInfo.targetView;
        ViewGroup.LayoutParams contentViewLayoutParams = contentView.getLayoutParams();
        this.addView(contentView);
        if (!RPageStatusUtils.isNull(contentViewLayoutParams))
            this.setLayoutParams(contentViewLayoutParams);
        return this;
    }

    public void bindView(@NonNull RPageStatusBindInfo rPageStatusBindInfo) {
        mRPageStatusBindInfo = rPageStatusBindInfo;
        View contentView = rPageStatusBindInfo.targetView;
        // 找到目标控件在父控件中的位置
        int targetIndexInParentView = -1;
        int childCount = rPageStatusBindInfo.parentView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (rPageStatusBindInfo.parentView.getChildAt(i) == contentView) {
                targetIndexInParentView = i;
                break;
            }
        }

        if (targetIndexInParentView != -1) {
            ViewGroup.LayoutParams contentViewLayoutParams = contentView.getLayoutParams();
            // 先将 contentView 从原来的位置移除，然后添加到当前控件中，再用当前控件替换 contentView
            rPageStatusBindInfo.parentView.removeViewAt(targetIndexInParentView);
            // 防止使用了weight属性导致高度变为0
            LayoutParams layoutParams = new LayoutParams(contentViewLayoutParams);
            layoutParams.width = LayoutParams.MATCH_PARENT;
            layoutParams.height = LayoutParams.MATCH_PARENT;
            this.addView(contentView, layoutParams);
            rPageStatusBindInfo.parentView.addView(this, targetIndexInParentView, contentViewLayoutParams);
        }
    }

    public void changePageStatus(@RPageStatus int pageStatus, @NonNull SparseArray<RPageStatusLayoutInfo> rPageStatusLayoutInfoSparseArray) {
        final RPageStatusLayoutInfo rPageStatusLayoutInfo = rPageStatusLayoutInfoSparseArray.get(pageStatus, null);
        if (rPageStatusLayoutInfo != null) {
            ViewStub viewStub = mPageStatusViewStubArray.get(pageStatus);
            // 判断 ViewStub 是否已经 inflate() 过
            if (!RPageStatusUtils.isNull(viewStub.getParent())) {
                viewStub.setLayoutResource(rPageStatusLayoutInfo.layoutId);
                View statusView = viewStub.inflate();
                // 各种状态页面根布局
                mPageStatusViewArray.put(pageStatus, statusView);

                if (!RPageStatusUtils.isNull(rPageStatusLayoutInfo.goneViewIds) && rPageStatusLayoutInfo.goneViewIds.length > 0) {
                    for (int goneViewId : rPageStatusLayoutInfo.goneViewIds) {
                        View goneView = statusView.findViewById(goneViewId);
                        if (!RPageStatusUtils.isNull(goneView)) goneView.setVisibility(GONE);
                    }
                }

                // 如果注册了状态页面控件信息回调
                if (rPageStatusLayoutInfo.onRPageInflateFinishListener != null) {
                    rPageStatusLayoutInfo.onRPageInflateFinishListener.onViewInflateFinish(mRPageStatusController, pageStatus, mRPageStatusBindInfo.object, statusView);
                }

                // 如果有事件，增加监听事件
                if (rPageStatusLayoutInfo.rPageStatusEvent != RPageStatusEvent.NO_CLICK) {
                    if (rPageStatusLayoutInfo.rPageStatusEvent == RPageStatusEvent.SINGLE_VIEW_CLICK) {
                        // 一个控件有事件
                        setStatusPageClickEvent(rPageStatusLayoutInfo.onRPageEventListener, pageStatus, rPageStatusLayoutInfoSparseArray,
                                statusView, rPageStatusLayoutInfo.viewId, rPageStatusLayoutInfo.showLoading);
                    } else if (rPageStatusLayoutInfo.rPageStatusEvent == RPageStatusEvent.MORE_VIEW_CLICK) {
                        // 多个控件有事件
                        for (int viewId : rPageStatusLayoutInfo.viewIds) {
                            setStatusPageClickEvent(rPageStatusLayoutInfo.onRPageEventListener, pageStatus, rPageStatusLayoutInfoSparseArray,
                                    statusView, viewId, rPageStatusLayoutInfo.showLoading);
                        }
                    }
                }
            }
        }

        changeShowPage(pageStatus);
    }

    private void setStatusPageClickEvent(final OnRPageEventListener onRPageEventListener, final int pageStatus,
                                         final @NonNull SparseArray<RPageStatusLayoutInfo> rPageStatusLayoutInfoSparseArray,
                                         View statusView, final int viewId, final boolean showLoading) {
        final View clickView = statusView.findViewById(viewId);
        if (!RPageStatusUtils.isNull(clickView)) {
            clickView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (showLoading)
                        changePageStatus(RPageStatus.LOADING, rPageStatusLayoutInfoSparseArray);
                    if (onRPageEventListener != null)
                        onRPageEventListener.onViewClick(mRPageStatusController, pageStatus, mRPageStatusBindInfo.object, clickView, viewId);
                }
            });
        }
    }

    private void changeShowPage(@RPageStatus int pageStatus) {
        if (currentPageStatus == pageStatus) return;
        currentPageStatus = pageStatus;

        ViewStub loadingViewStub = mPageStatusViewStubArray.get(RPageStatus.LOADING);
        ViewStub emptyViewStub = mPageStatusViewStubArray.get(RPageStatus.EMPTY);
        ViewStub netWorkViewStub = mPageStatusViewStubArray.get(RPageStatus.NET_WORK);
        ViewStub errorViewStub = mPageStatusViewStubArray.get(RPageStatus.ERROR);
        ViewStub notFoundViewStub = mPageStatusViewStubArray.get(RPageStatus.UN_KNOWN);

        if (pageStatus == RPageStatus.LOADING)
            loadingViewStub.setVisibility(VISIBLE);
        else
            loadingViewStub.setVisibility(GONE);

        if (pageStatus == RPageStatus.EMPTY)
            emptyViewStub.setVisibility(VISIBLE);
        else
            emptyViewStub.setVisibility(GONE);

        if (pageStatus == RPageStatus.CONTENT)
            mRPageStatusBindInfo.targetView.setVisibility(VISIBLE);
        else
            mRPageStatusBindInfo.targetView.setVisibility(GONE);

        if (pageStatus == RPageStatus.NET_WORK)
            netWorkViewStub.setVisibility(VISIBLE);
        else
            netWorkViewStub.setVisibility(GONE);

        if (pageStatus == RPageStatus.ERROR)
            errorViewStub.setVisibility(VISIBLE);
        else
            errorViewStub.setVisibility(GONE);

        if (pageStatus == RPageStatus.UN_KNOWN)
            notFoundViewStub.setVisibility(VISIBLE);
        else
            notFoundViewStub.setVisibility(GONE);
    }

    public int getCurrentPageStatus() {
        return currentPageStatus;
    }

    /**
     * 获取指定状态的根布局。注意：需要在 {@link #changePageStatus(int, SparseArray)} 之后才有数据
     *
     * @param pageStatus 指定状态
     */
    public View getStatusRootView(@RPageStatus int pageStatus) {
        return mPageStatusViewArray.get(pageStatus);
    }
}

package com.renj.pagestatuscontroller.help;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.widget.FrameLayout;

import com.renj.pagestatuscontroller.R;
import com.renj.pagestatuscontroller.annotation.RPageStatus;
import com.renj.pagestatuscontroller.utils.RPageStatusUtils;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：itrenjunhua@163.com
 * <p>
 * 创建时间：2019-06-20   14:50
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RPageStatusLayout extends FrameLayout {
    // 初始化的页面信息
    private SparseArray<ViewStub> mPageStatusViewArray = new SparseArray<>();
    // 内容View
    private View contentView;

    public RPageStatusLayout(@NonNull Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        View pageStatusView = LayoutInflater.from(context).inflate(R.layout.r_page_status_layout, this, false);
        mPageStatusViewArray.put(RPageStatus.LOADING, (ViewStub) pageStatusView.findViewById(R.id.loading_view));
        mPageStatusViewArray.put(RPageStatus.EMPTY, (ViewStub) pageStatusView.findViewById(R.id.empty_view));
        mPageStatusViewArray.put(RPageStatus.NET_WORK, (ViewStub) pageStatusView.findViewById(R.id.net_work_view));
        mPageStatusViewArray.put(RPageStatus.ERROR, (ViewStub) pageStatusView.findViewById(R.id.error_view));
        mPageStatusViewArray.put(RPageStatus.NOT_FOUND, (ViewStub) pageStatusView.findViewById(R.id.not_found_view));

        // 获取目标View的布局参数，并设置给当前 View
//        ViewGroup.LayoutParams layoutParams = mRPageStatusBindInfo.targetView.getLayoutParams();
//        this.setLayoutParams(layoutParams);
    }

    public void bindActivity(@NonNull RPageStatusBindInfo rPageStatusBindInfo) {
        contentView = rPageStatusBindInfo.targetView;
    }

    public View bindFragment(@NonNull RPageStatusBindInfo rPageStatusBindInfo) {
        contentView = rPageStatusBindInfo.targetView;
        return this;
    }


    public View bindFragmentSupport(@NonNull RPageStatusBindInfo rPageStatusBindInfo) {
        contentView = rPageStatusBindInfo.targetView;
        return this;
    }

    public void bindView(@NonNull RPageStatusBindInfo rPageStatusBindInfo) {
        contentView = rPageStatusBindInfo.targetView;
        // 找到目标控件在父控件中的位置
        ViewParent targetParent = contentView.getParent();
        if (targetParent != null && (targetParent instanceof ViewGroup)) {
            int targetIndexInParentView = -1;
            ViewGroup targetParentView = (ViewGroup) targetParent;
            int childCount = targetParentView.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (targetParentView.getChildAt(i) == contentView) {
                    targetIndexInParentView = i;
                    break;
                }
            }

            if (targetIndexInParentView != -1) {
                // 将 contentView 添加到当前控件，然后用当前控件替换 contentView
                this.addView(contentView);
                targetParentView.removeViewAt(targetIndexInParentView);
                targetParentView.addView(this, targetIndexInParentView, contentView.getLayoutParams());
            }
        }
    }

    public void changePageStatus(@RPageStatus int pageStatus, @NonNull SparseArray<RPageStatusLayoutInfo> rPageStatusLayoutInfoSparseArray) {
        RPageStatusLayoutInfo rPageStatusLayoutInfo = rPageStatusLayoutInfoSparseArray.get(pageStatus, null);
        if (rPageStatusLayoutInfo != null) {
            ViewStub viewStub = mPageStatusViewArray.get(pageStatus);
            // 判断 ViewStub 是否已经 inflate() 过
            if (!RPageStatusUtils.isNull(viewStub.getParent())) {
                viewStub.setLayoutResource(rPageStatusLayoutInfo.layoutId);
                viewStub.inflate();
            }
        }

        changeShowPage(pageStatus);
    }

    private void changeShowPage(@RPageStatus int pageStatus) {
        ViewStub loadingViewStub = mPageStatusViewArray.get(RPageStatus.LOADING);
        ViewStub emptyViewStub = mPageStatusViewArray.get(RPageStatus.EMPTY);
        ViewStub netWorkViewStub = mPageStatusViewArray.get(RPageStatus.NET_WORK);
        ViewStub errorViewStub = mPageStatusViewArray.get(RPageStatus.ERROR);
        ViewStub notFoundViewStub = mPageStatusViewArray.get(RPageStatus.NOT_FOUND);

        if (pageStatus == RPageStatus.LOADING)
            loadingViewStub.setVisibility(VISIBLE);
        else
            loadingViewStub.setVisibility(GONE);

        if (pageStatus == RPageStatus.EMPTY)
            emptyViewStub.setVisibility(VISIBLE);
        else
            emptyViewStub.setVisibility(GONE);

        if (pageStatus == RPageStatus.CONTENT)
            contentView.setVisibility(VISIBLE);
        else
            contentView.setVisibility(GONE);

        if (pageStatus == RPageStatus.NET_WORK)
            netWorkViewStub.setVisibility(VISIBLE);
        else
            netWorkViewStub.setVisibility(GONE);

        if (pageStatus == RPageStatus.ERROR)
            errorViewStub.setVisibility(VISIBLE);
        else
            errorViewStub.setVisibility(GONE);

        if (pageStatus == RPageStatus.NOT_FOUND)
            notFoundViewStub.setVisibility(VISIBLE);
        else
            notFoundViewStub.setVisibility(GONE);
    }
}

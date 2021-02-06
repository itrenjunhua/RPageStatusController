package com.renj.pagestatuscontroller;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import com.renj.pagestatuscontroller.annotation.RPageStatus;
import com.renj.pagestatuscontroller.listener.OnRPageInflateFinishListener;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：itrenjunhua@163.com
 * <p>
 * 创建时间：2019-06-20   14:44
 * <p>
 * 描述：页面状态控制接口
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public interface IRPageStatusController<T extends IRPageStatusController> extends IRPageStatusConfig<T> {
    /**
     * 绑定 {@link Activity}
     *
     * @param activity {@link Activity}
     * @return
     */
    void bind(@NonNull Activity activity);

    /**
     * 绑定 {@link Fragment}
     *
     * @param fragment {@link Fragment}
     * @param view     {@link Fragment}根View
     * @return
     */
    View bind(@NonNull Fragment fragment, @NonNull View view);

    /**
     * 绑定 {@link android.app.Fragment}
     *
     * @param fragment {@link android.app.Fragment}
     * @param view     {@link android.app.Fragment}根View
     * @return
     */
    View bind(@NonNull android.app.Fragment fragment, @NonNull View view);

    /**
     * 绑定到 {@link View}
     *
     * @param view {@link View}
     * @return
     */
    void bind(@NonNull View view);

    /**
     * 修改页面状态
     *
     * @param pageStatus 状态值
     */
    void changePageStatus(@RPageStatus int pageStatus);

    /**
     * 注册状态页面的布局监听，可以在回调中获取到状态页面控件信息。
     * 主要针对部分特殊页面和全局的状态页面只有细微差别，避免全部重置页面的麻烦，
     * 可以在这个回调内做对应处理（比如对子控件做显示、隐藏或者修改内容操作）<br/>
     * <b>特别注意：<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     * 1. 调用该方法之前需要已经调用过 {@link #addPageStatusView(int, int)} 方法添加了该状态的状态布局，否则无效<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     * 2. 调用该方法之前还没有调用过 {@link IRPageStatusController#changePageStatus(int)} 方法设置过该状态，否则无效<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     * 3. 多次调用该方法，后一次调用会覆盖前一次调用，最终生效的是最后一次结果
     * （离调用 {@link IRPageStatusController#changePageStatus(int)} 方法前面最近的一次）
     * </b>
     *
     * @param pageStatus            页面状态
     * @param onRPageInflateFinishListener 回调
     * @return
     */
    T registerOnRPageInflateFinishListener(@RPageStatus int pageStatus, OnRPageInflateFinishListener onRPageInflateFinishListener);

    /**
     * 隐藏(GONE 状态)某一状态下的部分控件，该方法是 {@link #registerOnRPageInflateFinishListener(int, OnRPageInflateFinishListener)} 特殊情况的简单实现<br/>
     * <b>生效条件：<br/>
     * 1.配置了该状态（调用了相关方法，设置了全局或独立的该状态的状态页面）<br/>
     * 2.还没有调用过 {@link #changePageStatus(int)} 方法设置该状态</b>
     *
     * @param pageStatus 页面状态
     * @param viewIds    需要隐藏的控件ID
     * @return
     */
    T goneView(@RPageStatus int pageStatus, @IdRes int[] viewIds);

    /**
     * 获取当前的页面状态
     *
     * @return {@link RPageStatus}
     */
    int getCurrentPageStatus();

    /**
     * 获取指定状态的根布局。注意：需要在 {@link #changePageStatus(int)} 之后才有数据
     *
     * @param pageStatus 指定状态 {@link RPageStatus}
     */
    View getStatusRootView(@RPageStatus int pageStatus);
}

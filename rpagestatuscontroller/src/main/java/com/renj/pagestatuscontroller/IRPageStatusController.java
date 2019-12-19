package com.renj.pagestatuscontroller;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import com.renj.pagestatuscontroller.annotation.RPageStatus;
import com.renj.pagestatuscontroller.listener.OnRPageEventListener;
import com.renj.pagestatuscontroller.listener.OnRPageViewListener;

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
     * 注册某一个状态页面布局监听，可以在回调中获取到状态页面信息，获取到子控件并显示、隐藏或者修改子控件内容）<br/>
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
     * @param pageStatus          页面状态
     * @param onRPageViewListener 回调
     * @return
     */
    T registerOnRPageViewListener(@RPageStatus int pageStatus, OnRPageViewListener onRPageViewListener);

    /**
     * 获取当前的页面状态
     *
     * @return {@link RPageStatus}
     */
    int getCurrentPageStatus();
}

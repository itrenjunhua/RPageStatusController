package com.renj.pagestatuscontroller;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import com.renj.pagestatuscontroller.annotation.RPageStatus;
import com.renj.pagestatuscontroller.listener.OnRPageEventListener;

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
     * 重置某一状态的事件监听，点击时会将页面修改为 {@link RPageStatus#LOADING} 状态，
     * 如果不需要修改，请调用 {@link #resetOnRPageEventListener(int, boolean, OnRPageEventListener)} 修改<br/>
     * <b>生效条件：<br/>
     * 1.配置了该状态（调用了相关方法，设置了全局或独立的该状态的状态页面）<br/>
     * 2.还没有调用过 {@link #changePageStatus(int)} 方法设置该状态</b>
     *
     * @param pageStatus           需要重置监听事件的状态
     * @param onRPageEventListener 监听事件对象
     * @return
     */
    T resetOnRPageEventListener(@RPageStatus int pageStatus, OnRPageEventListener onRPageEventListener);

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
    T resetOnRPageEventListener(@RPageStatus int pageStatus, boolean showLoading, OnRPageEventListener onRPageEventListener);

    /**
     * 修改页面状态
     *
     * @param pageStatus 状态值
     */
    void changePageStatus(@RPageStatus int pageStatus);

    /**
     * 获取当前的页面状态
     *
     * @return
     */
    int getCurrentPageStatus();
}

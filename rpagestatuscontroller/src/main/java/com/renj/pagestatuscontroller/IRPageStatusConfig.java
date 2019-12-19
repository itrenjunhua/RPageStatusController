package com.renj.pagestatuscontroller;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.renj.pagestatuscontroller.annotation.RPageStatus;
import com.renj.pagestatuscontroller.listener.OnRPageEventListener;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：itrenjunhua@163.com
 * <p>
 * 创建时间：2019-06-20   14:43
 * <p>
 * 描述：增加配置信息接口
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public interface IRPageStatusConfig<T extends IRPageStatusConfig> {
    /**
     * 增加状态页面布局
     *
     * @param pageStatus 页面状态 {@link RPageStatus}
     * @param layoutId   布局资源id
     * @return
     */
    T addPageStatusView(@RPageStatus int pageStatus, @LayoutRes int layoutId);

    /**
     * 给指定状态页面(单个)控件注册点击事件，默认点击时会将页面修改为 {@link RPageStatus#LOADING} 状态，
     * 如果不需要修改，请使用 {@link #registerOnRPageEventListener(int, boolean, int, OnRPageEventListener)} 方法。<br/>
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
     * @param pageStatus           页面状态 {@link RPageStatus}
     * @param viewId               布局文件中有点击事件的View的id
     * @param onRPageEventListener 点击事件回调监听
     * @return
     * @see #registerOnRPageEventListener(int, boolean, int, OnRPageEventListener)
     * @see #registerOnRPageEventListener(int, int[], OnRPageEventListener)
     * @see #registerOnRPageEventListener(int, boolean, int[], OnRPageEventListener)
     */
    T registerOnRPageEventListener(@RPageStatus int pageStatus, @IdRes int viewId,
                                   @Nullable OnRPageEventListener onRPageEventListener);

    /**
     * 给指定状态页面(单个)控件注册点击事件。<br/>
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
     * @param pageStatus           页面状态 {@link RPageStatus}
     * @param showLoading          点击时是否自动显示成 {@link RPageStatus#LOADING} 状态
     * @param viewId               布局文件中有点击事件的View的id
     * @param onRPageEventListener 点击事件回调监听
     * @return
     * @see #registerOnRPageEventListener(int, int, OnRPageEventListener)
     * @see #registerOnRPageEventListener(int, int[], OnRPageEventListener)
     * @see #registerOnRPageEventListener(int, boolean, int[], OnRPageEventListener)
     */
    T registerOnRPageEventListener(@RPageStatus int pageStatus,
                                   boolean showLoading, @IdRes int viewId,
                                   @Nullable OnRPageEventListener onRPageEventListener);


    /**
     * 给指定状态页面(多个)控件注册点击事件，默认点击时会将页面修改为 {@link RPageStatus#LOADING} 状态，
     * 如果不需要修改，请使用 {@link #registerOnRPageEventListener(int, boolean, int[], OnRPageEventListener)} 方法。<br/>
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
     * @param pageStatus           页面状态 {@link RPageStatus}
     * @param viewIds              布局文件中有点击事件的View的id集合
     * @param onRPageEventListener 点击事件回调监听
     * @return
     * @see #registerOnRPageEventListener(int, int, OnRPageEventListener)
     * @see #registerOnRPageEventListener(int, boolean, int, OnRPageEventListener)
     * @see #registerOnRPageEventListener(int, boolean, int[], OnRPageEventListener)
     */
    T registerOnRPageEventListener(@RPageStatus int pageStatus, @IdRes int[] viewIds,
                                   @Nullable OnRPageEventListener onRPageEventListener);

    /**
     * 给指定状态页面(多个)控件注册点击事件。<br/>
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
     * @param pageStatus           页面状态 {@link RPageStatus}
     * @param viewIds              布局文件中有点击事件的View的id集合
     * @param showLoading          点击时是否自动显示成 {@link RPageStatus#LOADING} 状态
     * @param onRPageEventListener 点击事件回调监听
     * @return
     * @see #registerOnRPageEventListener(int, int, OnRPageEventListener)
     * @see #registerOnRPageEventListener(int, boolean, int, OnRPageEventListener)
     * @see #registerOnRPageEventListener(int, int[], OnRPageEventListener)
     */
    T registerOnRPageEventListener(@RPageStatus int pageStatus, boolean showLoading,
                                   @IdRes int[] viewIds, @Nullable OnRPageEventListener onRPageEventListener);
}

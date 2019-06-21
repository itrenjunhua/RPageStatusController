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
 * 邮箱：renjunhua@anlovek.com
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
     * 增加状态页面布局
     *
     * @param pageStatus          页面状态 {@link RPageStatus}
     * @param layoutId            布局资源id
     * @param viewId              布局文件中有点击事件的View的id
     * @param onRPageEventListener 点击事件回调监听
     * @return
     */
    T addPageStatusView(@RPageStatus int pageStatus, @LayoutRes int layoutId, @IdRes int viewId, @Nullable OnRPageEventListener onRPageEventListener);


    /**
     * 增加状态页面布局
     *
     * @param pageStatus          页面状态 {@link RPageStatus}
     * @param layoutId            布局资源id
     * @param viewIds             布局文件中有点击事件的View的id集合
     * @param onRPageEventListener 点击事件回调监听
     * @return
     */
    T addPageStatusView(@RPageStatus int pageStatus, @LayoutRes int layoutId, @IdRes int[] viewIds, @Nullable OnRPageEventListener onRPageEventListener);
}

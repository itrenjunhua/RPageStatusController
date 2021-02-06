package com.renj.pagestatuscontroller.listener;

import android.support.annotation.NonNull;
import android.view.View;

import com.renj.pagestatuscontroller.IRPageStatusController;
import com.renj.pagestatuscontroller.annotation.RPageStatus;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2019-12-17   22:58
 * <p>
 * 描述：状态页面布局完成回调
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public interface OnRPageInflateFinishListener {
    /**
     * 获取当前状态页面的控件信息
     *
     * @param iRPageStatusController {@link IRPageStatusController} 对象
     * @param pageStatus             当前状态
     * @param object                 绑定的对象（调用bind()方法所传的参数；Activity、Fragment、View）
     * @param statusRootView         当前状态页面的根View
     */
    void onViewInflateFinish(@NonNull IRPageStatusController iRPageStatusController,
                             @RPageStatus int pageStatus,
                             @NonNull Object object, View statusRootView);
}

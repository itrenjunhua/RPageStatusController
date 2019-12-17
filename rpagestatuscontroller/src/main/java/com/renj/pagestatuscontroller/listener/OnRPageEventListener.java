package com.renj.pagestatuscontroller.listener;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;

import com.renj.pagestatuscontroller.IRPageStatusController;
import com.renj.pagestatuscontroller.annotation.RPageStatus;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：itrenjunhua@163.com
 * <p>
 * 创建时间：2019-06-20   14:44
 * <p>
 * 描述：事件监听回调接口
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public interface OnRPageEventListener {
    /**
     * 状态页面控件点击回调
     *
     * @param iRPageStatusController {@link IRPageStatusController} 对象
     * @param pageStatus             当前状态
     * @param object                 绑定的对象（调用bind()方法所传的参数；Activity、Fragment、View）
     * @param view                   被点击的控件
     * @param viewId                 被点击的控件ID
     */
    void onViewClick(@NonNull IRPageStatusController iRPageStatusController, @RPageStatus int pageStatus,
                     @NonNull Object object, @NonNull View view, @IdRes int viewId);
}

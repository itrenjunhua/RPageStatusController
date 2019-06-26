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
    void onViewClick(@NonNull IRPageStatusController iRPageStatusController, @RPageStatus int pageStatus,
                     @NonNull Object object, @NonNull View view, @IdRes int viewId);
}

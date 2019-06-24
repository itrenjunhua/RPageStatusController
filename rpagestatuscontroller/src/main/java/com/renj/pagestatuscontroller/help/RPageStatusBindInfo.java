package com.renj.pagestatuscontroller.help;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：itrenjunhua@163.com
 * <p>
 * 创建时间：2019-06-20   17:27
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
/*public*/ class RPageStatusBindInfo {
    Object object;
    /**
     * 当object为Fragment时，可以为null，其他的情况不能为null
     */
    ViewGroup parentView;
    View targetView;

    RPageStatusBindInfo(@NonNull Object object, @Nullable ViewGroup parentView, @NonNull View targetView) {
        this.object = object;
        this.parentView = parentView;
        this.targetView = targetView;
    }
}

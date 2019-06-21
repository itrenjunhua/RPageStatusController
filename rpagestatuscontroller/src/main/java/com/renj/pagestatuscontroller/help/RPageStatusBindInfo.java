package com.renj.pagestatuscontroller.help;

import android.support.annotation.NonNull;
import android.view.View;

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
    View targetView;

    RPageStatusBindInfo(@NonNull Object object, @NonNull View targetView) {
        this.object = object;
        this.targetView = targetView;
    }
}

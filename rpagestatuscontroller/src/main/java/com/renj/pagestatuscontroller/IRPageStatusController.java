package com.renj.pagestatuscontroller;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import com.renj.pagestatuscontroller.annotation.RPageStatus;

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
}

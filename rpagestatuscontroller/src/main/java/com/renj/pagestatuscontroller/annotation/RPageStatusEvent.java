package com.renj.pagestatuscontroller.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：itrenjunhua@163.com
 * <p>
 * 创建时间：2019-06-20   16:37
 * <p>
 * 描述：状态页面点击事件记录
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({
        RPageStatusEvent.NO_CLICK,
        RPageStatusEvent.SINGLE_VIEW_CLICK,
        RPageStatusEvent.MORE_VIEW_CLICK,
})
public @interface RPageStatusEvent {
    /**
     * 没有点击事件
     */
    int NO_CLICK = 0;
    /**
     * 一个View有点击事件
     */
    int SINGLE_VIEW_CLICK = 1;
    /**
     * 多个View有点击事件
     */
    int MORE_VIEW_CLICK = 2;
}

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
 * 创建时间：2019-06-20   14:42
 * <p>
 * 描述：页面状态注解
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({
        RPageStatus.LOADING,
        RPageStatus.EMPTY,
        RPageStatus.CONTENT,
        RPageStatus.NET_WORK,
        RPageStatus.ERROR,
        RPageStatus.UN_KNOWN,
})
public @interface RPageStatus {
    /**
     * 加载中状态
     */
    int LOADING = 100;
    /**
     * 空状态
     */
    int EMPTY = 101;
    /**
     * 内容状态
     */
    int CONTENT = 102;
    /**
     * 网络错误状态
     */
    int NET_WORK = 103;
    /**
     * 加载错误状态
     */
    int ERROR = 104;
    /**
     * 未知状态
     */
    int UN_KNOWN = 105;
}

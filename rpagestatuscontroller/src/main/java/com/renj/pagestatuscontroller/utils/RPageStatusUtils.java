package com.renj.pagestatuscontroller.utils;

import com.renj.pagestatuscontroller.annotation.RPageStatus;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：renjunhua@anlovek.com
 * <p>
 * 创建时间：2019-06-20   14:46
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RPageStatusUtils {
    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNull(Object... objects) {
        if (objects == null) return true;

        for (Object object : objects) {
            if (isNull(object))
                return true;
        }
        return false;
    }

    public static void checkParams(Object object) {
        if (isNull(object)) {
            throw new IllegalArgumentException("The parameter cannot be null.");
        }
    }

    public static void checkParams(Object... objects) {
        if (isNull(objects)) {
            throw new IllegalArgumentException("The parameter cannot be null.");
        }
    }

    public static void checkAddContentStatusPage(@RPageStatus int pageStatusValue) {
        if (pageStatusValue == RPageStatus.CONTENT) {
            throw new IllegalArgumentException("Cannot add " + pageStatusValue + " status configuration.");
        }
    }
}

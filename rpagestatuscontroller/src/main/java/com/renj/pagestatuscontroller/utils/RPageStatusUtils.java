package com.renj.pagestatuscontroller.utils;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.renj.pagestatuscontroller.annotation.RPageStatus;
import com.renj.pagestatuscontroller.help.RPageStatusLayoutInfo;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：itrenjunhua@163.com
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

    public static void checkAddContentStatusPage(@RPageStatus int pageStatus) {
        if (pageStatus == RPageStatus.CONTENT) {
            throw new IllegalArgumentException("Cannot add " + pageStatus + " status configuration.");
        }
    }

    public static void copyRPageStatusLayoutInfo(@NonNull SparseArray<RPageStatusLayoutInfo> src, @NonNull SparseArray<RPageStatusLayoutInfo> target) {
        if (src.size() > 0) {
            target.put(RPageStatus.LOADING, src.get(RPageStatus.LOADING, null));
            target.put(RPageStatus.EMPTY, src.get(RPageStatus.EMPTY, null));
            target.put(RPageStatus.NET_WORK, src.get(RPageStatus.NET_WORK, null));
            target.put(RPageStatus.ERROR, src.get(RPageStatus.ERROR, null));
            target.put(RPageStatus.NOT_FOUND, src.get(RPageStatus.NOT_FOUND, null));
        }
    }

    public static void deepCopyRPageStatusLayoutInfo(@NonNull SparseArray<RPageStatusLayoutInfo> src, @NonNull SparseArray<RPageStatusLayoutInfo> target) {
        if (src.size() > 0) {
            RPageStatusLayoutInfo loading = src.get(RPageStatus.LOADING, null);
            if (!isNull(loading))
                target.put(RPageStatus.LOADING, new RPageStatusLayoutInfo(loading));
            RPageStatusLayoutInfo empty = src.get(RPageStatus.EMPTY, null);
            if (!isNull(empty))
                target.put(RPageStatus.EMPTY, new RPageStatusLayoutInfo(empty));
            RPageStatusLayoutInfo netWork = src.get(RPageStatus.NET_WORK, null);
            if (!isNull(netWork))
                target.put(RPageStatus.NET_WORK, new RPageStatusLayoutInfo(netWork));
            RPageStatusLayoutInfo error = src.get(RPageStatus.ERROR, null);
            if (!isNull(error))
                target.put(RPageStatus.ERROR, new RPageStatusLayoutInfo(error));
            RPageStatusLayoutInfo notFound = src.get(RPageStatus.NOT_FOUND, null);
            if (!isNull(notFound))
                target.put(RPageStatus.NOT_FOUND, new RPageStatusLayoutInfo(notFound));
        }
    }
}

package com.renj.test.app;

import android.app.Application;

import com.renj.pagestatuscontroller.RPageStatusManager;
import com.renj.pagestatuscontroller.annotation.RPageStatus;
import com.renj.test.R;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：renjunhua@anlovek.com
 * <p>
 * 创建时间：2019-06-21   17:07
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        RPageStatusManager.getInstance()
                .addPageStatusView(RPageStatus.LOADING, R.layout.status_view_loading)
                .addPageStatusView(RPageStatus.EMPTY, R.layout.status_view_empty)
                .addPageStatusView(RPageStatus.NET_WORK, R.layout.status_view_network)
                .addPageStatusView(RPageStatus.ERROR, R.layout.status_view_error, R.id.tv_error, null);
    }
}

package com.renj.test.app;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.renj.pagestatuscontroller.IRPageStatusController;
import com.renj.pagestatuscontroller.RPageStatusManager;
import com.renj.pagestatuscontroller.annotation.RPageStatus;
import com.renj.pagestatuscontroller.listener.OnRPageEventListener;
import com.renj.test.R;
import com.renj.test.utils.Utils;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：itrenjunhua@163.com
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
    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        RPageStatusManager.getInstance()
                .addPageStatusView(RPageStatus.LOADING, R.layout.status_view_loading)
                .addPageStatusView(RPageStatus.EMPTY, R.layout.status_view_empty)
                .addPageStatusView(RPageStatus.NET_WORK, R.layout.status_view_network, R.id.tv_net_work, null)
                .addPageStatusView(RPageStatus.ERROR, R.layout.status_view_error, R.id.tv_error, new OnRPageEventListener() {
                    @Override
                    public void onViewClick(@NonNull IRPageStatusController iRPageStatusController, @NonNull Object object, @NonNull View view, int viewId) {
                        Utils.showToast("全局配置加载错误监听: " + object);
                        Log.i("MyApplication", "全局配置加载错误监听：" + "object = [" + object + "], view = [" + view + "], viewId = [" + viewId + "]");
                    }
                });
    }

    public static MyApplication getApplication() {
        return application;
    }
}

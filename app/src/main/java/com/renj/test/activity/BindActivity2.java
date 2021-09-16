package com.renj.test.activity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.renj.pagestatuscontroller.IRPageStatusController;
import com.renj.pagestatuscontroller.RPageStatusController;
import com.renj.pagestatuscontroller.annotation.RPageStatus;
import com.renj.pagestatuscontroller.listener.OnRPageEventListener;
import com.renj.pagestatuscontroller.listener.OnRPageInflateFinishListener;
import com.renj.test.R;
import com.renj.test.utils.Utils;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：itrenjunhua@163.com
 * <p>
 * 创建时间：2019-06-24   16:37
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class BindActivity2 extends AppCompatActivity {

    private RPageStatusController rPageStatusController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind2);

        rPageStatusController = RPageStatusController.get();

        rPageStatusController.bind(this);
        rPageStatusController.changePageStatus(RPageStatus.LOADING);

        // 重置事件
        rPageStatusController
                // 使用独立的加载错误页面
                .addPageStatusView(RPageStatus.ERROR, R.layout.status_view_error2)
                .registerOnRPageEventListener(RPageStatus.NET_WORK, R.id.tv_net_work, new OnRPageEventListener() {
                    @Override
                    public void onViewClick(@NonNull IRPageStatusController iRPageStatusController, @RPageStatus int pageStatus, @NonNull Object object, @NonNull View view, int viewId) {
                        Utils.showToast("网络错误 - " + pageStatus);

                        Utils.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                rPageStatusController.changePageStatus(RPageStatus.ERROR);
                            }
                        }, 3000);
                    }
                })
                .registerOnRPageEventListener(RPageStatus.ERROR, false, new int[]{R.id.tv_error, R.id.tv_error2}, new OnRPageEventListener() {
                    @Override
                    public void onViewClick(@NonNull IRPageStatusController iRPageStatusController, @RPageStatus int pageStatus, @NonNull Object object, @NonNull View view, int viewId) {
                        if (viewId == R.id.tv_error2)
                            iRPageStatusController.changePageStatus(RPageStatus.CONTENT);

                        if (viewId == R.id.tv_error) {
                            iRPageStatusController.changePageStatus(RPageStatus.LOADING);
                            Utils.showToast("独立配置加载错误监听: " + object);
                            Utils.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    rPageStatusController.changePageStatus(RPageStatus.CONTENT);
                                }
                            }, 3000);
                        }
                    }
                })
                // 注册状态页面布局监听
                .registerOnRPageInflateFinishListener(RPageStatus.ERROR, new OnRPageInflateFinishListener() {
                    @Override
                    public void onViewInflateFinish(@NonNull IRPageStatusController iRPageStatusController, int pageStatus, @NonNull Object object, View statusRootView) {
                        TextView tvError2 = statusRootView.findViewById(R.id.tv_error2);
                        tvError2.setText("点我吖！！！");
                    }
                });

        Utils.postDelayed(new Runnable() {
            @Override
            public void run() {
                rPageStatusController.changePageStatus(RPageStatus.NET_WORK);
            }
        }, 3000);
    }
}

package com.renj.test.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.renj.pagestatuscontroller.IRPageStatusController;
import com.renj.pagestatuscontroller.RPageStatusController;
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
 * 创建时间：2019-06-24   16:37
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class BindActivity extends AppCompatActivity {

    private RPageStatusController rPageStatusController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);

        rPageStatusController = RPageStatusController.get();

        rPageStatusController.bind(this);
        rPageStatusController.changePageStatus(RPageStatus.LOADING);

        rPageStatusController
                // 重置事件
                .registerOnRPageEventListener(RPageStatus.ERROR, R.id.tv_error, new OnRPageEventListener() {
                    @Override
                    public void onViewClick(@NonNull IRPageStatusController iRPageStatusController, @RPageStatus int pageStatus, @NonNull Object object, @NonNull View view, int viewId) {
                        Utils.showToast("重置全局错误页面事件 - " + pageStatus);

                        Utils.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                rPageStatusController.changePageStatus(RPageStatus.CONTENT);
                            }
                        }, 3000);
                    }
                });

        Utils.postDelayed(new Runnable() {
            @Override
            public void run() {
                rPageStatusController.changePageStatus(RPageStatus.ERROR);
            }
        }, 3000);
    }
}

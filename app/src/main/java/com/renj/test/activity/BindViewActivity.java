package com.renj.test.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.renj.pagestatuscontroller.RPageStatusController;
import com.renj.pagestatuscontroller.annotation.RPageStatus;
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
public class BindViewActivity extends AppCompatActivity {

    private RPageStatusController rPageStatusController1, rPageStatusController2, rPageStatusController3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        rPageStatusController1 = RPageStatusController.get();
        rPageStatusController1.bind(findViewById(R.id.view1));
        rPageStatusController1.changePageStatus(RPageStatus.LOADING);
        Utils.postDelayed(new Runnable() {
            @Override
            public void run() {
                rPageStatusController1.changePageStatus(RPageStatus.CONTENT);
            }
        }, 3200);

        rPageStatusController2 = RPageStatusController.get();
        rPageStatusController2.bind(findViewById(R.id.view2));
        rPageStatusController2.changePageStatus(RPageStatus.LOADING);
        Utils.postDelayed(new Runnable() {
            @Override
            public void run() {
                rPageStatusController2.changePageStatus(RPageStatus.CONTENT);
            }
        }, 3000);

        rPageStatusController3 = RPageStatusController.get();
        rPageStatusController3.bind(findViewById(R.id.view3));
        rPageStatusController3.changePageStatus(RPageStatus.LOADING);
        Utils.postDelayed(new Runnable() {
            @Override
            public void run() {
                rPageStatusController3.changePageStatus(RPageStatus.CONTENT);
            }
        }, 3500);
    }
}

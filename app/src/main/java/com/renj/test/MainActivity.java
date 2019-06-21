package com.renj.test;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.renj.pagestatuscontroller.RPageStatusController;
import com.renj.pagestatuscontroller.annotation.RPageStatus;

public class MainActivity extends AppCompatActivity {

    private RPageStatusController rPageStatusController;
    private View textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rPageStatusController = RPageStatusController.get();

        textView = findViewById(R.id.text_view);

        rPageStatusController.bind(textView);

        rPageStatusController.changePageStatus(RPageStatus.LOADING);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rPageStatusController.changePageStatus(RPageStatus.CONTENT);
            }
        }, 3000);
    }
}

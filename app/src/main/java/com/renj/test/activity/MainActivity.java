package com.renj.test.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.renj.test.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_bind_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(BindActivity.class);
            }
        });

        findViewById(R.id.bt_bind_activity2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(BindActivity2.class);
            }
        });

        findViewById(R.id.bt_bind_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(BindFragmentActivity.class);
            }
        });

        findViewById(R.id.bt_bind_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(BindViewActivity.class);
            }
        });
    }

    private void open(Class clazz) {
        Intent intent = new Intent(MainActivity.this, clazz);
        startActivity(intent);
    }
}

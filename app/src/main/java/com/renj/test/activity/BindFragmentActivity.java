package com.renj.test.activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.renj.test.R;
import com.renj.test.fragment.Fragment1;
import com.renj.test.fragment.Fragment2;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：itrenjunhua@163.com
 * <p>
 * 创建时间：2019-06-24   16:42
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class BindFragmentActivity extends AppCompatActivity {

    private FragmentManager supportFragmentManager;
    private Fragment[] fragments = new Fragment[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        supportFragmentManager = getSupportFragmentManager();
        fragments[0] = Fragment1.newInstance();
        fragments[1] = Fragment2.newInstance();
        supportFragmentManager.beginTransaction()
                .add(R.id.fl_content, fragments[0])
                .add(R.id.fl_content, fragments[1])
                .show(fragments[0])
                .hide(fragments[1])
                .commit();

        findViewById(R.id.bt_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportFragmentManager.beginTransaction()
                        .show(fragments[0])
                        .hide(fragments[1])
                        .commit();

                setTitle(R.string.bt_bind_fragment);
            }
        });

        findViewById(R.id.bt_fragment2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportFragmentManager.beginTransaction()
                        .show(fragments[1])
                        .hide(fragments[0])
                        .commit();
                setTitle(R.string.bt_bind_fragment2);
            }
        });
    }
}

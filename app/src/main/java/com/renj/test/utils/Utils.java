package com.renj.test.utils;

import android.os.Handler;
import android.widget.Toast;

import com.renj.test.app.MyApplication;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：renjunhua@anlovek.com
 * <p>
 * 创建时间：2019-06-24   17:08
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class Utils {
    private static Toast toast;

    public static void showToast(String msg) {
        if (toast == null)
            toast = Toast.makeText(MyApplication.getApplication(), "", Toast.LENGTH_SHORT);
        toast.setText(msg);
        toast.show();
    }

    public static void postDelayed(Runnable r, long delayMillis) {
        new Handler().postDelayed(r, delayMillis);
    }
}

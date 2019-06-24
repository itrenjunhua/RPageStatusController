package com.renj.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.renj.pagestatuscontroller.RPageStatusController;
import com.renj.pagestatuscontroller.annotation.RPageStatus;
import com.renj.pagestatuscontroller.listener.OnRPageEventListener;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * 邮箱：renjunhua@anlovek.com
 * <p>
 * 创建时间：2019-06-24   10:36
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class MyFragment extends Fragment {

    private RPageStatusController rPageStatusController;

    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment, null);

        rPageStatusController = RPageStatusController.get();
        rPageStatusController.resetOnRPageEventListener(RPageStatus.ERROR, new OnRPageEventListener() {
            @Override
            public void onViewClick(@NonNull Object object, @NonNull View view, int viewId) {
                Toast.makeText(MyFragment.this.getContext(), "... tv_error", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rPageStatusController.changePageStatus(RPageStatus.CONTENT);
                    }
                }, 3000);
            }
        });
        View bind = rPageStatusController.bind(this, view);
        rPageStatusController.changePageStatus(RPageStatus.ERROR);
        return bind;
    }
}

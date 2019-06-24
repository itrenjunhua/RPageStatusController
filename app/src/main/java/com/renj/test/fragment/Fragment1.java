package com.renj.test.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
 * 创建时间：2019-06-24   16:54
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class Fragment1 extends Fragment {
    private RPageStatusController rPageStatusController;

    public static Fragment1 newInstance() {
        Bundle args = new Bundle();
        Fragment1 fragment = new Fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, null);
        rPageStatusController = RPageStatusController.get();

        View fragmentView = rPageStatusController.bind(this, view);
        rPageStatusController.changePageStatus(RPageStatus.LOADING);

        Utils.postDelayed(new Runnable() {
            @Override
            public void run() {
                rPageStatusController.changePageStatus(RPageStatus.CONTENT);
            }
        }, 3000);

        // 这里一定要使用 bind() 方法返回的View
        return fragmentView;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            rPageStatusController.changePageStatus(RPageStatus.LOADING);

            Utils.postDelayed(new Runnable() {
                @Override
                public void run() {
                    rPageStatusController.changePageStatus(RPageStatus.CONTENT);
                }
            }, 3000);
        }
    }
}

package com.swipe.demo.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.swipe.demo.activity.BaseActivity;

/**
 * Created by Administrator on 2017/5/11.
 */
public class BaseFragment extends Fragment {
    BaseActivity mActivtiy;
    @Override
    public void onAttach(Activity activity) {
        mActivtiy =(BaseActivity) activity;
        super.onAttach(activity);
    }
}

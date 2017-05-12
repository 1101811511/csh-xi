package com.example.administrator.swipedemo;

import android.app.Activity;
import android.support.v4.app.Fragment;

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

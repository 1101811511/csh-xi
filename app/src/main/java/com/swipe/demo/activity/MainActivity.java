package com.swipe.demo.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.TextView;

import com.swipe.demo.fragment.MainFragment;
import com.swipe.demo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseLoginActivity {

    private MainFragment[] fragments;

    @InjectView(R.id.tabLayout)
   TabLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    protected void initView() {
        ButterKnife.inject(this);
        //获取LayoutInflater 来加载布局
        LayoutInflater inflate = LayoutInflater.from(this);
        fragments = MainFragment.childs();//返回所有的子fragment
        //循环遍历进行展示
        for (int i = 0; i < fragments.length; i++) {
            TextView view = (TextView) inflate.inflate(R.layout.title, null, false);
            view.setCompoundDrawablesRelativeWithIntrinsicBounds(0, fragments[i].getIconRes(), 0, 0);
            view.setText(fragments[i].getTitleRes());
            //源码：Adds a child view. If no layout parameters are already set on the child, the
            //* default parameters for this ViewGroup are set on the child.
            tableLayout.addTab(tableLayout.newTab().setCustomView(view));
        }
        showFragment(fragments[0].getFragment());
        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                showFragment(fragments[tab.getPosition()].getFragment());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    protected void initData() {

    }

    private void showFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        transaction.commitAllowingStateLoss();
    }
}

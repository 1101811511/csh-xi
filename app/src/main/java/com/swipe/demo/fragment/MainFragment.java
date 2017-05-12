package com.swipe.demo.fragment;

import android.support.v4.app.Fragment;

import com.swipe.demo.R;

/**

 * Created by Administrator on 2017/5/10.
 */
public class MainFragment {
    private int titleRes;
    private int iconRes;
    Fragment fragment;

    public MainFragment(int title, int icon, Fragment fragment) {
        this.titleRes =title;
        this.iconRes = icon;
        this.fragment = fragment;
    }

    public static MainFragment[]  childs(){
        MainFragment[] fragments = new MainFragment[4];
        fragments[0] = new MainFragment(R.string.first_tab,R.drawable.tab_item_feed,new FirstFragment());
        fragments[1] = new MainFragment(R.string.new_tab,R.drawable.tab_item_news,new NewsFragment());
        fragments[2] = new MainFragment(R.string.book_tab,R.drawable.tab_item_circle,new BookFragment());
        fragments[3] = new MainFragment(R.string.person_tab,R.drawable.tab_item_mine,new PersonFragment());
        return  fragments;
    }

    public int getTitleRes() {
        return titleRes;
    }

    public void setTitleRes(int titleRes) {
        this.titleRes = titleRes;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }
}




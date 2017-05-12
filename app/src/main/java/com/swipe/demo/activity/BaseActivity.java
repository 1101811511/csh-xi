package com.swipe.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

public abstract class BaseActivity extends RxAppCompatActivity {

    /**
     * 如：Button btn = $(R.id.btn);
     */
    protected <T extends View> T $(int resId) {
        return $(null, resId);
    }

    /**
     * 如：Button btn = $(inflater ,R.id.btn);
     */
    protected <T extends View> T $(View inflater, int resId) {
        if (null == inflater)
            return (T) findViewById(resId);
        else
            return (T) inflater.findViewById(resId);
    }
}

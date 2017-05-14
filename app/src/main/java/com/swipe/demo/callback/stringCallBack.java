package com.swipe.demo.callback;

import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/14.
 */
public abstract class stringCallBack extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response, int id) throws Exception {
        return response.body().toString();
    }
}

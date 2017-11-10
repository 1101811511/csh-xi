package com.swipe.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.swipe.demo.Bean.DateResponseBean;
import com.swipe.demo.Bean.LifeResponse;
import com.swipe.demo.view.LifeAdapter;
import com.swipe.demo.R;
import com.swipe.demo.utils.GsonUtils;
import com.swipe.demo.utils.Logger;
import com.swipe.demo.view.WebAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/5/10.
 */
public class FirstFragment  extends Fragment{
    @InjectView(R.id.life_grid)
    GridView mLifeGrid;
    @InjectView(R.id.web_load)
    GridView mWebLoad;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first,container,false);
        ButterKnife.inject(this,view);
        initView();
        return view;
    }

    private void initView() {
        OkHttpUtils.post().url("https://life.lanchain.com/csh_apps/login/homePage")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DateResponseBean<LifeResponse> responseBean = GsonUtils.fromJson(response, new TypeToken<DateResponseBean<LifeResponse>>(){}.getType());
                        LifeResponse  lifeResponse = responseBean.getResponse();
                        if (responseBean.isSuccess()){
                            Logger.i("++++++++++++++++++++++++firstFragment++++++++++++++++++++++++++++",response);
                            LifeAdapter lifeAdapter = new LifeAdapter(lifeResponse.mainConfigInfo,getActivity());
                            WebAdapter webAdapter = new WebAdapter(getActivity(),lifeResponse.expandConfigInfo);
                            mLifeGrid.setAdapter(lifeAdapter);
                            mWebLoad.setAdapter(webAdapter);
                        }else {
                            Toast.makeText(getActivity(),responseBean.getMsg(),Toast.LENGTH_SHORT).show();
                        }



                    }
                });
        mLifeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),i+"",Toast.LENGTH_SHORT).show();
            }
        });
    }

}

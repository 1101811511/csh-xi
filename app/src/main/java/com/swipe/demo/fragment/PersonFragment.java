package com.swipe.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.swipe.demo.R;
import com.swipe.demo.activity.MainActivity;
import com.swipe.demo.utils.Logger;
import com.swipe.demo.utils.LoginManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;

import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;


/**
 * Created by Administrator on 2017/5/11.
 */
public class PersonFragment extends BaseFragment {
    @InjectView(R.id.btn)
     Button btn;
    @InjectView(R.id.getIamge)
    Button buttonImage;
    @InjectView(R.id.text)
    TextView mName;
    private String url = "http://gank.io/api/data/Android/10/1";
    private  static  final  String TAG ="PersonFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person,container,false);
        ButterKnife.inject(this,view);
        initView();
        return view;
    }

    private void initView() {

        if (TextUtils.isEmpty(LoginManager.getInstance().getmUser().getNickname())){
            return;
        }else {
            mName.setText(LoginManager.getInstance().getmUser().getNickname());
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.get()
                        .url(url)
                        .tag(this)
                        .build()
                        .connTimeOut(20000)
                        .readTimeOut(20000)
                        .writeTimeOut(20000)
                        .execute(new MyStringCallback() );
            }
        });
        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)mActivity).loginQQ();
            }
        });
    }
    public class MyStringCallback extends StringCallback
    {
        @Override
        public void onBefore(Request request, int id)
        {
//            setTitle("loading...");
        }

        @Override
        public void onAfter(int id)
        {
//            setTitle("Sample-okHttp");
        }

        @Override
        public void onError(Call call, Exception e, int id)
        {
            e.printStackTrace();

        }

        @Override
        public void onResponse(String response, int id)
        {
            Log.e(TAG, "onResponse：complete");
           Logger.e("onResponse:" + response);

            switch (id)
            {
                case 100:
                    Toast.makeText(getContext(), "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(getContext(), "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }


    @OnClick({R.id.btn})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn:
              ((MainActivity) mActivity).loginQQ();
                break;
            case R.id.getIamge:

                break;
        }
    }


}}

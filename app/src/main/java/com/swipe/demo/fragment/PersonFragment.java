package com.swipe.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.swipe.demo.R;
import com.swipe.demo.activity.MainActivity;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/11.
 */
public class PersonFragment extends BaseFragment {
    @InjectView(R.id.btn)
    public Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person,container,false);
        ButterKnife.inject(this,view);
        initView();
        return view;
    }

    private void initView() {
    }


    @OnClick({R.id.btn})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn:
//                mListener = new BaseUiListener();
//                tencent.login(getActivity(),"all",mListener);
                ((MainActivity)mActivtiy).loginQQ();
                break;
        }
    }
    private class BaseUiListener implements IUiListener{

        @Override
        public void onComplete(Object o) {
            Toast.makeText(getContext(), "授权成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(getContext(), "授权失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(getContext(), "授权取消", Toast.LENGTH_SHORT).show();
        }
    }


}

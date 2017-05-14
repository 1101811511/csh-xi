package com.swipe.demo.activity;

import android.content.Intent;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import com.swipe.demo.utils.Logger;

public class BaseLoginActivity extends BaseActivity {
    private static String TAG = "SwipeDemo";
    private IUiListener mListener;
    private  Tencent tencent;

    {
        mListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                Toast.makeText(BaseLoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                String json = o.toString();
                Logger.e(TAG, json);
            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode , resultCode, data, mListener);
    }

    public void loginQQ() {
        tencent = Tencent.createInstance("1106159254", this);
        tencent.login(this, "all", mListener);
//        tencent.getQQToken()
    }
    public  void  getUserInfo(){
//        UserInfo userInfo = new UserInfo(BaseLoginActivity.this,)
    }

}

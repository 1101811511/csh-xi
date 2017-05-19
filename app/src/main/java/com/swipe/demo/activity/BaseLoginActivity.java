package com.swipe.demo.activity;

import android.content.Intent;
import android.widget.Toast;

import com.swipe.demo.Bean.UserQQ;
import com.swipe.demo.utils.GsonUtils;
import com.swipe.demo.utils.LoginManager;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import com.swipe.demo.utils.Logger;

import org.json.JSONException;
import org.json.JSONObject;

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
                Logger.e(TAG,json);
                if (json.contains("access_token")){
                    JSONObject jo =(JSONObject)o;
                    //登录授权
                    try {
                        tencent.setOpenId(jo.getString("openid"));
                        tencent.setAccessToken(jo.getString("access_token"),jo.getString("expires_in"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    getUserInfo();
                }else {
                    UserQQ  userQQ = GsonUtils.fromJson(json,UserQQ.class);
                    Logger.e("城市"+userQQ.getCity());
                    LoginManager.getInstance().setUser(userQQ);
                }
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
    }
    public void getUserInfo(){
        UserInfo userInfo = new UserInfo(BaseLoginActivity.this,tencent.getQQToken());
        userInfo.getUserInfo(mListener);
    }



}

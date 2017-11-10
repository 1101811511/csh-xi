package com.swipe.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import com.swipe.demo.Bean.UserQQ;
import com.swipe.demo.fragment.MainFragment;
import com.swipe.demo.utils.GsonUtils;
import com.swipe.demo.utils.LoginManager;
import com.swipe.demo.view.BroadCastManager;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import com.swipe.demo.utils.Logger;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class BaseLoginActivity extends BaseActivity {
    private static String TAG = "SwipeDemo";
    private IUiListener mListener;
    private  Tencent tencent;
    private  EventBus eventBus;

    {
        mListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
//                Toast.makeText(BaseLoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
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
                    LoginManager.getInstance().setUser(userQQ);
                    LoginManager.getInstance().saveLoginInfo(userQQ);
                    //发送广播
                    Intent intent = new Intent();
                    intent.putExtra("name",userQQ.getNickname());
                    intent.putExtra("head",userQQ.getFigureurl_qq_1());
                    intent.setAction("fragment_person");
                    BroadCastManager.getInstance().sendBroadCast(BaseLoginActivity.this,intent);

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
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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

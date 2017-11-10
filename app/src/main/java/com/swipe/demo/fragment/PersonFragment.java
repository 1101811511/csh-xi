package com.swipe.demo.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.squareup.picasso.Picasso;
import com.swipe.demo.R;
import com.swipe.demo.activity.DemoActivity;
import com.swipe.demo.activity.EncourageActivity;
import com.swipe.demo.activity.MainActivity;
import com.swipe.demo.utils.Logger;
import com.swipe.demo.utils.LoginManager;
import com.swipe.demo.utils.PreferenceUtil;
import com.swipe.demo.view.BroadCastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.pedant.sweetalert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;


/**
 * Created by Administrator on 2017/5/11.
 */
public  class PersonFragment extends BaseFragment {
    @InjectView(R.id.qq_login)
    FrameLayout qqLogin;//QQ登陆
    @InjectView(R.id.weibo_login)
    FrameLayout weiboLogin;//微薄登陆
    @InjectView(R.id.login_bg)
    ImageView loginBg; //登陆的背景
    @InjectView(R.id.header_seconde_img)
    CircleImageView headImg;//头像
    @InjectView(R.id.first_login)
    FrameLayout firstLogin;//尚未登录
    @InjectView(R.id.second_login)
    FrameLayout secondLogin;//已经登录
    @InjectView(R.id.login_name)
    TextView loginName;//登陆名字
    @InjectView(R.id.loginout)
    Button  loginOut;
    @InjectView(R.id.contact_us)
    LinearLayout mContact;
    @InjectView(R.id.about_us)
    LinearLayout mAbout;
    @InjectView(R.id.encourage_us)
    LinearLayout mEncourage;
    @InjectView(R.id.login_out)
    LinearLayout mLoginOut;//退出登录
    private String url = "http://gank.io/api/data/Android/10/1";
    private  static  final  String TAG ="PersonFragment";
    private LocalReceiver mReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person,container,false);
        ButterKnife.inject(this,view);
        initView();
        return view;
    }
    private void initView() {
      Observable<String>   observable = Observable.create(new Observable.OnSubscribe<String>() {
          @Override
          public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("阿川");
          }
      });
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.print(s);
            }
        };
        Observable<String>  observable1 = Observable.just("阿川");
        Action1<String> action1 = new Action1<String>() {
            @Override
            public void call(String s) {
             System.out.print(s);
            }
        };
        Observable.just("阿川").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.print(s);
            }
        });

        observable.subscribe(subscriber);
        Observable.just("阿川")
                .map(new Func1<String, Object>() {
                    @Override
                    public Object call(String s) {
                        return s+"tf";
                    }
                }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object s) {
                System.out.print(s.toString());
            }
        });
        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction("fragment_person");
            mReceiver = new LocalReceiver();
            BroadCastManager.getInstance().registerReceiver(mActivity,
                    mReceiver, filter);//注册广播接收者
        } catch (Exception e) {
            e.printStackTrace();
        }
        Boolean isTrue = LoginManager.getInstance().isValiabLogin();
        if (isTrue){
            firstLogin.setVisibility(View.GONE);
            secondLogin.setVisibility(View.VISIBLE);
            mLoginOut.setVisibility(View.VISIBLE);
            String name = LoginManager.getInstance().getmUser().getNickname();
            loginName.setText(name);
            Picasso.with(getContext()).load(LoginManager.getInstance().getmUser().getFigureurl_qq_1()).into(headImg);
            Picasso.with(getContext())
                    .load(LoginManager.getInstance()
                    .getmUser()
                     .getFigureurl_qq_1())
                     .into(loginBg);
            Logger.e(TAG,LoginManager.getInstance().getmUser().getFigureurl_qq_1());
        }else {
           firstLogin.setVisibility(View.VISIBLE);
            secondLogin.setVisibility(View.GONE);
        }
    }
    @OnClick({R.id.qq_login,R.id.weibo_login,R.id.contact_us,R.id.about_us,R.id.encourage_us,R.id.login_out})
    void onClcik(View v){
       switch(v.getId()){
           case R.id.qq_login:
               ((MainActivity)mActivity).loginQQ();
               break;
           case R.id.weibo_login:
               Toast.makeText(mActivity,"服务尚未开通",Toast.LENGTH_SHORT).show();
               break;
           case R.id.contact_us:
               url = "mqqwpa://im/chat?chat_type=wpa&uin=1101811511";
               startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url)));
               break;
           case R.id.encourage_us:
               EncourageActivity.launchActivity(mActivity);
               break;
           case R.id.login_out:
               Intent tent  = new Intent(getActivity(), DemoActivity.class);
               tent.putExtra("1","阿川");
               startActivity(tent);
               break;
//               final SweetAlertDialog  dialog = new SweetAlertDialog(getContext());
//               dialog.setTitleText("确定退出？");
//               dialog.setConfirmText("确定");
//               dialog.setCancelText("取消");
//               dialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                   @Override
//                   public void onClick(SweetAlertDialog sweetAlertDialog) {
//                       dialog.dismiss();
//                   }
//               });
//               dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                   @Override
//                   public void onClick(SweetAlertDialog sweetAlertDialog) {
//                       LoginManager.getInstance().loginOut();
//                       firstLogin.setVisibility(View.VISIBLE);
//                       secondLogin.setVisibility(View.GONE);
//                       mLoginOut.setVisibility(View.GONE);
//                       dialog.dismiss();
//                   }
//               });
//               dialog.show();

        }
    }
    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //收到广播后的处理
            mLoginOut.setVisibility(View.VISIBLE);
            String name = intent.getStringExtra("name");
            loginName.setText(name);
            String head = intent.getStringExtra("head");
                firstLogin.setVisibility(View.GONE);
                secondLogin.setVisibility(View.VISIBLE);
                loginName.setText(name);
                Picasso.with(getContext()).load(head).into(headImg);
                Picasso.with(getContext())
                        .load(LoginManager.getInstance()
                         .getmUser()
                         .getFigureurl_qq_1())
                         .into(loginBg);
        }

    }
    @Override
    public void onResume() {
        super.onResume();
        if (LoginManager.getInstance().isValiabLogin()){
            firstLogin.setVisibility(View.GONE);
            secondLogin.setVisibility(View.VISIBLE);
            loginName.setText(LoginManager.getInstance().getmUser().getNickname());
            Picasso.with(getContext()).load(LoginManager.getInstance().getmUser().getFigureurl_qq_1()).into(headImg);
            Picasso.with(getContext())
                    .load(LoginManager.getInstance()
                            .getmUser()
                            .getFigureurl_qq_1())
                    .into(loginBg);
            Logger.e(TAG,LoginManager.getInstance().getmUser().getFigureurl_qq_1());
        }else {
            firstLogin.setVisibility(View.VISIBLE);
            secondLogin.setVisibility(View.GONE);
        }

    }



}


package com.steven.babyiyo.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.steven.babyiyo.HomeActivity;
import com.steven.babyiyo.R;
import com.steven.babyiyo.UserNameActivity;
import com.steven.babyiyo.bean.MyUser;
import com.steven.babyiyo.component.EditTextWithDel;
import com.steven.babyiyo.component.PaperButton;
import com.steven.babyiyo.utlis.CheckUtils;
import com.steven.babyiyo.utlis.Tools;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

@ContentView(R.layout.fragment_login)
public class FragmentLogin extends BaseFragment  {
    @ViewInject(R.id.userph)
    EditTextWithDel userphone;
    @ViewInject(R.id.userpass)
    EditTextWithDel userpass;
    @ViewInject(R.id.bt_login)
    PaperButton bt_login;
    @ViewInject(R.id.login_progress)
    ProgressBar login_progress;
    @ViewInject(R.id.tv_forgetcode)
    TextView tv_forgetcode;
    @ViewInject(R.id.loginusericon)
    ImageView loginusericon;
    @ViewInject(R.id.codeicon)
    ImageView codeicon;
    @ViewInject(R.id.rela_name)
    RelativeLayout rela_name;
    @ViewInject(R.id.rela_pass)
    RelativeLayout rela_pass;
    private Handler handler = new Handler(){};




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initLogin();
        textListener();


    }

    private void textListener() {
        userphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = userphone.getText().toString();
                if(CheckUtils.isMobile(text)){
                    //抖动
                    rela_name.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));

                }

            }
        });
        userpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                rela_pass.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                    //rela_pass.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));

            }
        });
    }

    private void initLogin() {
        SharedPreferences userinfo = getActivity().getSharedPreferences("userinfo", 0);
        userphone.setText(userinfo.getString("username",null));
        userpass.setText(userinfo.getString("password",null));
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final   String phone = userphone.getText().toString();
                final String passwords = userpass.getText().toString();
                final  View view= v;

                if (TextUtils.isEmpty(phone)){
                    rela_name.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    loginusericon.setAnimation(Tools.shakeAnimation(2));
                    showSnackar(v,"IYO提示：请输入手机号码");
                    return;
                }
                if(!CheckUtils.isMobile(phone)){
                    //抖动
                    rela_name.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    loginusericon.setAnimation(Tools.shakeAnimation(2));
                    showSnackar(v,"IYO提示：用户名不正确");


                    return;
                }
                if (TextUtils.isEmpty(passwords)){
                    rela_pass.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    codeicon.setAnimation(Tools.shakeAnimation(2));
                    showSnackar(v,"IYO提示：请输入密码");

                    return;
                }
                login_progress.setVisibility(View.VISIBLE);


                BmobUser.loginByAccount(getActivity(), phone, passwords, new LogInListener<MyUser>() {

                    @Override
                    public void done(MyUser user, BmobException e) {
                        // TODO Auto-generated method stub
                        if(user!=null){
                            rela_name.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));
                            rela_name.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));
                            showSnackar(view,"IYO提示：登陆成功");
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    login_progress.setVisibility(View.GONE);
                                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                                    startActivity(intent);
                                    getActivity().overridePendingTransition(R.anim.fade,
                                            R.anim.my_alpha_action);


                                }
                            },1500);

                        }else{
                            login_progress.setVisibility(View.GONE);
                            rela_pass.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                            codeicon.setAnimation(Tools.shakeAnimation(2));
                            showSnackar(view,"IYO提示：登陆失败");
                        }
                    }
                });
            }
        });




    }
}
	

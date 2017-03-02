package com.steven.babyiyo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.steven.babyiyo.R;
import com.steven.babyiyo.UserNameActivity;
import com.steven.babyiyo.bean.MyUser;
import com.steven.babyiyo.component.EditTextWithDel;
import com.steven.babyiyo.component.PaperButton;
import com.steven.babyiyo.utlis.CheckUtils;
import com.steven.babyiyo.utlis.Tools;

import org.w3c.dom.Text;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.SaveListener;

@ContentView(R.layout.fragment_regist)
public class FragmentRegist extends BaseFragment  {
@ViewInject(R.id.next)
PaperButton nextBt;
    @ViewInject(R.id.userpassword)
    EditTextWithDel userpassword;
    @ViewInject(R.id.send_smscode)
    PaperButton sendsmscode;
    @ViewInject(R.id.userphone)
    EditTextWithDel userphone;
    @ViewInject(R.id.smscode)
    EditTextWithDel smscode;
    @ViewInject(R.id.fg_regist)
    LinearLayout fg_regist;
    @ViewInject(R.id.rela_rephone)
    RelativeLayout rela_rephone;
    @ViewInject(R.id.rela_recode)
    RelativeLayout rela_recode;
    @ViewInject(R.id.rela_repass)
    RelativeLayout rela_repass;
    @ViewInject(R.id.usericon)
    ImageView phoneIv;
    @ViewInject(R.id.keyicon)
    ImageView keyIv;
    @ViewInject(R.id.codeicon)
    ImageView passIv;
    MyCountTimer timer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     return x.view().inject(this,inflater,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        TextListener();
    }

    private void TextListener() {
        //用户名改变背景变
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
                    rela_rephone.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));

                }

            }
        });
        //验证码改变背景变
        smscode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                    rela_recode.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));



            }
        });
        //密码改变背景变
        userpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                rela_repass.setBackground(getResources().getDrawable(R.drawable.bg_border_color_black));



            }
        });






    }

    private void initView() {
        //发送验证码点击事件
        sendsmscode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view =v;
                String phone = userphone.getText().toString();
                boolean mobile = CheckUtils.isMobile(phone);
                if (!TextUtils.isEmpty(phone)){
                if (mobile) {
                    timer = new MyCountTimer(60000, 1000);
                    timer.start();
                    BmobSMS.requestSMSCode(getActivity(), phone, "IYO短信",new RequestSMSCodeListener() {

                        @Override
                        public void done(Integer smsId,BmobException ex) {
                            if(ex==null){//验证码发送成功
                                Log.i("smile", "短信id："+smsId);
                                showSnackar(view,"IYO提示：短信发送成功");
                            }else{
                                showSnackar(view,"IYO提示：短信发送失败"+"错误码"+smsId);

                            }
                        }

                    });}else
                {
                    rela_rephone.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    phoneIv.setAnimation(Tools.shakeAnimation(2));
                    showSnackar(view,"IYO提示：输入手机号码");
                }


                }else {
                    rela_rephone.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    phoneIv.setAnimation(Tools.shakeAnimation(2));
                  showSnackar(view,"IYO提示：手机号码不正确");
                }

            }
        });
        //下一步的点击事件
        nextBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                final String password = userpassword.getText().toString();
                String code = smscode.getText().toString();
                final String phone = userphone.getText().toString();

                if (TextUtils.isEmpty(phone)){
                   // fg_regist.setBackgroundResource(R.color.colorAccent);
                    rela_rephone.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    phoneIv.setAnimation(Tools.shakeAnimation(2));
                    showSnackar(view,"IYO提示：请输入手机号码");

                    return;
                }
                if(!CheckUtils.isMobile(phone)){
                    rela_rephone.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    phoneIv.setAnimation(Tools.shakeAnimation(2));
                    showSnackar(view,"IYO提示：手机号不正确");
                    // fg_regist.setBackgroundResource(R.color.colorAccent);
                    return;
                }
                if (TextUtils.isEmpty(code)){
                    rela_recode.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    keyIv.setAnimation(Tools.shakeAnimation(2));
                   // fg_regist.setBackgroundResource(R.color.colorAccent);
                    showSnackar(view,"IYO提示：请输入验证码");
                    return;

                }
                if (TextUtils.isEmpty(password)){
                    rela_repass.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                    passIv.setAnimation(Tools.shakeAnimation(2));
                   // fg_regist.setBackgroundResource(R.color.colorAccent);
                    showSnackar(view,"IYO提示：请输入密码");
                    return;
                }
                final MyUser user = new MyUser();
                user.setPassword(password);
                user.setMobilePhoneNumber(phone);
                user.signOrLogin(getActivity(), code, new SaveListener() {

                    @Override
                    public void onSuccess() {
                        Tools.saveUserInfo(getActivity(),"userinfo",phone,password);
                        // TODO Auto-generated method stub
                      //  fg_regist.setBackgroundResource(R.color.green);
                        Intent intent = new Intent(getActivity(), UserNameActivity.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.fade,
                                R.anim.my_alpha_action);
                        Log.i("smile", ""+ BmobUser.getCurrentUser(getActivity(),MyUser.class).getUsername()+"-"+user.getAge()+"-"+user.getObjectId()+"-"+user.getEmail());
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        // TODO Auto-generated method stub

                        rela_recode.setBackground(getResources().getDrawable(R.drawable.bg_border_color_cutmaincolor));
                        keyIv.setAnimation(Tools.shakeAnimation(2));
                        showSnackar(view,"IYO提示：验证码错误");
                       // fg_regist.setBackgroundResource(R.color.colorAccent);
                    }
                });


            }
        });


    }
//事件定时器
    class MyCountTimer extends CountDownTimer {

        public MyCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            sendsmscode.setText((millisUntilFinished / 1000) +"秒后重发");
            sendsmscode.setClickable(false);
        }
        @Override
        public void onFinish() {
            sendsmscode.setText("重新发送");
            sendsmscode.setClickable(true);
        }
    }
//回收timer
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
        }
    }
}
	

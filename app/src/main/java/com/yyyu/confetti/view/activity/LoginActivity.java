package com.yyyu.confetti.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;

import com.yyyu.baselibrary.utils.DimensChange;
import com.yyyu.confetti.bean.LoginFormBean;
import com.yyyu.confetti.R;
import com.yyyu.confetti.presenter.LoginActPresenter;
import com.yyyu.confetti.utils.RevealAnimUtils;
import com.yyyu.confetti.view.inter.ILoginView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 功能：登录界面
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/31
 */

public class LoginActivity extends BaseActivity implements ILoginView {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.cv_login)
    CardView cvLogin;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.fab_to_register)
    FloatingActionButton fabToRegister;

    boolean isCreateOnce  = true;
    private LoginActPresenter presenter;

    @Override
    public void beforeInit() {
        setTransition();
        presenter = new LoginActPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

        cvLogin.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(isCreateOnce){
                    RevealAnimUtils.animateRevealShow(cvLogin ,
                            cvLogin.getWidth(),
                            DimensChange.dp2px(LoginActivity.this , 40),
                            0,
                            cvLogin.getHeight());
                    isCreateOnce = false;
                }
            }
        });

    }

    @OnClick(R.id.fab_to_register)
    public void toRegisterAct(View view) {
        RegisterActivity.startAction(this , fabToRegister);
    }

    @OnClick(R.id.btn_go)
    public void doLogin(View view) {
        presenter.login(this);
    }

    public static void startAction(Activity activity, FloatingActionButton fab) {
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, fab,
                activity.getResources().getString(R.string.login_act_fab_share)).toBundle();
        Intent intent = new Intent(activity, LoginActivity.class);
        ActivityCompat.startActivity(activity, intent, bundle);
    }
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
       activity.startActivity(intent);
    }

    @Override
    public LoginFormBean getInputInfo() {
        LoginFormBean loginFormBean = new LoginFormBean();
        loginFormBean.setUsername(etUsername.getText().toString());
        loginFormBean.setPwd( etPassword.getText().toString());
        return loginFormBean;
    }

    @Override
    public void close() {
        LoginActivity.this.finish();
    }
}

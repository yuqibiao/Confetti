package com.yyyu.confetti.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;

import com.yyyu.confetti.R;
import com.yyyu.confetti.bean.RegisterFormBean;
import com.yyyu.confetti.presenter.RegisterActPresenter;
import com.yyyu.confetti.utils.RevealAnimUtils;
import com.yyyu.confetti.view.inter.IRegisterView;

import butterknife.BindView;

/**
 * 功能：注册Activity
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/31
 */

public class RegisterActivity extends BaseActivity implements IRegisterView{


    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_re_pwd)
    EditText etRepeatPassword;
    @BindView(R.id.bt_go)
    Button btGo;
    @BindView(R.id.cv_add)
    CardView cvAdd;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private RegisterActPresenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void beforeInit() {
        presenter = new RegisterActPresenter(this);
    }

    @Override
    protected void initView() {

        animateRevealShow();

    }

    @Override
    protected void initListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    animateRevealClose();
                } else {
                    onBackPressed();
                }
            }
        });
        //---发送注册请求
        btGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.register();
            }
        });
    }


    private boolean isOnCreateOnce = true;

    public void animateRevealShow() {

        cvAdd.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(isOnCreateOnce){
                    RevealAnimUtils.animateRevealShow(cvAdd, cvAdd.getWidth() / 2, 0, fab.getWidth() / 2, cvAdd.getHeight());
                    isOnCreateOnce = false;
                }
            }
        });


    }

    public void animateRevealClose() {
        RevealAnimUtils.animateRevealClose(cvAdd, cvAdd.getWidth() / 2, 0, cvAdd.getHeight(), fab.getWidth() / 2,
                new RevealAnimUtils.OnRevealCloseListener() {
                    @Override
                    public void close() {
                        RegisterActivity.super.onBackPressed();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animateRevealClose();
        } else {
            super.onBackPressed();
        }
    }

    public static void startAction(Activity activity, FloatingActionButton fab) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setExitTransition(null);
            activity.getWindow().setEnterTransition(null);
        }
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, fab,
                activity.getResources().getString(R.string.login_act_fab_share)).toBundle();
        Intent intent = new Intent(activity, RegisterActivity.class);
        ActivityCompat.startActivity(activity, intent, bundle);
    }

    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }


    @Override
    public RegisterFormBean getInputInfo() {
        RegisterFormBean inputInfo = new RegisterFormBean();
        inputInfo.setUsername(etUsername.getText().toString());
        inputInfo.setPwd(etPassword.getText().toString());
        inputInfo.setRePwd(etRepeatPassword.getText().toString());
        return inputInfo;
    }

    @Override
    public void toLoginAct() {
        onBackPressed();
    }

}

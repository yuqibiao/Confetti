package com.yyyu.confetti.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yyyu.baselibrary.utils.MySnackBar;
import com.yyyu.baselibrary.utils.StatusBarCompat;
import com.yyyu.confetti.MyApplication;
import com.yyyu.confetti.R;
import com.yyyu.confetti.di.component.BaseActComponent;
import com.yyyu.confetti.di.component.DaggerBaseActComponent;
import com.yyyu.confetti.di.module.BaseActModule;
import com.yyyu.confetti.view.inter.IBaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 功能：Activity的基类
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/13
 */
public  abstract  class BaseActivity extends AppCompatActivity implements IBaseView {

    private static final String TAG = "BaseActivity";

    @Inject
    protected Gson mGson;
    private BaseActComponent baseActComponent;
    private Unbinder mUnbind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: 当前显示的Activity是========"+this.getClass().getName());
        ActivityHolder.addActivity(this);
        beforeSetContentView();
        setContentView(getLayoutId());
        mUnbind = ButterKnife.bind(this);
        baseActComponent = DaggerBaseActComponent.builder()
                .applicationComponent( ((MyApplication)getApplication()).getAppComponent() )
                .baseActModule(new BaseActModule(this))
                .build();
        init();
    }

    public void beforeSetContentView() {

    }

    private void init() {
        beforeInit();
        initView();
        initListener();
        initData();
        afterInit();
    }

    /**
     * 钩子方法：得到layout的资源Id
     * @return
     */
    public abstract  int getLayoutId();

    public void beforeInit() {
    }

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 注册监听
     */
    protected abstract void initListener();

    /**
     * 初始化数据
     */
    protected void initData() {
    }

    /**
     * 初始化之后
     */
    protected void afterInit() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbind.unbind();
        ActivityHolder.removeActivity(this);
    }

    @Override
    public void showToast(String msg) {
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hiddenProgress() {

    }

    @Override
    public void showSnackBar(String msg) {
        MySnackBar.showShortDef(this , msg);
    }

    public BaseActComponent getBaseActComponent(){
        return baseActComponent;
    }

    protected void setTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Fade());
            getWindow().setReturnTransition(new Fade());
            getWindow().setSharedElementReturnTransition(new ChangeBounds());
            getWindow().setSharedElementEnterTransition(new ChangeBounds());
        }
    }

}

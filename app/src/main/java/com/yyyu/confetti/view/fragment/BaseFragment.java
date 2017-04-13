package com.yyyu.confetti.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yyyu.baselibrary.utils.MySnackBar;
import com.yyyu.baselibrary.utils.MyToast;
import com.yyyu.confetti.MyApplication;
import com.yyyu.confetti.R;
import com.yyyu.confetti.di.component.BaseFrgComponent;
import com.yyyu.confetti.di.component.DaggerBaseFrgComponent;
import com.yyyu.confetti.di.module.BaseFrgModule;
import com.yyyu.confetti.view.inter.IBaseView;
import com.yyyu.confetti.view.wdiget.LoadingStatusPager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Retrofit;


/**
 * 功能：fragment的基类
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/03/15
 */
public abstract class BaseFragment extends Fragment implements IBaseView {

    @Inject
    protected Gson mGson;
    private View rootView;
    private BaseFrgComponent mBaseFrgComponent;
    private Unbinder mUnbind;
    private LoadingStatusPager.LoadResult loadResult = LoadingStatusPager.LoadResult.LOADING;
    private LoadingStatusPager statusPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.from(getActivity()).inflate(getLayoutId(), container, false);
       /* statusPager = new LoadingStatusPager(
                getContext(), R.layout.loading, R.layout.loading_failed, R.layout.loading_empty) {
            @Override
            public View getContentView() {
                return rootView;
            }

            @Override
            protected void reload() {
                initData();
            }
        };*/

        mBaseFrgComponent = DaggerBaseFrgComponent
                .builder()
                .applicationComponent(((MyApplication) getContext().getApplicationContext()).getAppComponent())
                .baseFrgModule(new BaseFrgModule(this))
                .build();
        mUnbind = ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        beforeInit();
        initView();
        initListener();
        initData();
        afterInit();
    }

    /**
     * 得到fragment布局文件id的钩子方法
     */
    public abstract int getLayoutId();

    /**
     * 初始化之前：进行一些变量的初始化
     */
    protected void beforeInit() {
    }

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 初始化监听
     */
    protected abstract void initListener();

    /**
     * 初始化数据
     */
    protected void initData() {
    }

    protected void afterInit() {
    }

    public BaseFrgComponent getBaseFrgComponent() {
        return mBaseFrgComponent;
    }

    @Override
    public void showToast(String msg) {
        MyToast.showShort(getContext() , msg);
    }

    @Override
    public void showSnackBar(String msg) {
        MySnackBar.showShortDef(getActivity() , msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbind.unbind();
    }

    public void setLoadResult(LoadingStatusPager.LoadResult loadResult) {
        this.loadResult = loadResult;
        statusPager.setViewByStatus(loadResult);
    }
}

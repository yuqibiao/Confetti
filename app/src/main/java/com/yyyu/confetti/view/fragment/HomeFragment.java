package com.yyyu.confetti.view.fragment;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.github.rubensousa.floatingtoolbar.FloatingToolbar;
import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.baselibrary.view.ViewpagerLooper;
import com.yyyu.baselibrary.view.recyclerview.adapter.HeaderAndFooterWrapper;
import com.yyyu.baselibrary.view.recyclerview.listener.OnRvItemClickListener;
import com.yyyu.baselibrary.view.recyclerview.refresh.RefreshLayout;
import com.yyyu.confetti.MyApplication;
import com.yyyu.confetti.R;
import com.yyyu.confetti.adapter.HomeAdapter;
import com.yyyu.confetti.adapter.HomeHeaderVpAdapter;
import com.yyyu.confetti.bean.HomeData;
import com.yyyu.confetti.bean.HomeItem;
import com.yyyu.confetti.bean.HomeTop;
import com.yyyu.confetti.di.component.DaggerHomeFrgComponent;
import com.yyyu.confetti.di.module.HomeFrgModule;
import com.yyyu.confetti.model.user_state.UserStateContext;
import com.yyyu.confetti.net.zhihu.ZhihuMethods;
import com.yyyu.confetti.presenter.inter.IHomeFrgPresenter;
import com.yyyu.confetti.utils.RxBus;
import com.yyyu.confetti.view.activity.ContentShowActivity;
import com.yyyu.confetti.view.activity.LoginActivity;
import com.yyyu.confetti.view.activity.NetaseDetailActivity;
import com.yyyu.confetti.view.activity.RegisterActivity;
import com.yyyu.confetti.view.dialog.AddCollDialog;
import com.yyyu.confetti.view.inter.IHomeFrgView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 功能：首页Fragment
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/15
 */

public class HomeFragment extends BaseFragment implements IHomeFrgView {

    private static final String TAG = "HomeFragment";

    @BindView(R.id.pb_load)
    ProgressBar pbLoad;
    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.fab_home_op)
    FloatingActionButton fabHomeOp;
    @BindView(R.id.rl_home)
    RefreshLayout rlHome;
    @BindView(R.id.tb_home)
    Toolbar tbHome;

    @Inject
    ZhihuMethods zhihuMethods;
    @Inject
    IHomeFrgPresenter homeFrgPresenter;

    private HomeAdapter homeAdapter;
    private HeaderAndFooterWrapper wrapperAdapter;
    List<HomeItem> homeItemList;
    private View header;
    private ViewPager vpTop;
    private ViewpagerLooper vplTop;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void beforeInit() {
        homeAdapter = new HomeAdapter(getContext());
        wrapperAdapter = new HeaderAndFooterWrapper(homeAdapter);
        homeItemList = new ArrayList<>();
        rvHome.setAdapter(wrapperAdapter);
    }

    @Override
    protected void initView() {
        DaggerHomeFrgComponent
                .builder()
                .baseFrgComponent(getBaseFrgComponent())
                .homeFrgModule(new HomeFrgModule(this))
                .build()
                .inject(this);
        rvHome.setLayoutManager(new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL) );
        header = LayoutInflater.from(getContext()).inflate(R.layout.pt_home_header, rvHome, false);
        wrapperAdapter.addHeader(header);
        rlHome.setColorSchemeColors(getResources().getColor(R.color.colorAccent));

        vpTop = (ViewPager) header.findViewById(R.id.vp_top);
        vplTop = (ViewpagerLooper) header.findViewById(R.id.vpl_top);

    }

    @Override
    protected void initListener() {
        tbHome.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getInstance().post("open_drawer");
            }
        });
        rlHome.setOnRefreshWrapper(new RefreshLayout.OnRefreshWrapperListener() {
            @Override
            public void onRefresh() {
                homeFrgPresenter.getDate(0);
            }

            @Override
            public void onLoadMore() {
                homeFrgPresenter.getDate(1);
            }

        });
        rvHome.addOnItemTouchListener(new OnRvItemClickListener(rvHome) {
            @Override
            public void onItemClick(View itemView, int position) {
                HomeItem homeItem = homeItemList.get(position);
                ImageView ivShare = (ImageView) itemView.findViewById(R.id.iv_item_icon);
                if (homeItem.getType() == 100) {//分类提示tip
                    //---TODO
                } else if (homeItem.getType() == 1) {//网易公开课
                    NetaseDetailActivity.startAction(getActivity(), homeItem.getId(), ivShare);
                } else {
                    ContentShowActivity.startAction(getActivity(), homeItem.getType(), homeItem.getId(), homeItem.getUsername(), ivShare);
                }
            }

            @Override
            public void onItemLongClick(View itemView, int position) {

            }
        });
        fabHomeOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserStateContext stateContext =  ((MyApplication)getActivity().getApplication()).getUserStateContext();
                stateContext.oprFabEdit(HomeFragment.this);
            }
        });

    }

    @Override
    protected void initData() {
        homeFrgPresenter.getDate(0);
        homeFrgPresenter.getTopData();
    }


    @Override
    public void onRefresh(HomeData homeData) {
        homeItemList.clear();
        homeItemList.addAll(homeData.getHomeDataList());
        homeAdapter.setmData(homeItemList);
        wrapperAdapter.notifyDataSetChanged();
        //rvHome.setAdapter(wrapperAdapter);
    }

    @Override
    public void onRefreshFinished() {
        if(rlHome!=null){
            rlHome.setRefreshing(false);
        }
    }

    @Override
    public void onLoadMore(HomeData homeData) {
        homeItemList.addAll(homeData.getHomeDataList());
        homeAdapter.setmData(homeItemList);
        wrapperAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMoreFinished() {
        rlHome.setLoadFinished();
    }

    @Override
    public void setTopData(final HomeTop top) {
        vplTop.setViewPager(vpTop, new ViewpagerLooper.LooperPagerAdapter<HomeItem>(getActivity(), top.getTopData(), R.layout.vp_item_home_top) {
            @Override
            protected void setView(View viewItem, final HomeItem dataBean) {
                final ImageView ivTop = (ImageView) viewItem.findViewById(R.id.iv_top);
                Glide.with(getContext()).load(dataBean.getImgPath()).into(ivTop);
                ivTop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContentShowActivity.startAction(getActivity(), 0, dataBean.getId());
                    }
                });
            }
        });
    }

    @Override
    public void toLoginAct() {
        LoginActivity.startAction(getActivity() , fabHomeOp);
    }

    @Override
    public void showAddCollDialog() {
        AddCollDialog dialog = new AddCollDialog(getContext());
        dialog.show();
    }

    @Override
    public void showProgress() {
        pbLoad.setVisibility(View.VISIBLE);
    }

    @Override
    public void hiddenProgress() {
        if (pbLoad!=null){
            pbLoad.setVisibility(View.GONE);
        }
    }

}

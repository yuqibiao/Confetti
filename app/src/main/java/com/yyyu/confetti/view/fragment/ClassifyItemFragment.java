package com.yyyu.confetti.view.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.yyyu.baselibrary.view.recyclerview.listener.OnRvItemClickListener;
import com.yyyu.baselibrary.view.recyclerview.refresh.RefreshLayout;
import com.yyyu.confetti.R;
import com.yyyu.confetti.adapter.BaseClassifyItemAdapter;
import com.yyyu.confetti.adapter.CsdnItemAdapter;
import com.yyyu.confetti.adapter.JsItemAdapter;
import com.yyyu.confetti.adapter.NetaseItemAdapter;
import com.yyyu.confetti.adapter.ZhihuItemAdapter;
import com.yyyu.confetti.bean.ClassifyData;
import com.yyyu.confetti.bean.ClassifyItem;
import com.yyyu.confetti.bean.HomeItem;
import com.yyyu.confetti.di.component.DaggerClassifyItemFrgComponent;
import com.yyyu.confetti.di.module.ClassifyItemFragModule;
import com.yyyu.confetti.presenter.inter.IClassifyItemFragPresenter;
import com.yyyu.confetti.view.activity.ContentShowActivity;
import com.yyyu.confetti.view.activity.NetaseDetailActivity;
import com.yyyu.confetti.view.inter.IClassifyItemFrgView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 功能：分类中具体的某一项Fragment
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/23
 */

public class ClassifyItemFragment extends BaseFragment implements IClassifyItemFrgView {

    private static final String TAG = "ClassifyItemFragment";

    String tabItemId;
    int type;

    @BindView(R.id.pb_load)
    ProgressBar pbLoad;
    @BindView(R.id.rl_classify_item)
    RefreshLayout rlClassifyItem;
    @BindView(R.id.rv_classify_item)
    RecyclerView rvClassifyItem;

    @Inject
    IClassifyItemFragPresenter presenter;

    private BaseClassifyItemAdapter adapter;
    private List<ClassifyItem> mData;

    @Override
    protected void beforeInit() {
        DaggerClassifyItemFrgComponent
                .builder()
                .baseFrgComponent(getBaseFrgComponent())
                .classifyItemFragModule(new ClassifyItemFragModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_classify_item;
    }

    @Override
    protected void initView() {
        rlClassifyItem.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
    }

    @Override
    protected void initListener() {

        if (type == 0) {//---知乎日报的接口没有加载更多
            rlClassifyItem.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    presenter.getData(0, type, tabItemId);
                }
            });
        } else {
            rlClassifyItem.setOnRefreshWrapper(new RefreshLayout.OnRefreshWrapperListener() {
                @Override
                public void onRefresh() {
                    presenter.getData(0, type, tabItemId);
                }

                @Override
                public void onLoadMore() {
                    presenter.getData(1, type, tabItemId);
                }
            });
        }

        rvClassifyItem.addOnItemTouchListener(new OnRvItemClickListener(rvClassifyItem) {
            @Override
            public void onItemClick(View itemView, int position) {
                if (type == 1) {
                    ImageView ivShare = (ImageView) itemView.findViewById(R.id.iv_item_icon);
                    NetaseDetailActivity.startAction(getActivity(), mData.get(position).getId(), ivShare);
                } else if (type == 2) {
                    ImageView ivShare = (ImageView) itemView.findViewById(R.id.iv_content_icon);
                    ContentShowActivity.startAction(getActivity(), type, mData.get(position).getId(), mData.get(position).getUsername(), ivShare);
                } else {
                    ImageView ivShare = (ImageView) itemView.findViewById(R.id.iv_item_icon);
                    ContentShowActivity.startAction(getActivity(), type, mData.get(position).getId(), mData.get(position).getUsername(), ivShare);
                }
            }

            @Override
            public void onItemLongClick(View itemView, int position) {

            }
        });


    }

    @Override
    protected void initData() {

        presenter.getData(0, type, tabItemId);

    }

    public static ClassifyItemFragment getInstance(int type, String tabId) {
        ClassifyItemFragment fragment = new ClassifyItemFragment();
        fragment.type = type;
        fragment.tabItemId = tabId;
        return fragment;
    }

    @Override
    public void onRefresh(ClassifyData classifyData) {
        mData = classifyData.getClassifyItemList();
        switch (type) {
            case 0:
                adapter = new ZhihuItemAdapter(getContext(), classifyData.getClassifyItemList());
                rvClassifyItem.setLayoutManager(new LinearLayoutManager(getContext()));
                break;
            case 1:
                adapter = new NetaseItemAdapter(getContext(), classifyData.getClassifyItemList());
                rvClassifyItem.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                break;
            case 2:
                adapter = new JsItemAdapter(getContext(), classifyData.getClassifyItemList());
                rvClassifyItem.setLayoutManager(new LinearLayoutManager(getContext()));
                break;
            case 3:
                adapter = new CsdnItemAdapter(getContext(), classifyData.getClassifyItemList());
                rvClassifyItem.setLayoutManager(new LinearLayoutManager(getContext()));
                break;
        }
        rvClassifyItem.setAdapter(adapter);
    }

    @Override
    public void onRefreshFinished() {
        rlClassifyItem.setRefreshing(false);
    }

    @Override
    public void onLoadMore(ClassifyData classifyData) {
        adapter.getmData().addAll(classifyData.getClassifyItemList());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onLoadMoreFinished() {
        rlClassifyItem.setLoadFinished();
    }

    @Override
    public void showProgress() {
        pbLoad.setVisibility(View.VISIBLE);
    }

    @Override
    public void hiddenProgress() {
        pbLoad.setVisibility(View.GONE);
    }
}

package com.yyyu.confetti.view.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.yyyu.baselibrary.view.recyclerview.listener.OnRvItemClickListener;
import com.yyyu.confetti.R;
import com.yyyu.confetti.adapter.CollDetailAdapter;
import com.yyyu.confetti.bean.CollBean;
import com.yyyu.confetti.model.CollBeanBiz;
import com.yyyu.confetti.net.IRequestCallback;
import com.yyyu.confetti.view.activity.ContentShowActivity;
import com.yyyu.confetti.view.activity.NetaseDetailActivity;

import java.util.List;

import butterknife.BindView;

/**
 * 功能：我的收藏分类详情页
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/4/4
 */

public class CollDetailFragment extends BaseFragment{

    @BindView(R.id.rv_coll_detail)
    RecyclerView rvCollDetail;

    private String cateId ;
    private CollBeanBiz collBeanBiz;
    private List<CollBean> mData;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_coll_detail;
    }

    @Override
    protected void beforeInit() {
        collBeanBiz = new CollBeanBiz();
    }

    @Override
    protected void initView() {
        rvCollDetail.setLayoutManager(new StaggeredGridLayoutManager(1 , StaggeredGridLayoutManager.VERTICAL ));

    }

    @Override
    protected void initData() {
        collBeanBiz.getCollByCateId(cateId, new IRequestCallback<List<CollBean>>() {
            @Override
            public void onSuccess(List<CollBean> result) {
                mData = result;
                rvCollDetail.setAdapter(new CollDetailAdapter(getContext() , result));
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    @Override
    protected void initListener() {
        rvCollDetail.addOnItemTouchListener(new OnRvItemClickListener(rvCollDetail) {
            @Override
            public void onItemClick(View itemView, int position) {
                int type = mData.get(position).getItemType();
                switch (type){
                    case 0:
                    case 2:
                    case 3:
                    case 4:
                        ContentShowActivity.startAction(getActivity() , mData.get(position).getItemType() , mData.get(position).getCollUrl());
                        break;
                    case 1:
                        NetaseDetailActivity.startAction(getActivity(), mData.get(position).getCollUrl());
                        break;
                }
            }

            @Override
            public void onItemLongClick(View itemView, int position) {

            }
        });
    }

    public static CollDetailFragment getInstance(String cateId){
        CollDetailFragment fragment = new CollDetailFragment();
        fragment.cateId = cateId;
        return fragment;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hiddenProgress() {

    }
}

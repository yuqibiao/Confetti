package com.yyyu.confetti.view.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yyyu.baselibrary.view.recyclerview.listener.OnRvItemClickListener;
import com.yyyu.confetti.MyApplication;
import com.yyyu.confetti.R;
import com.yyyu.confetti.adapter.CollCateAdapter;
import com.yyyu.confetti.bean.CollCate;
import com.yyyu.confetti.bean.UserInfo;
import com.yyyu.confetti.model.CollCateBiz;
import com.yyyu.confetti.net.IRequestCallback;
import com.yyyu.confetti.view.activity.LoginActivity;
import com.yyyu.confetti.view.inter.ICollCateFragView;

import java.util.List;

import butterknife.BindView;

/**
 * 功能：我的收藏
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/4/4
 */

public class CollCateFragment extends BaseFragment{

    @BindView(R.id.rv_coll)
    RecyclerView rvColl;
    private CollCateAdapter adapter;
    private CollCateBiz collCateBiz;
    private List<CollCate> mData;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_coll;
    }

    @Override
    protected void beforeInit() {
        collCateBiz = new CollCateBiz();
    }

    @Override
    protected void initView() {
        rvColl.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {
        CollCateBiz collCateBiz = new CollCateBiz();
        UserInfo userInfo = ((MyApplication)getContext().getApplicationContext()).getUserInfo();
        collCateBiz.getAllCollCate(userInfo.getObjectId(), new IRequestCallback<List<CollCate>>() {
            @Override
            public void onSuccess(List<CollCate> result) {
                mData = result;
                adapter = new CollCateAdapter(getContext() , result);
                rvColl.setAdapter(adapter);
                if(result!=null && result.size()>0){
                    replaceWithFrag(CollDetailFragment.getInstance(result.get(0).getObjectId()));
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    @Override
    protected void initListener() {
        rvColl.addOnItemTouchListener(new OnRvItemClickListener(rvColl) {
            @Override
            public void onItemClick(View itemView, int position) {
                replaceWithFrag(CollDetailFragment.getInstance(mData.get(position).getObjectId()));
                adapter.setCheckedPos(position);
            }

            @Override
            public void onItemLongClick(View itemView, int position) {

            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hiddenProgress() {

    }

    private void replaceWithFrag(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_coll_detail , fragment);
        ft.commit();
    }

}

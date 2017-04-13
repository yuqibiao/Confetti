package com.yyyu.confetti.view.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.confetti.R;
import com.yyyu.confetti.bean.ClassifyTab;
import com.yyyu.confetti.di.component.DaggerClassifyDetailFrgComponet;
import com.yyyu.confetti.di.module.ClassifyDetailFrgModule;
import com.yyyu.confetti.net.APIMethodManager;
import com.yyyu.confetti.net.IRequestCallback;
import com.yyyu.confetti.presenter.inter.IClassifyDetailFrgPresenter;
import com.yyyu.confetti.utils.RxBus;
import com.yyyu.confetti.view.inter.IClassifyDetailFrgView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 功能：某一分类详情展示Fragment
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/20
 */

public class ClassifyDetailFragment extends BaseFragment implements IClassifyDetailFrgView {

    private static final String TAG = "ClassifyDetailFragment";

    int type;

    @BindView(R.id.pb_load)
    ProgressBar pbLoad;
    @BindView(R.id.tb_classify_detail)
    Toolbar tbClass;
    @BindView(R.id.tl_classify_detail)
    TabLayout tlClass;
    @BindView(R.id.vp_classify_detail)
    ViewPager vpClass;

    @Inject
    IClassifyDetailFrgPresenter presenter;


    @Override
    protected void beforeInit() {

        DaggerClassifyDetailFrgComponet
                .builder()
                .baseFrgComponent(getBaseFrgComponent())
                .classifyDetailFrgModule(new ClassifyDetailFrgModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_classify_detail;
    }

    @Override
    protected void initView() {
    }


    @Override
    protected void initListener() {
        tbClass.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getInstance().post("open_drawer");
            }
        });
    }

    @Override
    protected void initData() {
        presenter.getData(type);
    }

    /**
     * Fragment传参
     *
     * @param type
     * @return
     */
    public static ClassifyDetailFragment getInstance(int type) {
        ClassifyDetailFragment classifyDetailFragment = new ClassifyDetailFragment();
        classifyDetailFragment.type = type;
        return classifyDetailFragment;
    }


    @Override
    public void setData(ClassifyTab result) {
        final List<ClassifyTab.TabBean> mTabs = result.getTabList();
        final List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < mTabs.size(); i++) {
            fragmentList.add(ClassifyItemFragment.getInstance(type, mTabs.get(i).getId()));
        }
        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {

            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }

            @Override
            public Fragment getItem(int position) {

                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTabs.get(position).getTitle();
            }
        };
        vpClass.setAdapter(adapter);
        tlClass.setupWithViewPager(vpClass);
    }

    @Override
    public void onLoadDataFailed(String tips) {
        showSnackBar("加载数据失败，请检查网络！");
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

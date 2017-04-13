package com.yyyu.confetti.di.module;

import com.yyyu.confetti.net.zhihu.ZhihuMethods;
import com.yyyu.confetti.presenter.HomeFrgPresenter;
import com.yyyu.confetti.presenter.inter.IHomeFrgPresenter;
import com.yyyu.confetti.view.fragment.HomeFragment;
import com.yyyu.confetti.view.inter.IHomeFrgView;

import dagger.Module;
import dagger.Provides;

/**
 * 功能：HomeFragment对应Module
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/15
 */

@Module
public class HomeFrgModule {

    private HomeFragment mHomeFragment;

    public HomeFrgModule(HomeFragment homeFragment){
        this.mHomeFragment = homeFragment;
    }
    @Provides
    public IHomeFrgPresenter provideHomeFrg(){
        return new HomeFrgPresenter(mHomeFragment);
    }
    @Provides
    public ZhihuMethods  provideZHihuMethoda(){
        return ZhihuMethods.getInstance();
    }

}

package com.yyyu.confetti.di.component;

import com.yyyu.confetti.di.module.HomeFrgModule;
import com.yyyu.confetti.net.zhihu.ZhihuMethods;
import com.yyyu.confetti.presenter.inter.IHomeFrgPresenter;
import com.yyyu.confetti.view.fragment.HomeFragment;

import dagger.Component;

/**
 * 功能：HomeFragment对应Component
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/15
 */

@Component(dependencies = BaseFrgComponent.class, modules = {HomeFrgModule.class})
public interface HomeFrgComponent {

    void inject(HomeFragment fragment);

    ZhihuMethods proZhihuMethods();

    IHomeFrgPresenter proHomeFrgPresenter();

}

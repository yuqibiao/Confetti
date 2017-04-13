package com.yyyu.confetti.di.component;

import com.yyyu.confetti.di.module.ClassifyDetailFrgModule;
import com.yyyu.confetti.di.module.HomeFrgModule;
import com.yyyu.confetti.presenter.inter.IClassifyDetailFrgPresenter;
import com.yyyu.confetti.view.fragment.ClassifyDetailFragment;

import dagger.Component;

/**
 * 功能：对应ClassifyDetailFragment
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/24
 */

@Component(dependencies = BaseFrgComponent.class, modules = {ClassifyDetailFrgModule.class})
public interface ClassifyDetailFrgComponet {

    void inject(ClassifyDetailFragment fragment);

    IClassifyDetailFrgPresenter proClassfiyDetailFrgPresenter();

}

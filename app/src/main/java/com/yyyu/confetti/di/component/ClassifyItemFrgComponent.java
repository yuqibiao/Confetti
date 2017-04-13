package com.yyyu.confetti.di.component;

import com.yyyu.confetti.di.module.ClassifyDetailFrgModule;
import com.yyyu.confetti.di.module.ClassifyItemFragModule;
import com.yyyu.confetti.presenter.inter.IClassifyItemFragPresenter;
import com.yyyu.confetti.view.fragment.ClassifyItemFragment;

import dagger.Component;
import dagger.Module;

/**
 * 功能：对应ClassifyItemFragment
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/24
 */

@Component(dependencies = BaseFrgComponent.class, modules = {ClassifyItemFragModule.class})
public interface ClassifyItemFrgComponent {

    void inject(ClassifyItemFragment fragment);

    IClassifyItemFragPresenter proClassifyItemFragPresenter();

}

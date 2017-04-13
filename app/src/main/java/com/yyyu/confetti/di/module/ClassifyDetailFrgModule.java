package com.yyyu.confetti.di.module;

import com.yyyu.confetti.presenter.ClassifyDetailPresenter;
import com.yyyu.confetti.presenter.inter.IClassifyDetailFrgPresenter;
import com.yyyu.confetti.view.fragment.ClassifyDetailFragment;

import dagger.Module;
import dagger.Provides;

/**
 * 功能：对应ClassifyDetailFragment
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/24
 */

@Module
public class ClassifyDetailFrgModule {

    private ClassifyDetailFragment fragment;

    public ClassifyDetailFrgModule(ClassifyDetailFragment fragment){
        this.fragment = fragment;
    }

    @Provides
    public IClassifyDetailFrgPresenter provideClassifyDetatilFragPresenter(){
        return new ClassifyDetailPresenter(fragment);
    }


}

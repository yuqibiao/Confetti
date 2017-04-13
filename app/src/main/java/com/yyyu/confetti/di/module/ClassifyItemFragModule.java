package com.yyyu.confetti.di.module;

import com.yyyu.confetti.presenter.ClassifyItemFragPresenter;
import com.yyyu.confetti.presenter.inter.IClassifyItemFragPresenter;
import com.yyyu.confetti.view.fragment.ClassifyItemFragment;

import dagger.Module;
import dagger.Provides;

/**
 * 功能：对应ClassifyItemFragment
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/24
 */

@Module
public class ClassifyItemFragModule {

    private ClassifyItemFragment fragment;

    public ClassifyItemFragModule(ClassifyItemFragment fragment){
        this.fragment = fragment;
    }

    @Provides
    public IClassifyItemFragPresenter provideClassifyItemFragPresenter(){
        return new ClassifyItemFragPresenter(fragment);
    }

}

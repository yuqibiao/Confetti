package com.yyyu.confetti.di.module;

import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;

/**
 * 功能：Fragment的基类Module
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/15
 */

@Module
public class BaseFrgModule {

    private Fragment mFragment;

    public BaseFrgModule(Fragment fragment) {
        this.mFragment = fragment;
    }

    @Provides
    Fragment provideFragment() {
        return mFragment;
    }


}

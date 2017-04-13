package com.yyyu.confetti.di.module;

import android.app.Activity;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * 功能：基类Activity对应的Module
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/13
 */

@Module
public class BaseActModule {

     Activity mActivity;

    public BaseActModule(Activity activity){
        this.mActivity = activity;
    }

    @Provides
    public Activity  provideAct(){
        return mActivity;
    }


}

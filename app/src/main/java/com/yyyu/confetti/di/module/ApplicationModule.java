package com.yyyu.confetti.di.module;

import android.content.Context;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * 功能：Application对应的Module
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/13
 */

@Module
public class ApplicationModule {

    //---全局的Context
    Context mAContext;
    Gson mGson;

    public ApplicationModule(Context context){
        this.mAContext = context;
    }

    @Provides
    public Context  provideAContext(){
        return mAContext;
    }

    @Provides
    public Gson provideGson(){
        return new Gson();
    }

}

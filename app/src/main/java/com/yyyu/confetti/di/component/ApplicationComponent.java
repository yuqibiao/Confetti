package com.yyyu.confetti.di.component;

import android.content.Context;

import com.google.gson.Gson;
import com.yyyu.confetti.di.module.ApplicationModule;

import dagger.Component;
import dagger.Provides;

/**
 * 功能：Application对应的Component
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/13
 */

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    //---向下提供这个全局的Context
    Context proContext();

    Gson proGson();

}

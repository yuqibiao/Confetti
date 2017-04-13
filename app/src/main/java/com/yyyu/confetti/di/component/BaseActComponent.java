package com.yyyu.confetti.di.component;

import android.app.Activity;

import com.google.gson.Gson;
import com.yyyu.confetti.di.module.BaseActModule;

import dagger.Component;

/**
 * 功能：基类Activity对应的Component
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/13
 */

@Component(dependencies = ApplicationComponent.class , modules = {BaseActModule.class})
public interface BaseActComponent {

    //---向下提供这个Activity
    Activity proActivity();
    //---向下提供Gson实例
    Gson proGson();

}

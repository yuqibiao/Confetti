package com.yyyu.confetti.di.component;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.yyyu.confetti.di.module.BaseFrgModule;

import dagger.Component;

/**
 * 功能：基类Fragment对应Component
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/15
 */

@Component(dependencies = ApplicationComponent.class , modules = {BaseFrgModule.class})
public interface BaseFrgComponent {

    Gson proGson();
    Fragment proFragment();
    Context proContext();

}

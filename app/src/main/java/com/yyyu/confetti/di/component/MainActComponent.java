package com.yyyu.confetti.di.component;

import com.yyyu.confetti.di.module.MainActModule;
import com.yyyu.confetti.view.activity.MainActivity;

import dagger.Component;

/**
 * 功能：MainActivity对应的Component
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/13
 */

@Component(dependencies = BaseActComponent.class , modules = {MainActModule.class})
public interface MainActComponent {

    void inject(MainActivity activity);

}

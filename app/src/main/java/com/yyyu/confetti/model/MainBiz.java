package com.yyyu.confetti.model;

import com.yyyu.confetti.model.inter.IMainBiz;

/**
 * 功能：
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/12
 */

public class MainBiz implements IMainBiz {

    @Override
    public void getDataFromNet() {
        new  Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

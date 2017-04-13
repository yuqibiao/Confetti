package com.yyyu.confetti.net;

/**
 * 功能：网络请求回调
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/14
 */

public interface IRequestCallback<T> {

    void onSuccess(T result);

    void onFailure(Throwable throwable);
}

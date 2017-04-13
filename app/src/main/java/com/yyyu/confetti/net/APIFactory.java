package com.yyyu.confetti.net;

import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.confetti.net.csdn.CsdnApi;
import com.yyyu.confetti.net.js.JsApi;
import com.yyyu.confetti.net.js.JsMethods;
import com.yyyu.confetti.net.netase.NetaseApi;
import com.yyyu.confetti.net.zhihu.ZhihuApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 功能：网络请求的封装
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/14
 */

public class APIFactory {

    public static final String BASE_ZHIHU_URL = "http://news-at.zhihu.com/api/4/";
    public static final String BASE_NETASE_URL = "http://c.open.163.com/mob/";
    public static final String BASE_JS_URL = "http://www.jianshu.com";
    public static final String BASE_CSDN_URL = "http://ms.csdn.net/api/";
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit.Builder builder;

    private APIFactory() {

        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                MyLog.i("APIFactory: OkHttpClient", "OkHttpMessage:" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        OkHttpClient.Builder httpClientBuild = new OkHttpClient.Builder();
        httpClientBuild.addInterceptor(loggingInterceptor);
        httpClientBuild.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder = new Retrofit.Builder()
                .client(httpClientBuild.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

    public static APIFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final APIFactory INSTANCE = new APIFactory();
    }

    public ZhihuApi createZhihuApi() {
        Retrofit retrofit = builder.baseUrl(BASE_ZHIHU_URL).build();
        ZhihuApi zhihuApi = retrofit.create(ZhihuApi.class);
        return zhihuApi;
    }

    public NetaseApi createNetaseApi() {
        Retrofit retrofit = builder.baseUrl(BASE_NETASE_URL).build();
        NetaseApi netaseApi = retrofit.create(NetaseApi.class);
        return netaseApi;
    }

    public CsdnApi createCsdnApi(){
        Retrofit retrofit = builder.baseUrl(BASE_CSDN_URL).build();
        CsdnApi csdnApi = retrofit.create(CsdnApi.class);
        return csdnApi;
    }

}

package com.yyyu.confetti.net.zhihu;

import com.yyyu.confetti.bean.json.ZhihuClassify;
import com.yyyu.confetti.bean.json.ZhihuClassifyData;
import com.yyyu.confetti.bean.json.ZhihuComment;
import com.yyyu.confetti.bean.json.ZhihuContent;
import com.yyyu.confetti.bean.json.ZhihuHome;
import com.yyyu.confetti.bean.json.ZhihuTop;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 功能：知乎日报相关API
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/13
 */

public interface ZhihuApi {

    @GET ("news/latest")
    Observable<ZhihuTop> getTopData();

    @GET("news/before/{date}")
    Observable<ZhihuHome> getHomeDataByDate(@Path("date") String date);

    @GET("news/{id}")
    Observable<ZhihuContent> getContentById(@Path("id") String id);

    @GET ("story/{id}/short-comments")
    Observable<ZhihuComment> getContentComById(@Path("id") String id);

    @GET("themes")
    Observable<ZhihuClassify> getClassify();

    @GET("theme/{classifyId}")
    Observable<ZhihuClassifyData> getDataByClassifyId(@Path("classifyId") String classifyId);


}

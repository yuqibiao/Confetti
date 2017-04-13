package com.yyyu.confetti.net.netase;

import com.yyyu.confetti.bean.json.NetaseClassify;
import com.yyyu.confetti.bean.json.NetaseClassifyData;
import com.yyyu.confetti.bean.json.NetaseComment;
import com.yyyu.confetti.bean.json.NetaseContent;
import com.yyyu.confetti.bean.json.NetaseHome;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 功能：网易公开课相关API
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/14
 */

public interface NetaseApi {

    @GET("classify/newplaylist.do?type=5&id=32&flag=1")
    Observable<NetaseHome> getHomeDataByCursor(@Query("cursor") String cursor);

    //http://c.open.163.com/mob/M7GH051TD/getMoviesForAndroid.do
    @GET("{id}/getMoviesForAndroid.do")
    Observable<NetaseContent> getContentById(@Path("id") String id);

    @GET("http://comment.api.163.com/api/json/post/list/new/normal/video_bbs/{id}/desc/{startCount}/20/100/2/2")
    Observable<NetaseComment> getCommentById(@Path("id") String id , @Path("startCount") int startCount);

    @GET("home/config.do")
    Observable<NetaseClassify> getClassify();

    @GET("classify/newplaylist.do?type=5&flag=1")
    Observable<NetaseClassifyData> getDataByClassifyId(@Query("id") String classifyId, @Query("cursor") String cursor);

}

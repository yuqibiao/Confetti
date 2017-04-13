package com.yyyu.confetti.net.csdn;

import com.yyyu.confetti.bean.json.CsdnClassify;
import com.yyyu.confetti.bean.json.CsdnClassifyData;
import com.yyyu.confetti.bean.json.CsdnContent;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 功能：CSDN Api接口
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/26
 */

public interface CsdnApi {

    @GET("blog/all_tags")
    Observable<CsdnClassify>  getClassify();

    @GET("blog/get_blog_list?SessionId=PeL79RdV86Qj9R8yZK8T5QOTZmVe3LvQpB5UkqtGYWrKvLkCFuhvvSw02VhK%2F16iP9EJqJmWuZdRPIpPfa5wYA%3D%3D&pagesize=20")
    Observable<CsdnClassifyData> getDataByClassifyId(@Query("tagId") String tagId, @Query("page") int page);

    @GET("blog/get_blog_detail?SessionId=PeL79RdV86Qj9R8yZK8T5QOTZmVe3LvQpB5UkqtGYWrKvLkCFuhvvSw02VhK%2F16iP9EJqJmWuZdRPIpPfa5wYA%3D%3D")
    Observable<CsdnContent> getContentById(@Query("articleId") String articleId , @Query("username") String username);

}

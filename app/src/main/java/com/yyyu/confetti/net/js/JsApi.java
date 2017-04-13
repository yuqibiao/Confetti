package com.yyyu.confetti.net.js;

import com.yyyu.confetti.bean.ClassifyData;
import com.yyyu.confetti.bean.ClassifyTab;
import com.yyyu.confetti.bean.HomeData;
import com.yyyu.confetti.bean.ItemContent;
import com.yyyu.confetti.bean.json.JsClassifyData;
import com.yyyu.confetti.net.IRequestCallback;

import rx.Observable;

/**
 * 功能：简书网络请求接口（使用的是jsoup解析）
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/28
 */

public interface JsApi {

    Observable<HomeData> getHomeDataByPage( int page);

    /**
     * 得到分类(目前是Android分类下的数据)
     *
     * @param callback
     */
    void getClassify(IRequestCallback<ClassifyTab> callback);

    /**
     * 某一分类下的数据
     *
     * @param callback
     * @param comId
     * @param page
     */
    void getDataByClassifyId(final IRequestCallback<ClassifyData> callback, String comId, int page);

    /**
     * 得到文章的详情（这里的id直接就是一个url，因为之前抓取得到是url）
     *
     * @param id
     */
    void getContentById(final IRequestCallback<ItemContent> callback , String id);


}

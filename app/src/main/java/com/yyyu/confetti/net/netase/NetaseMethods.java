package com.yyyu.confetti.net.netase;

import com.yyyu.confetti.bean.ContentCom;
import com.yyyu.confetti.bean.ClassifyData;
import com.yyyu.confetti.bean.ClassifyItem;
import com.yyyu.confetti.bean.ClassifyTab;
import com.yyyu.confetti.bean.json.NetaseClassify;
import com.yyyu.confetti.bean.json.NetaseClassifyData;
import com.yyyu.confetti.bean.json.NetaseComment;
import com.yyyu.confetti.bean.json.NetaseContent;
import com.yyyu.confetti.bean.json.NetaseHome;
import com.yyyu.confetti.net.APIFactory;
import com.yyyu.confetti.net.IRequestCallback;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 功能：网易公开课数据请求
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/18
 */

public class NetaseMethods {

    private final NetaseApi netaseApi;

    private NetaseMethods() {
        netaseApi = APIFactory.getInstance().createNetaseApi();
    }

    private static class SingletonHolder {
        private static final NetaseMethods INSTANCE = new NetaseMethods();
    }

    public static NetaseMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 分页得到公开课数据
     *
     * @param page
     * @return
     */
    public Observable<NetaseHome> getHomeDateByPage(int page) {

        int cursor = 20 * page;

        return netaseApi
                .getHomeDataByCursor("" + cursor)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 通过Id得到详情
     *
     * @param id
     * @return
     */
    public void getContentById(final IRequestCallback<NetaseContent> callback, String id) {

        netaseApi.getContentById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NetaseContent>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(NetaseContent netaseContent) {
                        callback.onSuccess(netaseContent);
                    }
                });
    }

    /**
     * 得到评论
     *
     * @param id
     * @param page
     * @return
     */
    public void getContentComById(final IRequestCallback<ContentCom> callback, String id, int page) {
        int startCount = page * 20;
        netaseApi.getCommentById(id, startCount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<NetaseComment>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callback.onFailure(e);
            }

            @Override
            public void onNext(NetaseComment netaseComment) {
                ContentCom contentCom = new ContentCom();
                List<ContentCom.Comment> contentComs = new ArrayList<>();
                List<NetaseComment.PostBean> posts = netaseComment.getNewPosts();
                for (NetaseComment.PostBean post : posts) {
                    ContentCom.Comment com = new ContentCom.Comment();
                    com.setComTime(post.getFuck().getComTime());
                    com.setComContent(post.getFuck().getComContent());
                    com.setUserName(post.getFuck().getUserName().split("&nbsp;")[0]);
                    com.setLikeNum(post.getFuck().getLikeNum());
                    contentComs.add(com);
                }
                contentCom.setCommentList(contentComs);
                callback.onSuccess(contentCom);
            }
        });
    }


    /**
     * 得到分类
     *
     * @return
     */
    public void getClassify(final IRequestCallback<ClassifyTab> callback) {
        netaseApi
                .getClassify()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NetaseClassify>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(NetaseClassify netaseClassify) {
                        ClassifyTab classifyTab = new ClassifyTab();
                        List<ClassifyTab.TabBean> tabList = new ArrayList<>();
                        for (NetaseClassify.DataBean.ClassifyConfigBean.SourceBean bean
                                : netaseClassify.getData().getClassifyConfig().getSource()) {
                            ClassifyTab.TabBean tabBean = new ClassifyTab.TabBean();
                            tabBean.setId(bean.getId() + "");
                            tabBean.setTitle(bean.getName());
                            tabList.add(tabBean);
                        }
                        for (NetaseClassify.DataBean.ClassifyConfigBean.BaseBean bean
                                : netaseClassify.getData().getClassifyConfig().getBase()) {
                            ClassifyTab.TabBean tabBean = new ClassifyTab.TabBean();
                            tabBean.setId(bean.getId() + "");
                            tabBean.setTitle(bean.getName());
                            tabList.add(tabBean);
                        }
                       /* for (NetaseClassify.DataBean.ClassifyConfigBean.SpecialBean bean
                                : netaseClassify.getData().getClassifyConfig().getSpecial()) {
                            ClassifyTab.TabBean tabBean = new ClassifyTab.TabBean();
                            tabBean.setId(bean.getId() + "");
                            tabBean.setTitle(bean.getName());
                            tabList.add(tabBean);
                        }*/
                        classifyTab.setTabList(tabList);
                        callback.onSuccess(classifyTab);
                    }
                });
    }

    /**
     * 得到某一分类下的具体数据
     *
     * @param callback
     * @param classifyId
     * @param cursor
     */
    public void getDataByClassifyId(final IRequestCallback<ClassifyData> callback, String classifyId, String cursor) {
        netaseApi
                .getDataByClassifyId(classifyId, cursor)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NetaseClassifyData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(NetaseClassifyData netaseClassifyData) {
                        ClassifyData classifyData = new ClassifyData();
                        List<ClassifyItem> classifyItemList = new ArrayList<>();
                        List<NetaseHome.DataBean> dataBeanList = netaseClassifyData.getData();
                        for (NetaseHome.DataBean bean : dataBeanList) {
                            ClassifyItem classifyItem = new ClassifyItem(1, bean.getPlid(), bean.getTitle(), bean.getPicUrl());
                            classifyItemList.add(classifyItem);
                        }
                        classifyData.setClassifyItemList(classifyItemList);
                        callback.onSuccess(classifyData);
                    }
                });
    }


}

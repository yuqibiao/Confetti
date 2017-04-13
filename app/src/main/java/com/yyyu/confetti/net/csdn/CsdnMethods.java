package com.yyyu.confetti.net.csdn;

import com.yyyu.confetti.bean.ClassifyData;
import com.yyyu.confetti.bean.ClassifyItem;
import com.yyyu.confetti.bean.ClassifyTab;
import com.yyyu.confetti.bean.HomeData;
import com.yyyu.confetti.bean.ItemContent;
import com.yyyu.confetti.bean.json.CsdnClassify;
import com.yyyu.confetti.bean.json.CsdnClassifyData;
import com.yyyu.confetti.bean.json.CsdnContent;
import com.yyyu.confetti.bean.json.CsdnHome;
import com.yyyu.confetti.net.APIFactory;
import com.yyyu.confetti.net.IRequestCallback;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 功能：Csdn数据请求
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/26
 */

public class CsdnMethods {

    private final CsdnApi csdnApi;

    private CsdnMethods() {
        csdnApi = APIFactory.getInstance().createCsdnApi();
    }

    private static class SingletonHolder {
        private final static CsdnMethods INSTANCE = new CsdnMethods();
    }

    public static CsdnMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * 得到首页的数据（实质上是Android分类下的数据）
     *
     * @param page
     * @return
     */
    public Observable<CsdnClassifyData> getHomeDataByPage(int page){
        return csdnApi
                .getDataByClassifyId(""+1, page+1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 得到CSDN分类数据
     *
     * @param callback
     */
    public void getClassify(final IRequestCallback<ClassifyTab> callback) {
        csdnApi
                .getClassify()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CsdnClassify>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(CsdnClassify csdnClassify) {
                        ClassifyTab classifyTab = new ClassifyTab();
                        List<ClassifyTab.TabBean> tabList = new ArrayList<>();

                        List<CsdnClassify.DataBean> dataList = csdnClassify.getData();
                        for (CsdnClassify.DataBean bean : dataList) {
                            List<CsdnClassify.DataBean.TagsBean> tags = bean.getTags();
                            for(CsdnClassify.DataBean.TagsBean tag :tags){
                                ClassifyTab.TabBean tabBean = new ClassifyTab.TabBean();
                                tabBean.setId(tag.getId() + "");
                                tabBean.setTitle(tag.getLabel());
                                tabList.add(tabBean);
                            }
                        }
                        classifyTab.setTabList(tabList);
                        callback.onSuccess(classifyTab);
                    }
                });
    }


    /**
     * 通过分类Id得到某一分类下的具体数据
     *
     * @param callback
     * @param comId
     * @param page
     */
    public void getDataByClassifyId(final IRequestCallback<ClassifyData> callback, String comId, int page) {

        csdnApi
                .getDataByClassifyId(comId, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CsdnClassifyData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(CsdnClassifyData csdnClassifyData) {

                        List<CsdnHome.DataBean> data = csdnClassifyData.getData();
                        ClassifyData classifyData = new ClassifyData();
                        List<ClassifyItem> classifyItemList = new ArrayList<>();
                        for (CsdnHome.DataBean bean : data) {
                            ClassifyItem item = new ClassifyItem(3, bean.getArticleId()+ "", bean.getTitle(), bean.getAvatar());
                            item.setSubTitle(bean.getDescription()+"");
                            item.setUsername(bean.getUserName());
                            item.setTime(bean.getPostTime());
                            classifyItemList.add(item);
                        }
                        classifyData.setClassifyItemList(classifyItemList);
                        callback.onSuccess(classifyData);
                    }
                });

    }

    /**
     * 通过Id得到文章的具体内容
     *
     * @param callback
     * @param id
     */
    public void getContentById(final IRequestCallback<ItemContent> callback, final String id  , String username) {

        csdnApi
                .getContentById(id , username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CsdnContent>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(CsdnContent csdnContent) {
                        ItemContent itemContent = new ItemContent();
                        CsdnContent.DataBean dataBean = csdnContent.getData();
                        itemContent.setId(dataBean.getId()+"");
                        itemContent.setTitle(dataBean.getTitle());
                        itemContent.setIconPath(dataBean.getAvatar());
                        itemContent.setBody(dataBean.getContent());
                        callback.onSuccess(itemContent);

                    }
                });

    }


}

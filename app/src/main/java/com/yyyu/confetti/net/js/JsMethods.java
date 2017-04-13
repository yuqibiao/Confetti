package com.yyyu.confetti.net.js;


import android.text.TextUtils;
import android.util.Log;

import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.confetti.bean.ClassifyData;
import com.yyyu.confetti.bean.ClassifyItem;
import com.yyyu.confetti.bean.ClassifyTab;
import com.yyyu.confetti.bean.HomeData;
import com.yyyu.confetti.bean.HomeItem;
import com.yyyu.confetti.bean.ItemContent;
import com.yyyu.confetti.bean.json.JsClassifyData;
import com.yyyu.confetti.bean.json.JsItemBean;
import com.yyyu.confetti.net.APIFactory;
import com.yyyu.confetti.net.IRequestCallback;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * 功能：
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/28
 */

public class JsMethods implements JsApi {

    private static final String TAG = "JsMethods";
    private Elements elements;


    private JsMethods() {

    }


    @Override
    public Observable<HomeData> getHomeDataByPage(final int page) {

        Observable<HomeData> observable = Observable
                .create(new Observable.OnSubscribe<HomeData>() {
                    @Override
                    public void call(final Subscriber<? super HomeData> subscriber) {
                        getDataByClassifyId(new IRequestCallback<ClassifyData>() {
                            @Override
                            public void onSuccess(ClassifyData result) {

                                MyLog.e(TAG , "JSMethods====success");

                                HomeData homeData = new HomeData();
                                List<ClassifyItem> classifyItemList = result.getClassifyItemList();
                                List<HomeItem> homeItemList = new ArrayList<>();
                                for (ClassifyItem classifyItem : classifyItemList) {
                                    homeItemList.add(classifyItem);
                                }
                                homeData.setHomeDataList(homeItemList);
                                subscriber.onNext(homeData);
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onFailure(Throwable throwable) {

                                throwable.printStackTrace();
                                subscriber.onError(throwable);
                            }
                        }, "3fde3b545a35", page);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return observable;
    }

    @Override
    public void getClassify(final IRequestCallback<ClassifyTab> callback) {

        ClassifyTab classifyTab = new ClassifyTab();
        List<ClassifyTab.TabBean> tabList = new ArrayList<>();
        ClassifyTab.TabBean tabBean0 = new ClassifyTab.TabBean("3fde3b545a35", "Android知识");
        ClassifyTab.TabBean tabBean1 = new ClassifyTab.TabBean("NEt52a", "程序员");
        ClassifyTab.TabBean tabBean2 = new ClassifyTab.TabBean("V2CqjW", "@IT·互联网");
        ClassifyTab.TabBean tabBean3 = new ClassifyTab.TabBean("71fc832a4492", "随笔集");
        ClassifyTab.TabBean tabBean4 = new ClassifyTab.TabBean("1e050b63cf15", "读书笔记");
        ClassifyTab.TabBean tabBean5 = new ClassifyTab.TabBean("93d58e9169cb", "互联网科技");
        ClassifyTab.TabBean tabBean6 = new ClassifyTab.TabBean("3233d1a249ca", "iOS Developer");
        ClassifyTab.TabBean tabBean7 = new ClassifyTab.TabBean("f489ec955505", "Web前端之路");
        ClassifyTab.TabBean tabBean8 = new ClassifyTab.TabBean("ef7836bf3e22", "Java技术文章");
        tabList.add(tabBean0);
        tabList.add(tabBean1);
        tabList.add(tabBean2);
        tabList.add(tabBean3);
        tabList.add(tabBean4);
        tabList.add(tabBean5);
        tabList.add(tabBean6);
        tabList.add(tabBean7);
        tabList.add(tabBean8);
        classifyTab.setTabList(tabList);

        callback.onSuccess(classifyTab);
        Observable.just(classifyTab).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ClassifyTab>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ClassifyTab data) {
                callback.onSuccess(data);
            }
        });


    }

    @Override
    public void getDataByClassifyId(final IRequestCallback<ClassifyData> callback, final String classifyId, int page) {

        final String url = "http://www.jianshu.com/c/" + classifyId + "?order_by=commented_at&page=" + page;

        final ClassifyData jsClassifyData = new ClassifyData();
        final List<ClassifyItem> classifyItemList = new ArrayList<>();

        Observable
                .create(new Observable.OnSubscribe<Elements>() {
                    @Override
                    public void call(Subscriber<? super Elements> subscriber) {
                        try {
                            Document document = Jsoup.connect(url).timeout(5000).get();
                            elements = document.select("ul.note-list").select("li");
                            subscriber.onNext(elements);
                        } catch (IOException e) {
                            e.printStackTrace();
                            callback.onFailure(e);
                            e.printStackTrace();
                        }
                    }
                })
                .flatMap(new Func1<Elements, Observable<Element>>() {
                    @Override
                    public Observable<Element> call(Elements elements) {
                        return Observable.from(elements);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Element>() {

                    private  int index =0;

                    @Override
                    public void onCompleted() {
                        jsClassifyData.setClassifyItemList(classifyItemList);
                        callback.onSuccess(jsClassifyData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(Element element) {
                        index++;
                        int type = 2;
                        String id = element.select("a.title").attr("abs:href");
                        String title = element.select("a.title").text();
                        String avatarImg = element.select("a.avatar").select("img").attr("src");
                        String imgPath = "http:" + avatarImg.split("[?]")[0];
                        ClassifyItem classifyItem = new ClassifyItem(type, id, title, imgPath);
                        classifyItem.setTime(formatTime(element.select("span.time").attr("data-shared-at")));
                        classifyItem.setSubTitle(element.select("p.abstract").text());
                        classifyItem.setUsername(element.select("div.name").text());
                        String primaryImg = element.select("a.wrap-img").select("img").attr("src");
                        if (!TextUtils.isEmpty(primaryImg)) {
                            classifyItem.setContentIconPath("http:" + primaryImg.split("[?]")[0]);
                        }
                        classifyItemList.add(classifyItem);
                        if(index == elements.size()){
                            onCompleted();
                        }
                    }
                });

    }

    @Override
    public void getContentById(final IRequestCallback<ItemContent> callback, String id) {

        final String url = id;//直接传一个url过来

        Observable
                .create(new Observable.OnSubscribe<Elements>() {
                    @Override
                    public void call(Subscriber<? super Elements> subscriber) {
                        try {
                            Document document = Jsoup.connect(url)
                                    .timeout(10000)
                                    .get();
                            Elements elements = document.select("body");
                            subscriber.onNext(elements);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<Elements, Observable<Element>>() {
                    @Override
                    public Observable<Element> call(Elements elements) {
                        return Observable.from(elements);
                    }
                }).take(1)
                .subscribe(new Subscriber<Element>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(Element element) {
                        String title = element.select("h1.title").text();
                        String iconPath = element.select("div.image-package").select("img").attr("src");
                        String body = element.select("div.show-content").toString();
                        ItemContent content = new ItemContent();
                        content.setTitle(title);
                        if (!TextUtils.isEmpty(iconPath)) {
                            content.setIconPath(iconPath.split("[?]")[0]);
                        }
                        content.setBody(body);
                        callback.onSuccess(content);
                    }
                });


    }

    private String formatTime(String time) {
        String[] ts = time.split("T");
        String[] split = ts[1].split("\\+");
        return ts[0] + " " + split[0];
    }


    static class SingletonHolder {
        static final JsMethods INSTANCE = new JsMethods();
    }

    public static JsMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }


}

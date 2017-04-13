package com.yyyu.confetti.net;

import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.confetti.bean.ClassifyData;
import com.yyyu.confetti.bean.ClassifyItem;
import com.yyyu.confetti.bean.ClassifyTab;
import com.yyyu.confetti.bean.ContentCom;
import com.yyyu.confetti.bean.HomeData;
import com.yyyu.confetti.bean.HomeItem;
import com.yyyu.confetti.bean.HomeTop;
import com.yyyu.confetti.bean.ItemContent;
import com.yyyu.confetti.bean.json.CsdnClassifyData;
import com.yyyu.confetti.bean.json.CsdnHome;
import com.yyyu.confetti.bean.json.NetaseContent;
import com.yyyu.confetti.bean.json.NetaseHome;
import com.yyyu.confetti.bean.json.ZhihuHome;
import com.yyyu.confetti.net.csdn.CsdnMethods;
import com.yyyu.confetti.net.js.JsMethods;
import com.yyyu.confetti.net.netase.NetaseMethods;
import com.yyyu.confetti.net.zhihu.ZhihuMethods;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 功能：网络请求API的统一管理类，和其它组件进行交互
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/18
 */

public class APIMethodManager {

    private static final String TAG = "APIMethodManager";

    private final ZhihuMethods zhihuMethods;
    private final NetaseMethods netaseMthods;
    private final CsdnMethods csdnMethods;
    private final JsMethods jsMethods;

    private APIMethodManager() {
        zhihuMethods = ZhihuMethods.getInstance();
        netaseMthods = NetaseMethods.getInstance();
        jsMethods = JsMethods.getInstance();
        csdnMethods = CsdnMethods.getInstance();

    }

    private static class SingletonHolder {
        private static final APIMethodManager INSTANCE = new APIMethodManager();
    }

    public static APIMethodManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 轮播图 Top数据
     *
     * @param callback
     */
    public void getTopData(IRequestCallback<HomeTop> callback) {
        zhihuMethods.getTopData(callback);
    }

    /**
     * 得到某一分类下的数据
     *
     * @param callback
     * @param type       0:知乎日报 1:网易公开课 2：简书 3：CSDN
     * @param classifyId 分类Id
     * @param page
     */
    public void getDataByClassifyId(IRequestCallback<ClassifyData> callback, int type, String classifyId, int page) {

        switch (type) {
            case 0:
                zhihuMethods.getDataByClassifyId(callback, classifyId);
                break;
            case 1:
                netaseMthods.getDataByClassifyId(callback, classifyId, page * 20 + "");
                break;
            case 2:
                jsMethods.getDataByClassifyId(callback, classifyId, page);
                break;
            case 3:
                page++;//CSDN的page是从1开始的
                csdnMethods.getDataByClassifyId(callback, classifyId, page);
                break;
        }

    }


    /**
     * 得到分类
     *
     * @param callback
     * @param type     0:知乎日报 1:网易公开课 2：简书 3：CSDN
     */
    public void getClassify(final IRequestCallback<ClassifyTab> callback, int type) {

        switch (type) {
            case 0:
                zhihuMethods.getClassify(callback);
                break;
            case 1:
                netaseMthods.getClassify(callback);
                break;
            case 2:
                jsMethods.getClassify(callback);
                break;
            case 3:
                csdnMethods.getClassify(callback);
                break;

        }

    }


    /**
     * 得到评论
     *
     * @param callback
     * @param id       0:知乎日报 1:网易公开课 2：简书 3：CSDN
     * @param type
     * @param page     当前页
     */
    public void getContentComById(final IRequestCallback<ContentCom> callback, int type, String id, final int page) {

        switch (type) {
            case 0:
                zhihuMethods.getContentComById(callback, id);
                break;
            case 1:
                netaseMthods.getContentComById(callback, id, page);
                break;
            case 2:
                break;
            case 3:

                break;
        }

    }

    /**
     * 根据Id得到网易公开课的详情（和其它三个的格式差太多，所以分开处理算了）
     *
     * @param callback
     * @param id
     */
    public void getNetaseContentById(final IRequestCallback<NetaseContent> callback, String id) {
        netaseMthods.getContentById(callback, id);
    }

    /**
     * 得到内容
     *
     * @param callback
     * @param type     0:知乎日报 1:网易公开课 2：简书 3：CSDN
     * @param id       对应id
     */
    public void getContentById(final IRequestCallback<ItemContent> callback, int type, String id, String username) {

        switch (type) {
            case 0:
                zhihuMethods.getContentById(callback, id);
                break;
            case 1://差距太大，分离单独写了 getNetaseContentById
                break;
            case 2:
                jsMethods.getContentById(callback, id);
                break;
            case 3:
                csdnMethods.getContentById(callback, id, username);
                break;
            case 4:
                break;
        }
    }

    /**
     * 得到主页的数据
     *
     * @param iRequestCallback
     * @param page             当前页
     */
    public void getHomeDataByPage(final IRequestCallback<HomeData> iRequestCallback, final int page) {

        Observable<ZhihuHome> zhihuObservable = zhihuMethods.getHomeDataByPage(page);
        Observable<NetaseHome> netaseObservable = netaseMthods.getHomeDateByPage(page);
        Observable<CsdnClassifyData> csdnObservable = csdnMethods.getHomeDataByPage(page);
        Observable<HomeData> jsObservable = jsMethods.getHomeDataByPage(page);
        final HomeData homData = new HomeData();
        final List<HomeItem> homeItemList = new ArrayList<>();
        final List<HomeItem> zhihuItemList = new ArrayList<>();
        final List<HomeItem> netaseItemList = new ArrayList<>();
        final List<HomeItem> jsItemList = new ArrayList<>();
        final List<HomeItem> csdnItemList = new ArrayList<>();


        Observable
                .concat(jsObservable , zhihuObservable, netaseObservable )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                        HomeItem netaseItem = new HomeItem(100 , "" , "网易公开课" , "");
                        homeItemList.add(netaseItem);
                        if(netaseItemList.size()>=6){
                            List<HomeItem> temp = netaseItemList.subList(0,6);
                            homeItemList.addAll(temp);
                        }

                        HomeItem zhrbItem = new HomeItem(100 , "" , "知乎日报" , "");
                        homeItemList.add(zhrbItem);
                        if(netaseItemList.size()>=6){
                            List<HomeItem> temp = zhihuItemList.subList(0,4);
                            homeItemList.addAll(temp);
                        }

                        HomeItem jsItem = new HomeItem(100 , "" , "简书推荐" , "");
                        homeItemList.add(jsItem);
                        if(netaseItemList.size()>=6){
                            List<HomeItem> temp = jsItemList.subList(0,4);
                            homeItemList.addAll(temp);
                        }
/*
                        HomeItem csdnItem = new HomeItem(100 , "" , "CSDN推荐" , "");
                        homeItemList.add(csdnItem);
                        if(netaseItemList.size()>=6){
                            List<HomeItem> temp = csdnItemList.subList(0,8);
                            homeItemList.addAll(temp);
                        }*/

                        homData.setHomeDataList(homeItemList);
                        iRequestCallback.onSuccess(homData);

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        MyLog.e(TAG , "错误===="+e.getMessage());
                        iRequestCallback.onFailure(e);
                    }

                    @Override
                    public void onNext(Object o) {
                        if (o instanceof ZhihuHome) {
                            ZhihuHome zhihuHome = (ZhihuHome) o;
                            List<ZhihuHome.StoriesBean> stories = zhihuHome.getStories();
                            for (ZhihuHome.StoriesBean story : stories) {
                                int type = 0;
                                int id = story.getId();
                                String title = story.getTitle();
                                String imgPath = story.getImages() != null && story.getImages().size() >= 1 ? story.getImages().get(0) : null;
                                HomeItem item = new HomeItem(type, "" + id, title, imgPath);
                                zhihuItemList.add(item);
                            }
                        } else if (o instanceof NetaseHome) {
                            NetaseHome netaseHome = (NetaseHome) o;
                            List<NetaseHome.DataBean> dataList = netaseHome.getData();
                            for (NetaseHome.DataBean bean : dataList) {
                                int type = 1;
                                String id = bean.getPlid();
                                String title = bean.getTitle();
                                String imgPath = bean.getPicUrl();
                                HomeItem item = new HomeItem(type, id, title, imgPath);
                                item.setSubTitle(bean.getCourseType());
                                netaseItemList.add(item);
                            }
                        }else if(o instanceof  HomeData){
                            jsItemList.addAll((( HomeData )o).getHomeDataList());
                        }
                        else if (o instanceof CsdnClassifyData) {
                            CsdnClassifyData csdnClassifyData = (CsdnClassifyData) o;
                            List<CsdnHome.DataBean> dataList = csdnClassifyData.getData();
                            for (CsdnHome.DataBean bean : dataList) {
                                HomeItem item = new ClassifyItem(3, bean.getArticleId() + "", bean.getTitle(), bean.getAvatar());
                                item.setSubTitle(bean.getDescription() + "");
                                item.setUsername(bean.getUserName());
                                item.setTime(bean.getPostTime());
                                csdnItemList.add(item);
                            }
                        }
                    }
                });

    }


}

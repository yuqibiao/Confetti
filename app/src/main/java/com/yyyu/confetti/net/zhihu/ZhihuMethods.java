package com.yyyu.confetti.net.zhihu;

import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.baselibrary.utils.MyTimeUtils;
import com.yyyu.confetti.bean.ClassifyData;
import com.yyyu.confetti.bean.ClassifyItem;
import com.yyyu.confetti.bean.ClassifyTab;
import com.yyyu.confetti.bean.ContentCom;
import com.yyyu.confetti.bean.HomeData;
import com.yyyu.confetti.bean.HomeItem;
import com.yyyu.confetti.bean.HomeTop;
import com.yyyu.confetti.bean.ItemContent;
import com.yyyu.confetti.bean.json.NetaseClassifyData;
import com.yyyu.confetti.bean.json.NetaseHome;
import com.yyyu.confetti.bean.json.ZhihuClassify;
import com.yyyu.confetti.bean.json.ZhihuClassifyData;
import com.yyyu.confetti.bean.json.ZhihuComment;
import com.yyyu.confetti.bean.json.ZhihuContent;
import com.yyyu.confetti.bean.json.ZhihuHome;
import com.yyyu.confetti.bean.json.ZhihuTop;
import com.yyyu.confetti.net.APIFactory;
import com.yyyu.confetti.net.IRequestCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 功能：知乎日报数据请求
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/14
 */

public class ZhihuMethods {


    private final ZhihuApi zhihuApi;

    private ZhihuMethods() {
        zhihuApi = APIFactory.getInstance().createZhihuApi();
    }

    private static class SingletonHolder {
        private final static ZhihuMethods INSTANCE = new ZhihuMethods();
    }

    public static ZhihuMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void getTopData(final IRequestCallback<HomeTop> callback) {

        zhihuApi
                .getTopData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhihuTop>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(ZhihuTop zhihuTop) {
                        HomeTop top = new HomeTop();
                        List<HomeItem> topData = new ArrayList<>();
                        List<ZhihuTop.TopStoriesBean> topStories = zhihuTop.getTop_stories();
                        for (ZhihuTop.TopStoriesBean story : topStories) {
                            HomeItem item = new HomeItem(0, story.getId() + "", story.getTitle(), story.getImage());
                            topData.add(item);
                        }
                        top.setTopData(topData);
                        callback.onSuccess(top);
                    }
                });

    }

    /**
     * 分页得到知乎首页数据
     *
     * @param page
     * @return
     */
    public Observable<ZhihuHome> getHomeDataByPage(int page) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        calendar.add(Calendar.DATE, -page);
        String dateStr = dateFormat.format(calendar.getTime());

        return zhihuApi
                .getHomeDataByDate(dateStr)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 通过Id得到具体内容
     *
     * @param id
     * @return
     */
    public void getContentById(final IRequestCallback<ItemContent> callback, String id) {

        final ItemContent content = new ItemContent();
        zhihuApi
                .getContentById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhihuContent>() {
                    @Override
                    public void onCompleted() {
                        callback.onSuccess(content);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(ZhihuContent zhihuContent) {
                        content.setId(zhihuContent.getId() + "");
                        content.setTitle(zhihuContent.getTitle());
                        List<String> imgs = zhihuContent.getImages();
                        if (imgs != null && imgs.size() > 0) {
                            content.setIconPath(imgs.get(0));
                        }
                        content.setBody(zhihuContent.getBody());
                    }
                });

    }


    /**
     * 得到内容的评论
     *
     * @param callback
     * @param contentId
     */
    public void getContentComById(final IRequestCallback<ContentCom> callback, String contentId) {
        zhihuApi
                .getContentComById(contentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhihuComment>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(ZhihuComment zhihuComment) {
                        ContentCom contentCom = new ContentCom();
                        List<ContentCom.Comment> commentList = new ArrayList<>();
                        List<ZhihuComment.CommentsBean> beanList = zhihuComment.getComments();
                        for (ZhihuComment.CommentsBean bean : beanList) {
                            ContentCom.Comment comment = new ContentCom.Comment();
                            comment.setComId(bean.getId() + "");
                            comment.setComContent(bean.getContent());
                            comment.setComTime(MyTimeUtils.formatDateDT(bean.getTime() * 1000L));
                            comment.setLikeNum(bean.getLikes());
                            comment.setUserIcon(bean.getAvatar());
                            comment.setUserName(bean.getAuthor());
                            commentList.add(comment);
                        }
                        contentCom.setCommentList(commentList);
                        callback.onSuccess(contentCom);
                    }
                });
    }


    /**
     * 得到分类
     *
     * @param callback
     */
    public void getClassify(final IRequestCallback<ClassifyTab> callback) {
        zhihuApi
                .getClassify()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhihuClassify>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(ZhihuClassify zhihuClassify) {
                        ClassifyTab classifyTab = new ClassifyTab();
                        List<ClassifyTab.TabBean> tabList = new ArrayList<>();
                        List<ZhihuClassify.OthersBean> othersBeanList = zhihuClassify.getOthers();
                        for (ZhihuClassify.OthersBean bean : othersBeanList) {
                            ClassifyTab.TabBean tabBean = new ClassifyTab.TabBean();
                            tabBean.setId(bean.getId() + "");
                            tabBean.setTitle(bean.getName());
                            tabList.add(tabBean);
                        }
                        classifyTab.setTabList(tabList);
                        callback.onSuccess(classifyTab);
                    }
                });
    }

    /**
     * 得到某一分类下的数据
     * <p>
     * 没有分页（接口的原因）
     *
     * @param callback
     * @param classifyId
     */
    public void getDataByClassifyId(final IRequestCallback<ClassifyData> callback, String classifyId) {

        zhihuApi.getDataByClassifyId(classifyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhihuClassifyData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }

                    @Override
                    public void onNext(ZhihuClassifyData zhihuClassifyData) {
                        ClassifyData classifyData = new ClassifyData();
                        List<ClassifyItem> classifyItemList = new ArrayList<>();
                        List<ZhihuHome.StoriesBean> beenList = zhihuClassifyData.getStories();
                        for (ZhihuHome.StoriesBean bean : beenList) {
                            String imgPath = bean.getImages() != null && bean.getImages().size() >= 1 ? bean.getImages().get(0) : null;
                            ClassifyItem classifyItem = new ClassifyItem(1, bean.getId() + "", bean.getTitle(), imgPath);
                            classifyItemList.add(classifyItem);
                        }
                        classifyData.setClassifyItemList(classifyItemList);
                        callback.onSuccess(classifyData);
                    }
                });

    }


}

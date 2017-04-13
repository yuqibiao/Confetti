package com.yyyu.confetti.presenter;

import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.baselibrary.utils.MySnackBar;
import com.yyyu.confetti.bean.ClassifyData;
import com.yyyu.confetti.model.ClassifyItemBiz;
import com.yyyu.confetti.model.inter.IClassifyItemBiz;
import com.yyyu.confetti.net.IRequestCallback;
import com.yyyu.confetti.presenter.inter.IClassifyItemFragPresenter;
import com.yyyu.confetti.view.inter.IClassifyItemFrgView;

/**
 * 功能：
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/24
 */

public class ClassifyItemFragPresenter implements IClassifyItemFragPresenter{

    private static final String TAG = "ClassifyItemFragPresent";

    private IClassifyItemFrgView classifyItemFrgView;
    private IClassifyItemBiz classifyItemBiz;

    public ClassifyItemFragPresenter(IClassifyItemFrgView classifyItemFrgView){
        this.classifyItemFrgView = classifyItemFrgView;
        classifyItemBiz = new ClassifyItemBiz();
    }

    private int  pageNow=0;

    @Override
    public void getData(final int getDataType, int type  , String tabId) {

        classifyItemFrgView.showProgress();

        if(getDataType==0){
            pageNow = 0;
        }else if(getDataType ==1){
            pageNow++;
        }

        classifyItemBiz.getData(new IRequestCallback<ClassifyData>() {
            @Override
            public void onSuccess(ClassifyData result) {
                if(getDataType ==0){
                    classifyItemFrgView.onRefresh(result);
                    classifyItemFrgView.onRefreshFinished();
                }else if(getDataType==1){
                    classifyItemFrgView.onLoadMore(result);
                    classifyItemFrgView.onLoadMoreFinished();
                }
                classifyItemFrgView.hiddenProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                //--do error thing
                throwable.printStackTrace();
                MyLog.e(TAG , "出错==="+throwable.getMessage());
            }
        }, type, tabId , pageNow);

    }


}

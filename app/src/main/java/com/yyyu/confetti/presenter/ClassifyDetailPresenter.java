package com.yyyu.confetti.presenter;

import com.yyyu.confetti.bean.ClassifyTab;
import com.yyyu.confetti.model.ClassifyDetailFragBiz;
import com.yyyu.confetti.model.inter.IClassifyDetailFragBiz;
import com.yyyu.confetti.net.IRequestCallback;
import com.yyyu.confetti.presenter.inter.IClassifyDetailFrgPresenter;
import com.yyyu.confetti.presenter.inter.IContentActPresenter;
import com.yyyu.confetti.view.inter.IClassifyDetailFrgView;

/**
 * 功能：对应ClassifyDetailFragment
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/24
 */

public class ClassifyDetailPresenter implements IClassifyDetailFrgPresenter{

    private IClassifyDetailFrgView classifyDetailFrgView;
    private IClassifyDetailFragBiz classifyDetailFragBiz;

    public ClassifyDetailPresenter(IClassifyDetailFrgView classifyDetailFrgView){
        this.classifyDetailFrgView = classifyDetailFrgView;
        classifyDetailFragBiz = new ClassifyDetailFragBiz();
    }


    @Override
    public void getData(int type) {
        classifyDetailFragBiz.getData(new IRequestCallback<ClassifyTab>() {
            @Override
            public void onSuccess(ClassifyTab result) {
                classifyDetailFrgView.setData(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                classifyDetailFrgView.onLoadDataFailed(throwable.getMessage());
            }
        } , type);
    }
}

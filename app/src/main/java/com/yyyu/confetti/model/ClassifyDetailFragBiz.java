package com.yyyu.confetti.model;

import com.yyyu.confetti.bean.ClassifyTab;
import com.yyyu.confetti.model.inter.IClassifyDetailFragBiz;
import com.yyyu.confetti.net.APIMethodManager;
import com.yyyu.confetti.net.IRequestCallback;

/**
 * 功能：对应ClassifyDetailFragment
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/24
 */

public class ClassifyDetailFragBiz implements IClassifyDetailFragBiz{

    private APIMethodManager manager;

    public ClassifyDetailFragBiz(){
        manager = APIMethodManager.getInstance();
    }

    @Override
    public void getData(IRequestCallback<ClassifyTab> callback, int type) {
        manager.getClassify(callback ,type);
    }

}

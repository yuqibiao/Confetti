package com.yyyu.confetti.model;

import com.yyyu.confetti.bean.ClassifyData;
import com.yyyu.confetti.model.inter.IClassifyItemBiz;
import com.yyyu.confetti.net.APIMethodManager;
import com.yyyu.confetti.net.IRequestCallback;

/**
 * 功能：对应ClassifyItemFragment
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/24
 */

public class ClassifyItemBiz implements IClassifyItemBiz{

    private APIMethodManager apiMethodManager;

    public ClassifyItemBiz(){
        apiMethodManager = APIMethodManager.getInstance();
    }

    @Override
    public void getData(IRequestCallback<ClassifyData> callback, int type, String tabId, int page) {
        apiMethodManager.getDataByClassifyId(callback , type , tabId , page);
    }
}

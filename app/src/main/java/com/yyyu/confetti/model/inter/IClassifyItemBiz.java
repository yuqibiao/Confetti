package com.yyyu.confetti.model.inter;

import com.yyyu.confetti.bean.ClassifyData;
import com.yyyu.confetti.net.IRequestCallback;

/**
 * 功能：对应ClassifyItemFragment
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/24
 */

public interface IClassifyItemBiz {

    void getData(IRequestCallback<ClassifyData> callback , int type , String tabId , int page);

}

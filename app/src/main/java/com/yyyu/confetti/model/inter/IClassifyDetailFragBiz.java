package com.yyyu.confetti.model.inter;

import com.yyyu.confetti.bean.ClassifyTab;
import com.yyyu.confetti.net.IRequestCallback;

/**
 * 功能：对应ClassifyDetailFragment
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/24
 */

public interface IClassifyDetailFragBiz {

    void getData(IRequestCallback<ClassifyTab> callback , int type);

}

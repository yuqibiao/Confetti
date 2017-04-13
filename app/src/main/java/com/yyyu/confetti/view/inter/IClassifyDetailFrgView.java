package com.yyyu.confetti.view.inter;

import com.yyyu.confetti.bean.ClassifyTab;

/**
 * 功能：
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/24
 */

public interface IClassifyDetailFrgView extends IBaseView {

    void setData(ClassifyTab result);

    void onLoadDataFailed(String tips);

}

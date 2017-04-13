package com.yyyu.confetti.view.inter;

import com.yyyu.confetti.bean.ContentCom;
import com.yyyu.confetti.bean.json.NetaseContent;

/**
 * 功能：对应NetaseContentActivity
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/22
 */

public interface INetaseContentView {

    void setData(NetaseContent netaseContent);

    void setComment(ContentCom contentCom);

}

package com.yyyu.confetti.adapter;

import android.content.Context;

import com.yyyu.confetti.R;
import com.yyyu.confetti.bean.ClassifyItem;

import java.util.List;

/**
 * 功能：分类中简书Item对应的Adapter
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/29
 */

public class JsItemAdapter extends BaseClassifyItemAdapter{

    public JsItemAdapter(Context context, List<ClassifyItem> data) {
        super(context, data);
    }

    @Override
    protected int getViewId() {
        return R.layout.rv_item_js;
    }
}

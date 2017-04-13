package com.yyyu.confetti.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yyyu.confetti.R;
import com.yyyu.confetti.bean.ClassifyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：分类中网易公开课Item对应的Adapter
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/24
 */

public class ZhihuItemAdapter extends BaseClassifyItemAdapter{

    public ZhihuItemAdapter(Context context, List<ClassifyItem> data) {
        super(context, data);
    }

    @Override
    protected int getViewId() {
        return R.layout.rv_item_zhrb;
    }
}

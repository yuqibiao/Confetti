package com.yyyu.confetti.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yyyu.baselibrary.view.ViewpagerLooper;
import com.yyyu.confetti.R;
import com.yyyu.confetti.bean.HomeItem;
import com.yyyu.confetti.bean.HomeTopData;
import com.yyyu.confetti.view.activity.ContentShowActivity;

import java.util.List;

/**
 * 功能：首页 对应Header
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/25
 */

public class HomeHeaderVpAdapter extends ViewpagerLooper.LooperPagerAdapter<HomeItem>{

    private Context mContext;

    public HomeHeaderVpAdapter(Context context, List<HomeItem> data, int containerId) {
        super(context, data, containerId);
        this.mContext = context;
    }

    @Override
    protected void setView(View viewItem, final HomeItem dataBean) {
        final ImageView ivTop = (ImageView) viewItem.findViewById(R.id.iv_top);
        Glide.with(mContext).load(dataBean.getImgPath()).into(ivTop);
        ivTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mContext instanceof Activity){
                    ContentShowActivity.startAction((Activity) mContext , 0 , dataBean.getId());
                }
            }
        });
    }

}

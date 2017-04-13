package com.yyyu.confetti.model;

import com.yyyu.confetti.bean.CollBean;
import com.yyyu.confetti.model.inter.ICollBeanBiz;
import com.yyyu.confetti.net.IRequestCallback;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * 功能：收藏文章业务逻辑实现
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/4/5
 */

public class CollBeanBiz implements ICollBeanBiz{
    @Override
    public void saveColl(final CollBean collBean, final IRequestCallback<CollBean> callback) {
        collBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    callback.onSuccess(collBean);
                }else{
                    callback.onFailure(e);
                }
            }
        });
    }

    @Override
    public void getCollByCateId(String cateId, final IRequestCallback<List<CollBean>> callback) {
        BmobQuery<CollBean> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("cateId" , cateId);
        bmobQuery.findObjects(new FindListener<CollBean>() {
            @Override
            public void done(List<CollBean> list, BmobException e) {
                if(e==null){
                    callback.onSuccess(list);
                }else{
                    callback.onFailure(e);
                }
            }
        });
    }
}

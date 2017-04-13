package com.yyyu.confetti.model;

import com.yyyu.confetti.bean.CollCate;
import com.yyyu.confetti.model.inter.ICollCateBiz;
import com.yyyu.confetti.net.IRequestCallback;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * 功能：收藏分类业务逻辑
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/4/5
 */

public class CollCateBiz implements ICollCateBiz{


    @Override
    public void saveCollCate(final CollCate collCate, final IRequestCallback<CollCate> callback) {
        collCate.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){
                    callback.onSuccess(collCate);
                }else{
                    callback.onFailure(e);
                }
            }
        });
    }

    @Override
    public void getAllCollCate(String userId , final IRequestCallback<List<CollCate>> callback) {
        BmobQuery<CollCate> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("userId" ,userId );
        bmobQuery.findObjects(new FindListener<CollCate>() {
            @Override
            public void done(List<CollCate> list, BmobException e) {
                if(e==null){
                    callback.onSuccess(list);
                }else{
                    callback.onFailure(e);
                }
            }
        });
    }

}

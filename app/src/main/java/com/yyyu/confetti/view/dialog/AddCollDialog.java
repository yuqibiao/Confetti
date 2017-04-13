package com.yyyu.confetti.view.dialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yyyu.baselibrary.utils.MySnackBar;
import com.yyyu.baselibrary.utils.MyToast;
import com.yyyu.baselibrary.utils.WindowUtils;
import com.yyyu.baselibrary.view.StereoView;
import com.yyyu.baselibrary.view.recyclerview.listener.OnRvItemClickListener;
import com.yyyu.confetti.MyApplication;
import com.yyyu.confetti.R;
import com.yyyu.confetti.adapter.CateSelectAdapter;
import com.yyyu.confetti.bean.CollBean;
import com.yyyu.confetti.bean.CollCate;
import com.yyyu.confetti.bean.UserInfo;
import com.yyyu.confetti.model.CollBeanBiz;
import com.yyyu.confetti.model.CollCateBiz;
import com.yyyu.confetti.model.inter.ICollBeanBiz;
import com.yyyu.confetti.model.inter.ICollCateBiz;
import com.yyyu.confetti.net.IRequestCallback;

import java.util.List;

/**
 * 功能：
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/4/4
 */

public class AddCollDialog extends BaseBottomDialog {

    private TextView tvToCreateFolder;
    private EditText etCollName;
    private EditText etCollUrl;
    private RecyclerView rvCate;
    private TextView tvAddColl;
    private StereoView svColl;
    private TextView tvToAddColl;
    private TextView tvAddFolder;
    private EditText etFolderTitle;
    private EditText etFolderIntro;
    private CateSelectAdapter adapter;
    private ICollBeanBiz collBeanBiz;
    private ICollCateBiz collCateBiz;
    private List<CollCate> collCateList;
    private UserInfo userInfo;

    public AddCollDialog(Context context) {
        super(context, WindowUtils.getSize(context)[1] / 2, WindowUtils.getSize(context)[1] , null);

    }


    @Override
    public int getLayoutId() {
        return R.layout.dialog_add_coll;
    }

    @Override
    protected void initView() {
        svColl = (StereoView) bottomView.findViewById(R.id.sv_coll);
        tvToCreateFolder = (TextView) bottomView.findViewById(R.id.tv_to_create_folder);
        tvAddColl = (TextView) bottomView.findViewById(R.id.tv_add_coll);
        etCollName = (EditText) bottomView.findViewById(R.id.et_coll_title);
        etCollUrl = (EditText) bottomView.findViewById(R.id.et_coll_url);

        tvToAddColl = (TextView) bottomView.findViewById(R.id.tv_to_add_coll);
        tvAddFolder = (TextView) bottomView.findViewById(R.id.tv_add_folder);
        etFolderTitle = (EditText) bottomView.findViewById(R.id.et_folder_title);
        etFolderIntro = (EditText) bottomView.findViewById(R.id.et_folder_intro);

        rvCate = (RecyclerView) bottomView.findViewById(R.id.rv_cate);
        rvCate.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initData() {
        userInfo = ((MyApplication)mContext.getApplicationContext()).getUserInfo();
        collCateBiz = new CollCateBiz();
        collBeanBiz = new CollBeanBiz();
        collCateBiz.getAllCollCate(userInfo.getObjectId() , new IRequestCallback<List<CollCate>>() {

            @Override
            public void onSuccess(List<CollCate> result) {
                collCateList = result;
                adapter = new CateSelectAdapter(mContext, result);
                rvCate.setAdapter(adapter);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    @Override
    protected void initListener() {

        //---选择分类
        rvCate.addOnItemTouchListener(new OnRvItemClickListener(rvCate) {
            @Override
            public void onItemClick(View itemView, int position) {
                if (adapter != null) {
                    adapter.setSelectedPos(position);
                }
            }

            @Override
            public void onItemLongClick(View itemView, int position) {

            }
        });

        //---添加收藏
        tvAddColl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String collTitle = etCollName.getText().toString();
                String collUrl = etCollUrl.getText().toString();
                if (TextUtils.isEmpty(collTitle) || TextUtils.isEmpty(collUrl)) {
                    MySnackBar.showLongDef(etCollName, "文章的标题或者URL不能为空");
                    return;
                }
                if (collCateList == null || collCateList.size() == 0) {
                    return;
                }
                CollBean collBean = new CollBean();
                collBean.setUserId(userInfo.getObjectId());
                collBean.setCateId(collCateList.get(adapter.getSelectedPos()).getObjectId());
                collBean.setCollTitle(collTitle);
                collBean.setCollUrl(collUrl);
                collBean.setItemType(4);

                collBeanBiz.saveColl(collBean, new IRequestCallback<CollBean>() {
                    @Override
                    public void onSuccess(CollBean result) {
                        bottomSheetDialog.dismiss();
                        MyToast.showLong(mContext, "文章添加成功");
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        MyToast.showLong(mContext, "添加失败");
                    }
                });
            }
        });

        tvAddFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //---添加收藏件夹
                if (TextUtils.isEmpty(etFolderTitle.getText().toString())) {
                    MyToast.showShort(mContext, "收藏夹名称不能为空");
                    return;
                }
                CollCate collCate = new CollCate();
                collCate.setUserId(userInfo.getObjectId());
                collCate.setCateTitle(etFolderTitle.getText().toString());
                collCate.setCateIntro(etFolderIntro.getText().toString());

                collCateBiz.saveCollCate(collCate, new IRequestCallback<CollCate>() {
                    @Override
                    public void onSuccess(CollCate result) {
                        initData();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

            }
        });

        tvToCreateFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svColl.toNext();
            }
        });

        tvToAddColl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svColl.toPre();
            }
        });

    }
}

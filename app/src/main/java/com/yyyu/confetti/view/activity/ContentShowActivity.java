package com.yyyu.confetti.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yyyu.baselibrary.utils.WindowUtils;
import com.yyyu.baselibrary.utils.gen_pic_from_webview.Article;
import com.yyyu.baselibrary.utils.gen_pic_from_webview.WebViewHelper;
import com.yyyu.confetti.R;
import com.yyyu.confetti.bean.ItemContent;
import com.yyyu.confetti.presenter.ContentActPresenter;
import com.yyyu.confetti.presenter.inter.IContentActPresenter;
import com.yyyu.confetti.view.dialog.CollArticleDialog;
import com.yyyu.confetti.view.dialog.ZhihuContentShowDialog;
import com.yyyu.confetti.view.inter.IContentView;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * 功能：内容展示Activity
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/17
 */

public class ContentShowActivity extends BaseActivity implements IContentView {

    private static final String TAG = "ContentShowActivity";

    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tb_content)
    Toolbar tbContent;
    @BindView(R.id.ctl_content)
    CollapsingToolbarLayout ctlContent;
    @BindView(R.id.ns_content)
    NestedScrollView nsContent;
    @BindView(R.id.wv_content)
    WebView wvContent;
    @BindView(R.id.pb_content)
    ContentLoadingProgressBar pbContent;
    @BindView(R.id.fab_coll)
    FloatingActionButton fabColl;
    @BindView(R.id.fab_com)
    FloatingActionButton fabCom;
    @BindView(R.id.btn_gen_pic)
    Button btnGenPic;

    IContentActPresenter presenter;
    private int type;
    private String id;
    private String username;
    private ItemContent content;

    @Override
    public void beforeSetContentView() {
            setTransition();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_content_show;
    }

    @Override
    public void beforeInit() {
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
        id = intent.getStringExtra("id");
        username = intent.getStringExtra("username");
    }

    @Override
    protected void initView() {
        setSupportActionBar(tbContent);
        presenter = new ContentActPresenter(this);
        ctlContent.setExpandedTitleColor(getResources().getColor(R.color.myTrans));
        ctlContent.setCollapsedTitleTextColor(getResources().getColor(R.color.myWhite));
        ctlContent.setTitle("");
        ctlContent.setTitleEnabled(true);
        tbContent.setNavigationIcon(R.mipmap.ic_back);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            nsContent.setTransitionGroup(true);
            wvContent.setTransitionGroup(true);
        }
    }

    @Override
    protected void initListener() {
        tbContent.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fabCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int winHeight = WindowUtils.getSize(getApplicationContext())[1];
                ZhihuContentShowDialog dialog = new ZhihuContentShowDialog(ContentShowActivity.this, winHeight, winHeight, id);
                dialog.show();
            }
        });

        //---监听webView
        wvContent.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                btnGenPic.setVisibility(View.VISIBLE);
            }
        });
        WebViewHelper.getInstance().setUpWebView(wvContent, new WebViewHelper.OnGetDataListener() {
            @Override
            public void getDataListener(String text) {
                Article article = new Article(text, TextUtils.isEmpty(
                        WebViewHelper.getInstance().getTitle()) ? "" : "《" + WebViewHelper.getInstance().getTitle() + "》");
                CaptureActivity.startAction(ContentShowActivity.this, article);
            }
        });

    }

    @Override
    protected void initData() {
        if(type==4){
            wvContent.loadUrl(id);
        }else{
            presenter.getData(type, id ,username );
        }

    }

    @Override
    public void setData(final ItemContent itemContent) {
        content = itemContent;
        ctlContent.setTitle(itemContent.getTitle());
        tvTitle.setText(itemContent.getTitle());
        Glide.with(this)
                .load(itemContent.getIconPath())
                .bitmapTransform(new BlurTransformation(this, 3))
                .into(ivIcon);
        if (type == 0) {//知乎日报
            String mNewsContent = "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_content_style.css\"/>"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_header_style.css\"/>"
                    + itemContent.getBody().replace("<div class=\"img-place-holder\">", "");
            wvContent.loadDataWithBaseURL("file:///android_asset/zhihu/", mNewsContent, "text/html", "UTF-8", null);
            //wvContent.setDrawingCacheEnabled(true);
        } else if(type==3||type==2){
            String mNewsContent = "<link rel=\"stylesheet\" type=\"text/css\" href=\"prettify.css\"/>"
                    + "<script src=\"prettify.js\" type=\"text/javascript\"></script>"
                    + itemContent.getBody();
            wvContent.loadDataWithBaseURL("file:///android_asset/csdn/", mNewsContent, "text/html", "UTF-8", null);
        }

    }

    @OnClick(R.id.btn_gen_pic)
    public void generatePic(View view) {
        wvContent.post(new Runnable() {
            @Override
            public void run() {
                WebViewHelper.getInstance().getSelectedData(wvContent);
            }
        });
        btnGenPic.setVisibility(View.GONE);
    }

    @OnClick(R.id.fab_coll)
    public void collArticle(View view){
        CollArticleDialog dialog = new CollArticleDialog(this , type , content.getId() , content.getTitle() );
        dialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.content_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.content_share:
               share();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 跳转到ContentShowActivity
     *
     * @param activity
     * @param type     0:知乎日报 2：简书 3：CSDN
     * @param id
     * @param ivShare
     */
    public static void startAction(Activity activity, int type, String id, String username , ImageView ivShare) {
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, ivShare, activity.getResources().getString(R.string.home_item_img_share)).toBundle();
        Intent intent = new Intent(activity, ContentShowActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("id", id);
        intent.putExtra("username" ,username );
        ActivityCompat.startActivity(activity, intent, bundle);
    }

    public static void startAction(Activity activity, int type, String id) {
        Intent intent = new Intent(activity, ContentShowActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WebViewHelper.getInstance().clear();
    }

    @Override
    public void onBackPressed() {
        if (wvContent != null && wvContent.canGoBack()) {
            wvContent.goBack();
            return;
        }
        super.onBackPressed();
    }

    private void share() {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        switch (type){
            case 0:
                intent.putExtra(Intent.EXTRA_TEXT, "来自Confetti的分享" + content.getTitle() + "，http://daily.zhihu.com/story/" + content.getId());
                break;
            case 3:
                intent.putExtra(Intent.EXTRA_TEXT, "来自Confetti的分享" + content.getTitle() + content.getId());
                break;
        }
        startActivity(Intent.createChooser(intent, content.getTitle()));
    }

}

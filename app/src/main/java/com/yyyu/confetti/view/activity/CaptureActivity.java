package com.yyyu.confetti.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yyyu.baselibrary.utils.CutViewUtils;
import com.yyyu.baselibrary.utils.FileUtils;
import com.yyyu.baselibrary.utils.MySnackBar;
import com.yyyu.baselibrary.utils.gen_pic_from_webview.Article;
import com.yyyu.baselibrary.utils.gen_pic_from_webview.GeneratePictureView;
import com.yyyu.confetti.R;

import java.io.File;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 功能：生成图片Activity
 *
 * @author yyyu
 * @version 1.0
 * @date 2017/3/20
 */

public class CaptureActivity extends BaseActivity {

    @BindView(R.id.tb_capture)
    Toolbar tbCapture;
    @BindView(R.id.rg_theme)
    RadioGroup rgTheme;
    @BindView(R.id.rb_day)
    RadioButton rbDay;
    @BindView(R.id.rb_night)
    RadioButton rbNight;
    @BindView(R.id.gpv_capture)
    GeneratePictureView gpvCapture;
    private Article article;
    private String imgName;


    @Override
    public int getLayoutId() {
        return R.layout.activity_capture;
    }

    @Override
    public void beforeInit() {
        imgName = new Date().toString() + ".jpg";
        article = (Article) getIntent().getSerializableExtra("data");
    }

    @Override
    protected void initView() {
        gpvCapture.init(article.strData , article.strTitle , "username" , "sign");
        setSupportActionBar(tbCapture);
    }

    @Override
    protected void initListener() {

        tbCapture.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rgTheme.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rb_day:
                        gpvCapture.changeDay(article.strData , article.strTitle , "yyyu" , "一蓑烟雨任平生");
                        break;
                    case R.id.rb_night:
                        gpvCapture.changeNight(article.strData , article.strTitle , "yyyu" , "一蓑烟雨任平生");
                        break;
                }
            }
        });
    }

    @OnClick({R.id.btn_share, R.id.btn_save})
    public void operate(View view) {
        switch (view.getId()) {
            case R.id.btn_share:
                shareMsg(article.strTitle , article.strTitle , article.strData ,null );
                break;
            case R.id.btn_save:
                if (FileUtils.isFileExists(imgName)) {
                    MySnackBar.showLongDef(gpvCapture, "该文章已经被保存过啦").setAction("任性再保存", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FileUtils.saveBitmap(CaptureActivity.this, CutViewUtils.getBitmapByView(gpvCapture), imgName);
                            MediaScannerConnection.scanFile(CaptureActivity.this, new String[]{FileUtils.savePath + imgName}, null, null);
                            MySnackBar.showLongDef(gpvCapture, "文章已保存为图片，去相册查看");
                        }
                    });
                    break;
                }
                FileUtils.saveBitmap(this, CutViewUtils.getBitmapByView(gpvCapture), imgName);
                MediaScannerConnection.scanFile(this, new String[]{FileUtils.savePath + imgName}, null, null);
                MySnackBar.showLongDef(gpvCapture, "文章已保存为图片，去相册查看");
                break;
        }
    }

    public static void startAction(Activity activity, Article article) {
        Intent intent = new Intent(activity, CaptureActivity.class);
        intent.putExtra("data", article);
        activity.startActivity(intent);
    }

    /**
     * 分享功能
     *
     *
     * @param activityTitle
     *            Activity的名字
     * @param msgTitle
     *            消息标题
     * @param msgText
     *            消息内容
     * @param imgPath
     *            图片路径，不分享图片则传null
     */
    public void shareMsg(String activityTitle, String msgTitle, String msgText,
                         String imgPath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (imgPath == null || imgPath.equals("")) {
            intent.setType("text/plain"); // 纯文本
        } else {
            File f = new File(imgPath);
            if (f != null && f.exists() && f.isFile()) {
                intent.setType("image/jpg");
                Uri u = Uri.fromFile(f);
                intent.putExtra(Intent.EXTRA_STREAM, u);
            }
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, activityTitle));
    }

}

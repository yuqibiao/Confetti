package com.yyyu.confetti;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bumptech.glide.util.LogTime;
import com.yyyu.baselibrary.utils.MyLog;
import com.yyyu.confetti.bean.ClassifyData;
import com.yyyu.confetti.bean.ClassifyTab;
import com.yyyu.confetti.bean.ContentCom;
import com.yyyu.confetti.net.APIFactory;
import com.yyyu.confetti.net.APIMethodManager;
import com.yyyu.confetti.net.IRequestCallback;
import com.yyyu.confetti.net.js.JsApi;
import com.yyyu.confetti.net.js.JsMethods;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static final String TAG = "ExampleInstrumentedTest";
    @Test
    public void useAppContext() throws Exception {

        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getContext();
        MyLog.e("start=======qweqw=====");
       /* APIMethodManager.getInstance().getDataByClassifyId(new IRequestCallback<ClassifyData>() {
            @Override
            public void onSuccess(ClassifyData result) {
                MyLog.e("result======="+result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
                MyLog.e("error============"+throwable.getMessage());
            }
        } , 2 , "3fde3b545a35" , 20);*/


        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Document document = Jsoup.connect("http://www.baidu.com")
                            .timeout(10000)
                            .get();

                    Elements noteList = document.select("ul.note-list");
                    Elements li = noteList.select("li");

                    Log.e("TAG" ,"==="+ li.size());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i(TAG, "run: " + e.getMessage());
                }

            }
        }).start();


    }

}

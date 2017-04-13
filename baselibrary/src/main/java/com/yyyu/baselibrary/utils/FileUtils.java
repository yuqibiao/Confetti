package com.yyyu.baselibrary.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 功能：文件操作相关的工具类
 *
 * @author yyyu
 * @date 2016/5/30
 */
public class FileUtils {

    public static String savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/interview/";

    /**
     * 保存图片的方法
     */
    public static String saveBitmap(Activity activity, Bitmap bm, String picName) {

        if (Build.VERSION.SDK_INT >= 22) {
            int REQUEST_EXTERNAL_STORAGE = 1;
            String[] PERMISSIONS_STORAGE = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
            int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                        activity,
                        PERMISSIONS_STORAGE,
                        REQUEST_EXTERNAL_STORAGE
                );
            }
        }
        File f = new File(savePath, picName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 把图片加载到输出流中
        int option = 100;
        while (baos.toByteArray().length / 1024 > 1024) {// >1M就压缩
            baos.reset();// 清空流
            option -= 10;
            MyLog.e("图片超过1M 压缩了-------------------");
            bm.compress(Bitmap.CompressFormat.JPEG, option, baos);// 眼option比例压缩
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bais.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            MyLog.e("not found =============" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            MyLog.e("io==============" + e.getMessage());
            e.printStackTrace();
        }
        return f.getPath();
    }

    /**
     * 根据文件名得到文件
     */
    public static File getFIleByName(String fileName) {
        File file = new File(savePath + fileName);
        if (file.exists()) {
            return file;
        } else {
            return null;
        }
    }

    /**
     * 判断文件是否已经存在
     */
    public static boolean isFileExists(String fileName) {
        File temp = new File(savePath + fileName);
        return temp.exists();
    }

    /**
     * 根据Uri得到图片的路径
     */
    public static String getImagePathFromUri(Context context, Uri uri) {
        String res = "";
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    /**
     * 得到缓存数据的方法
     * 返回false表示不从缓存获取
     */
    public static String getDataFromCache(Context context, String url) {
        String data=null;
            File saveFile = new File(context.getCacheDir(), url + ".ofyu");
            if (!saveFile.exists()) return null;
            ByteArrayOutputStream bos = null;
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(saveFile);
                bos = new ByteArrayOutputStream();
                int len;
                byte[] buffer = new byte[1024];
                while ( (len= fis.read(buffer))>0){
                    bos.write(buffer , 0 , len);
                }
                 data =  bos.toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bos!=null){
                    try {
                        bos.flush();
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis!=null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            return data;
        }
    }

    /**
     * 缓存数据的方法
     */
    public static void toCacheData(Context context, String url, String dataJson) {
        File saveFile = new File(context.getCacheDir(), url + ".ofyu");
        ByteArrayInputStream bis = null;
        FileOutputStream fos = null;
        try {
            bis = new ByteArrayInputStream(dataJson.getBytes());
            fos = new FileOutputStream(saveFile);
            int len;
            byte[] buffer = new byte[1024];
            while ((len = bis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            MyLog.e("缓存成功=============================");
        } catch (IOException e) {
            e.printStackTrace();
            MyLog.e("IOException==========="+e.getMessage());
        } finally {
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}





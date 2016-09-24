package com.yehua.zhihu_zhouyehua_hehe.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;

/**
 * 从SD卡上读取文件，写入文件
 */
public class SDUtils {
    //缓存路径，不包含SD卡的根路径
    final static String CACHE_PATH = "/zhouy/imgCache/";

    /**
     * 从SD卡中，根据文件名，读取Bitmap
     *
     * @param filePath 本地路径
     * @return
     */
    public static Bitmap getBitmap(String filePath) {
        //1.先判断SD卡是否安装好，如果没有安装好，直接返回null
        if (!isMount()) {
            return null;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        //BitmapFactory
        return BitmapFactory.decodeFile(filePath);
    }

    /**
     * @return
     */
    public static boolean isMount() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }


}

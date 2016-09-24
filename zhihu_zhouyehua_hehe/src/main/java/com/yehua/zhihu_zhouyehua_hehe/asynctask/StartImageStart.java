package com.yehua.zhihu_zhouyehua_hehe.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2016/9/21 0021.
 */
public class StartImageStart extends AsyncTask<String,Void,String>{
    public interface StartImageBask{
        //传递数据
        public void setData(String path);
    }
    StartImageBask cb;
    Context context;

    public StartImageStart(Context context,StartImageBask cb) {
        this.context = context;
        this.cb=cb;
    }

    @Override
    protected String doInBackground(String... params) {
        String path = null;
        FutureTarget<File> future = Glide.with(context)
                .load(params[0])
                .downloadOnly(500, 500);
        try {
            File cacheFile = future.get();
            path = cacheFile.getAbsolutePath();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return path;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(cb!=null){
            cb.setData(s);
        }
    }
}

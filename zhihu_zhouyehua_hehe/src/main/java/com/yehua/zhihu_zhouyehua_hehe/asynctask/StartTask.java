package com.yehua.zhihu_zhouyehua_hehe.asynctask;

import android.os.AsyncTask;

import com.yehua.zhihu_zhouyehua_hehe.Utils.HttpUtils;
import com.yehua.zhihu_zhouyehua_hehe.bean.StartBean;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public class StartTask extends AsyncTask<String,Void,StartBean>{
    public interface StartBask{
        //传递数据
        public void setData(StartBean startBean);
    }
    StartBask cb;
    public StartTask(StartBask cb) {
        this.cb = cb;
    }

    @Override
    protected StartBean doInBackground(String... params) {
        StartBean sb=null;
        String http=params[0];
        try {
            //获得下载的字符串
            String jsonString= HttpUtils.getText(http);
            //解析数据
            JSONObject json=new JSONObject(jsonString);
            sb=new StartBean(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }

    @Override
    protected void onPostExecute(StartBean startBean) {
        super.onPostExecute(startBean);
        if(cb!=null){
            cb.setData(startBean);
        }
    }
}

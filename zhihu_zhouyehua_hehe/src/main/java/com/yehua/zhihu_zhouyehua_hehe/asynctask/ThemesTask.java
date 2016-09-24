package com.yehua.zhihu_zhouyehua_hehe.asynctask;

import android.os.AsyncTask;
import android.os.SystemClock;

import com.yehua.zhihu_zhouyehua_hehe.Utils.HttpUtils;
import com.yehua.zhihu_zhouyehua_hehe.bean.ThemesBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/22 0022.
 */
public class ThemesTask extends AsyncTask<String,Void,List<ThemesBean>>{

    public interface ThemesBack{
        public void setThemesData(List<ThemesBean> data);

    }
    //定义接口
    ThemesBack themesBack;
    //延时的时间，即欢迎界面显示的时间
    int times;

    public ThemesTask(ThemesBack themesBack,int times) {
        this.themesBack = themesBack;
        this.times=times;
    }

    @Override
    protected List<ThemesBean> doInBackground(String... params) {
        List<ThemesBean> list=new ArrayList<>();
        try {
            //下载的数据
            String str = HttpUtils.getText(params[0]);
            //解析数据
            JSONObject jsonObject=new JSONObject(str);
            JSONArray jsonArray=jsonObject.optJSONArray("others");
            for (int i=0;i<jsonArray.length();i++){
                ThemesBean themesBean=new ThemesBean(jsonArray.optJSONObject(i));
                list.add(themesBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SystemClock.sleep(times);
        return list;
    }

    @Override
    protected void onPostExecute(List<ThemesBean> themesBeen) {
        super.onPostExecute(themesBeen);
        if(themesBack!=null){
            themesBack.setThemesData(themesBeen);
        }
    }
}

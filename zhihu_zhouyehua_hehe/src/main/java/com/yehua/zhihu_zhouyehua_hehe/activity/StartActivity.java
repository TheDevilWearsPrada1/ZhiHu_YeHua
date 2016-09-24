package com.yehua.zhihu_zhouyehua_hehe.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ImageView;
import android.widget.TextView;

import com.yehua.zhihu_zhouyehua_hehe.R;
import com.yehua.zhihu_zhouyehua_hehe.Utils.SDUtils;
import com.yehua.zhihu_zhouyehua_hehe.asynctask.StartImageStart;
import com.yehua.zhihu_zhouyehua_hehe.asynctask.StartTask;
import com.yehua.zhihu_zhouyehua_hehe.asynctask.ThemesTask;
import com.yehua.zhihu_zhouyehua_hehe.bean.StartBean;
import com.yehua.zhihu_zhouyehua_hehe.bean.ThemesBean;
import com.yehua.zhihu_zhouyehua_hehe.functionalities.LearningPatternDisabled;

import java.io.Serializable;
import java.util.List;

public class StartActivity extends Activity {
    //偏好设置
    SharedPreferences shared;

    ImageView iv;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.start_activity);
        //找到控件
        setupViews();
        //启动异步任务
        startAsy();
    }

    /**
     * 启动异步任务
     * <p/>
     * 有无网络都能加载图片
     */
    private void startAsy() {
        StartTask.StartBask cb = new StartTask.StartBask() {
            @Override
            public void setData(final StartBean startBean) {
                String text = readShared("text");
                String path = readShared("path");
                if (startBean == null || startBean.text.equals(text)) {
                    //设置控件数据
                    tv.setText(text);
                    iv.setImageBitmap(SDUtils.getBitmap(path));
                    setupActivity();
                    return;
                }

                //保存数据
                //1.获取sharedprefs对象实例
                shared = getSharedPreferences("sharedprefs", MODE_PRIVATE);
                //2.利用sharedprefs实例获取Editor对象
                final SharedPreferences.Editor editor = shared.edit();
                //3.利用Editor写入对应的值：Key—value
                editor.putString("text", startBean.text);
                //下载图片
                StartImageStart startImageStart = new StartImageStart(StartActivity.this, new StartImageStart.StartImageBask() {
                    @Override
                    public void setData(String path) {
                        editor.putString("path", path);
                        //设置控件数据
                        tv.setText(startBean.text);
                        iv.setImageBitmap(SDUtils.getBitmap(path));
                        editor.commit();
                        setupActivity();
                    }
                });
                startImageStart.execute(startBean.img);

            }
        };
        StartTask startTask = new StartTask(cb);
        startTask.execute("http://news-at.zhihu.com/api/4/start-image/1080*1776");
    }

    private String readShared(String key) {
        //1.获取sharedprefs对象实例
        shared = getSharedPreferences("sharedprefs", MODE_PRIVATE);
        String str = shared.getString(key, null);
        return str;
    }

    /**
     * 初始化控件
     */
    private void setupViews() {
        iv = (ImageView) findViewById(R.id.start_iv);
        tv = (TextView) findViewById(R.id.start_tv);
    }

    /**
     * 实现界面的跳转
     */
    private void setupActivity() {
        //启动异步任务,下载下一个页面所需的数据
        ThemesTask.ThemesBack tb = new ThemesTask.ThemesBack() {
            @Override
            public void setThemesData(List<ThemesBean> data) {
                //跳转页面并传递下载的数据
                Intent intent = new Intent(StartActivity.this, LearningPatternDisabled.class);
                intent.putExtra("data", (Serializable) data);
                startActivity(intent);

            }
        };
        //创建异步任务，设置接口，并设置延时多久跳转
        ThemesTask task = new ThemesTask(tb,3000);
        task.execute("http://news-at.zhihu.com/api/4/themes");
//                Intent intent=new Intent(StartActivity.this,MainActivity.class);

    }

    /**
     * 关闭当前Activity
     */
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}

package com.yehua.zhihu_zhouyehua_hehe.Utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class HttpUtils {
    /**
     * 联网下载JSON数据
     * @param http
     * @return
     * @throws Exception
     */
    public static String getText(String http) throws Exception {
        StringBuilder sb=new StringBuilder();
        HttpURLConnection conn=null;
        URL url=new URL(http);
        conn= (HttpURLConnection) url.openConnection();
        //获得流
        InputStream in=conn.getInputStream();
        //字符缓冲流
        BufferedReader br=new BufferedReader(new InputStreamReader(in));
        String temp=null;
        //循环读取流
        while (null!=(temp=br.readLine())){
            sb.append(temp);
        }
        //关闭流
        br.close();
        conn.disconnect();
        return sb.toString();
    }
}

package com.yehua.zhihu_zhouyehua_hehe.bean;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public class StartBean {
   /* {
        "text": "\"山色\" © 李喜斌",
            "img": "https://pic2.zhimg.com/v2-ca233494720fa706735030a011fbe0b9.jpg"
    }*/

    public StartBean(JSONObject json) {
        text=json.optString("text");
        img=json.optString("img");
    }
    public String text;
    public String img;
}

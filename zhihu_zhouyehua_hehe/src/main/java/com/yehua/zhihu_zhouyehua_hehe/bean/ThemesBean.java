package com.yehua.zhihu_zhouyehua_hehe.bean;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/22 0022.
 */
public class ThemesBean implements Serializable{
    public ThemesBean(JSONObject json) {
        this.color=json.optString("color");
        this.thumbnail=json.optString("thumbnail");
        this.description=json.optString("description");
        this.name=json.optString("name");
        this.id=json.optInt("id");
    }
    /*"limit": 1000,
            "subscribed": [],
            "others": [
    {
        "color": 15007,
            "thumbnail": "http://pic3.zhimg.com/0e71e90fd6be47630399d63c58beebfc.jpg",
            "description": "了解自己和别人，了解彼此的欲望和局限。",
            "id": 13,
            "name": "日常心理学"
    },*/
    public String color;
    public String thumbnail;
    public String description;
    public String name;
    public int id;

}

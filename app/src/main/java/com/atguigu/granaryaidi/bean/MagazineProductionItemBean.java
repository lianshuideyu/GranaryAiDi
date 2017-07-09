package com.atguigu.granaryaidi.bean;

/**
 * Created by Administrator on 2017/7/9.
 */

/**
 * 杂志页面 对应 粮仓接口的 每个 item的bean对象
 */
public class MagazineProductionItemBean {

    /**
     * taid : 1313
     * topic_name : 抱歉，这些纽约街头的橱窗已下线
     * cat_id : 19
     * author_id : 4
     * topic_url : http://www.iliangcang.com/i/topicapp/201606173316
     * access_url : http://www.iliangcang.com/i/topicapp/201606173316
     * cover_img : http://imgs-qn.iliangcang.com/ware/appimg/topic/cover/1313_.jpg?_t=1466167929
     * cover_img_new : http://imgs-qn.iliangcang.com/ware/appimg/topic/cover/1313_.jpg?_t=1466167929
     * hit_number : 1247
     * addtime : 2016-06-17 20:42:34
     * content :
     * nav_title : 良仓杂志
     * author_name : 《新视线》
     * cat_name : 建筑
     */

    private String taid;
    private String topic_name;
    private String cat_id;
    private String author_id;
    private String topic_url;
    private String access_url;
    private String cover_img;
    private String cover_img_new;
    private int hit_number;
    private String addtime;
    private String content;
    private String nav_title;
    private String author_name;
    private String cat_name;

    public String getTaid() {
        return taid;
    }

    public void setTaid(String taid) {
        this.taid = taid;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getTopic_url() {
        return topic_url;
    }

    public void setTopic_url(String topic_url) {
        this.topic_url = topic_url;
    }

    public String getAccess_url() {
        return access_url;
    }

    public void setAccess_url(String access_url) {
        this.access_url = access_url;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public String getCover_img_new() {
        return cover_img_new;
    }

    public void setCover_img_new(String cover_img_new) {
        this.cover_img_new = cover_img_new;
    }

    public int getHit_number() {
        return hit_number;
    }

    public void setHit_number(int hit_number) {
        this.hit_number = hit_number;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNav_title() {
        return nav_title;
    }

    public void setNav_title(String nav_title) {
        this.nav_title = nav_title;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    @Override
    public String toString() {
        return "MagazineProductionItemBean{" +
                "taid='" + taid + '\'' +
                ", topic_name='" + topic_name + '\'' +
                ", cat_id='" + cat_id + '\'' +
                ", author_id='" + author_id + '\'' +
                ", topic_url='" + topic_url + '\'' +
                ", access_url='" + access_url + '\'' +
                ", cover_img='" + cover_img + '\'' +
                ", cover_img_new='" + cover_img_new + '\'' +
                ", hit_number=" + hit_number +
                ", addtime='" + addtime + '\'' +
                ", content='" + content + '\'' +
                ", nav_title='" + nav_title + '\'' +
                ", author_name='" + author_name + '\'' +
                ", cat_name='" + cat_name + '\'' +
                '}';
    }
}

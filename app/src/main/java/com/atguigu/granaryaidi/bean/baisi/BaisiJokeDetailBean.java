package com.atguigu.granaryaidi.bean.baisi;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/16.
 */

public class BaisiJokeDetailBean implements Serializable{

    private String icon;
    private String name;
    private String update;

    private String jokecontent;

    private String upCount;//点赞数量
    private int downCount;//讨厌的数量
    private int forwardCount;//转发的数量
    private String commentCount;//评论的数量
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BaisiJokeDetailBean() {
    }

    public BaisiJokeDetailBean(String icon, String name, String update,
                               String jokecontent, String upCount, int downCount,
                               int forwardCount, String commentCount,String id) {
        this.icon = icon;
        this.name = name;
        this.update = update;
        this.jokecontent = jokecontent;
        this.upCount = upCount;
        this.downCount = downCount;
        this.forwardCount = forwardCount;
        this.commentCount = commentCount;
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaisiJokeDetailBean{" +
                "icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", update='" + update + '\'' +
                ", jokecontent='" + jokecontent + '\'' +
                ", upCount='" + upCount + '\'' +
                ", downCount=" + downCount +
                ", forwardCount=" + forwardCount +
                ", commentCount='" + commentCount + '\'' +
                '}';
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getJokecontent() {
        return jokecontent;
    }

    public void setJokecontent(String jokecontent) {
        this.jokecontent = jokecontent;
    }

    public String getUpCount() {
        return upCount;
    }

    public void setUpCount(String upCount) {
        this.upCount = upCount;
    }

    public int getDownCount() {
        return downCount;
    }

    public void setDownCount(int downCount) {
        this.downCount = downCount;
    }

    public int getForwardCount() {
        return forwardCount;
    }

    public void setForwardCount(int forwardCount) {
        this.forwardCount = forwardCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }
}

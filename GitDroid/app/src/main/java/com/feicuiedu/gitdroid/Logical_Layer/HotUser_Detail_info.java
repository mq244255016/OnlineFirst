package com.feicuiedu.gitdroid.Logical_Layer;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MMQ on 2016/8/2.
 * 这个是拿到的用户数据，分别为 名字，id，用户头像，type,score
 */
public class HotUser_Detail_info {

    @SerializedName("login")
    private String userName;

    private int id;

    @SerializedName("avatar_url")
    private String headImage;


    private String type;

    private double score;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}

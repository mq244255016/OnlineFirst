package com.feicuiedu.gitdroid.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 个人用户信息响应的结果，实体类
 * Created by MMQ on 2016/7/30.
 */
public class User {

    //登陆所用的账号
    private String login;

    //用户名
    private String name;
    private int id;

    //用户头像路径
    @SerializedName("avatar_url")
    private String avatar;

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}

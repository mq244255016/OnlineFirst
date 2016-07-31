package com.feicuiedu.gitdroid.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 授权登陆响应结果，为了之后能够一键Gson解析，
 * 只要名字不同，就需要来转换一下，用SerializedName，上面
 * 写和网络中数据名一样的名字
 * Created by MMQ on 2016/7/30.
 */
public class AccessTokenResult {
    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("scope")
    private String scope;

    @SerializedName("token_type")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public String getScope() {
        return scope;
    }

    public String getTokenType() {
        return tokenType;
    }
}

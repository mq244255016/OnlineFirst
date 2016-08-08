package com.feicuiedu.gitdroid.http;

import com.feicuiedu.gitdroid.Logical_Layer.HotUser_Info;
import com.feicuiedu.gitdroid.entity.AccessTokenResult;
import com.feicuiedu.gitdroid.entity.RepoResult;
import com.feicuiedu.gitdroid.entity.User;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.Query;

/**
 * 用Retrofit的话，能基本表现出很多的HTTP基本知识
 * 上面的话为讲解者的注释
 * 实现了写的GitHbuApi接口
 * Created by MMQ on 2016/7/30.
 */
public class GitHubClient implements GitHubApi {

    private  GitHubApi gitHubApi;

    //单例
    public static GitHubClient gitHubClient;

    //单例模式，之后实例化要调用个这个方法
    public static GitHubClient getInstance(){
        if(gitHubClient==null){
            gitHubClient=new GitHubClient();
        }
        return gitHubClient;

    }

    //无参的构造方法,里面用了Okhttp第三方，实例化这个类就会调用
    private GitHubClient() {
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                //添加token拦截器
            .addInterceptor(new TokenInterceptor())
                .build();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL)
                .client(okHttpClient)
                //Gson转换器,这个就是一键解析
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //构建API
        gitHubApi =retrofit.create(GitHubApi.class);

    }

    //这些是实现GitHubApi接口覆写的方法
    @Override
    public Call<AccessTokenResult> getOAuthToken(@Field("client_id") String client, @Field("client_secret") String clientSecret, @Field("code") String code) {
        return gitHubApi.getOAuthToken(client, clientSecret, code);
    }

    //返回的用户信息
    @Override
    public Call<User> getUserInfo() {
        return gitHubApi.getUserInfo();
    }


    /*
    这是获得仓库类的方法，传入的是页数和必要的一个q，也就是地址，和页数。
     */
    @Override
    public Call<RepoResult> searchRepos(@Query("q") String query, @Query("page") int pageId) {
        return gitHubApi.searchRepos(query, pageId);
    }

    @Override
    public Call<HotUser_Info> hotUserBack(@Query("q") String flower, @Query("page") int pageId) {
        return gitHubApi.hotUserBack(flower,pageId);
    }


    //
}

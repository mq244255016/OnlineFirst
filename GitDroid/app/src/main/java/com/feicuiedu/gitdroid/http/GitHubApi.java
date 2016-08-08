package com.feicuiedu.gitdroid.http;

import com.feicuiedu.gitdroid.Logical_Layer.HotUser_Info;
import com.feicuiedu.gitdroid.entity.AccessTokenResult;
import com.feicuiedu.gitdroid.entity.RepoResult;
import com.feicuiedu.gitdroid.entity.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 没错，这是一个接口
 * Retrofit能将标准的reset接口用Java来描述（通过注解）
 *通过retrofit的create 方法，去创建Call模型
 * Created by MMQ on 2016/7/30.
 */
public interface GitHubApi {
    String  URL="https://api.github.com/";
    //GitHub开发者，申请的时候填写（重定向返回是的一个标记）
    String CALL_BACK="mq";

    //自己申请的id和
    String CLIENT_ID="b0167805df749176a01a";

    String CLIENT_SECRET="fb84c508c86a629e13e134a4256a964d3ca9f949";

    //授权时申请的可访问域,在Api文档中可以看到，直接上传上来的三个数据。
    String AUTH_SCOPE="user,public_repo,repo";

    //授权的登陆界面（用WebView来加载）,网站加上自己的id，和域名的拼接
    String AUTH_RUL="https://github.com/login/oauth/authorize?client_id=" + CLIENT_ID + "&scope=" + AUTH_SCOPE;


    /*
    获取访问令牌的API，用的是注解框架@Field是表单的注解模式，@Headers是
    头

     */
    //这个是头
    @Headers("Accept: application/json")
    //这个不太清楚，大概是格式
    @FormUrlEncoded
    @POST("https://github.com/login/oauth/access_token")
    Call<AccessTokenResult> getOAuthToken(
      //以下为表单拼接,这就是上面传过去的那三个
      @Field("client_id") String client,
      @Field("client_secret") String clientSecret,
      @Field("code") String code);


    @GET("user")
    Call<User> getUserInfo();


    /**
     * 获取仓库里面的数据
     * Param query 查询参数(language:java)
     * @Param pageId 查询页数据(从1开始)
     */

    @GET("/search/repositories")
    Call<RepoResult> searchRepos(
            @Query("q")String query,//这里的q是Api当中显示必须要传入的
            @Query("page") int pageId);//这个是拼接传入的页数



    /*
    获取热门开发者的数据，通过粉丝来查询
     */
    @GET("/search/users")
    Call<HotUser_Info> hotUserBack(
            @Query("q") String flower,
            @Query("page") int pageId
    );



}

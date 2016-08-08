package com.feicuiedu.gitdroid.hotuser;

import android.util.Log;

import com.feicuiedu.gitdroid.Logical_Layer.HotUser_Detail_info;
import com.feicuiedu.gitdroid.Logical_Layer.HotUser_Info;
import com.feicuiedu.gitdroid.http.GitHubClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 热门用户的逻辑层，这里定义了内部的接口，直接把视图层的方法放到里面
 * Created by MMQ on 2016/8/2.
 */
public class HotUser_logic {


    private Call<HotUser_Info> hotUserInfoCall;//声明拿到的初始数据的Call
    private int nextPage=0;//这是加载的页数

    /*
    定义内部接口，是视图界面要用到的方法
     */
    public interface  HotUser_Interface{

        /*
        上拉刷新的视图的方法
         */
        //加载具体数据
        void hotRefreshData(List<HotUser_Detail_info> refreshList);

        //显示加载出来的主试图
        void hotRefreshShowList();

        //刷新加载错误的视图
        void hotRefreshError();


        //停止刷新的方法
        void hideRefreshView();

//        -------------------------------------------------------------------------------------


        /*
        上拉加载更多的视图方法
         */
        void hotLoadMoreData(List<HotUser_Detail_info> loadList);



    }

    //做为属性，调用里面的方法
   private HotUser_Interface hotUser_interface;

    /*
    实例化这个逻辑操作类的时候就需要传入一个接口 的对象，所以要直接继承
    传入自身就可以。
     */
    public HotUser_logic(HotUser_Interface hotUser_interface) {
        this.hotUser_interface = hotUser_interface;
    }

    //-------------------------------------------------------------------------------------------------
    /*
    下拉刷新方法
     */
    public void refresh_logic(){

        Log.i("hot", "逻辑层下拉刷新方法");
        hotUser_interface.hideRefreshView();//加载停止的方法
        hotUser_interface.hotRefreshShowList();//视图显示方法
         nextPage=1;
        //来调用写在GitHubClient当中的方法，出入拼接，拿到数据
        hotUserInfoCall= GitHubClient.getInstance().hotUserBack(
                "followers:"+">1000",nextPage
        );
        hotUserInfoCall.enqueue(refreshCallback);
    }


    /*
    下拉刷新的具体操作
     */
    private  Callback<HotUser_Info> refreshCallback=new Callback<HotUser_Info>() {

        @Override//获取数据成功走的方法
        public void onResponse(Call<HotUser_Info> call, Response<HotUser_Info> response) {
            //调用刷新隐藏
            Log.i("hot", "进入了这个的下拉的具体操作");
            hotUser_interface.hideRefreshView();

            //得到响应结果,接受到数据,如果在运行就获得数据
            if(response.isSuccessful()){
                HotUser_Info hotUserInfo=response.body();
                if(hotUserInfo==null){
                    //调用显示结果为空的方法,这里用的加载错误的方法。
                    hotUser_interface.hotRefreshError();
                    return;
                }

                //取出数据调用视图界面方法出入数据到视图界面的方法
                List<HotUser_Detail_info> hotUserDetailInfo= hotUserInfo.getItems();
                hotUser_interface.hotRefreshData(hotUserDetailInfo);
                Log.i("hot", hotUserDetailInfo.size() + "这是接到的长度");
                //下拉刷新成功为1下一个为2
                nextPage=2;
                return;
            }

        }

        @Override//获取数据失败走的方法
        public void onFailure(Call<HotUser_Info> call, Throwable t) {
            Log.i("hot", "进入了这个的下拉失败操作");
//            hotUser_interface.hotRefreshError();
            hotUser_interface.hideRefreshView();//刷新结束，调用停止刷新方法
        }
    };
}

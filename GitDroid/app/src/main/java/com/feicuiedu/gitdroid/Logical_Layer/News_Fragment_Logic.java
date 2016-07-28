package com.feicuiedu.gitdroid.Logical_Layer;

import android.os.AsyncTask;
import android.util.Log;

import com.feicuiedu.gitdroid.myinterface.News_refresh;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MMQ on 2016/7/27.
 */
public class News_Fragment_Logic{
    int sum;

    private News_refresh news_refresh;//这是一个接口的对象

    //进行下拉刷新，这里传的是一个接口
    public News_Fragment_Logic(News_refresh news_refresh) {
        this.news_refresh = news_refresh;
    }

    //下拉刷新
    public void refresh(){
        //这里的工作一般是在后台线程做，使用了异步线程进行了加载数据
        new RefreshTask().execute();

    }

    /*
    异步线程，用来添加数据,这里暂时用来假数据的添加
     */
    class RefreshTask extends AsyncTask<Void ,Void ,Void >{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //线程休眠3秒，为了效果更加明显
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        //这里的时候已经拿到数据了
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //传入调用方法中的集合，是在这里获得
            List<String> list=new ArrayList<>();
             for (int i=0;i<20;i++,i++){
                 list.add("这是我穿进去的假数据"+(sum++));
             }
            //调用显示内容的方法。调用的刷新数据的这个方法
            news_refresh.stopRefresh();
            news_refresh.refreshData(list);
            Log.i("xiala",list+"这是逻辑list的长度");
            news_refresh.showCorrectData();
        }
    }
}

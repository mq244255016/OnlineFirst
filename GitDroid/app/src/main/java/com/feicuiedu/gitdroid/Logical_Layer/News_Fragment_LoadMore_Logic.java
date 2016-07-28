package com.feicuiedu.gitdroid.Logical_Layer;

import android.os.AsyncTask;

import com.feicuiedu.gitdroid.myinterface.LoadMore;

/**
 * Created by MMQ on 2016/7/28.
 */
public class News_Fragment_LoadMore_Logic {
   private LoadMore loadMore;

    public News_Fragment_LoadMore_Logic(LoadMore loadMore) {
        this.loadMore = loadMore;
    }


    //上拉加载更多的方法
    public void loadMoreData(){
        new LoadDadaTask();
    }

    class LoadDadaTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            loadMore.loading();

            loadMore.loadError();
            loadMore.loadNoMore();
        }
    }

}

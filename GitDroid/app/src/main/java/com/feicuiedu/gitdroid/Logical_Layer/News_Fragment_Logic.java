package com.feicuiedu.gitdroid.Logical_Layer;

import android.util.Log;

import com.feicuiedu.gitdroid.entity.Repo;
import com.feicuiedu.gitdroid.entity.RepoResult;
import com.feicuiedu.gitdroid.http.GitHubClient;
import com.feicuiedu.gitdroid.myinterface.News_Refresh_And_LoadMore;
import com.feicuiedu.gitdroid.entity.Language;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MMQ on 2016/7/27.
 * 热门仓库的Fragment的P层，负责从网络拿取数据到本地，传到list当中，
 * 调用热门仓库页面的接口里面的方法，实现方法，传递数据，之后在热门的
 * Fragment界面来实例化并调用里面的方法，实现上拉加载和下拉刷新的操作
 */
public class News_Fragment_Logic {
    //热门界面视图的接口
    private News_Refresh_And_LoadMore news_Refresh_LoadMore;//这是一个接口的对象

    private int nextPage = 0;//加载的页数
    private Language language;//自定义的language的页面

    private Call<RepoResult> repoCall;


    //便于操作，这里传的是一个接口,还需要传一个Language的对象
    public News_Fragment_Logic(News_Refresh_And_LoadMore news_Refresh_LoadMore, Language language) {
        this.news_Refresh_LoadMore = news_Refresh_LoadMore;
        this.language = language;
    }


    //下拉刷新
    public void refresh() {
////        //调用隐藏更多方法
//       news_Refresh_LoadMore.hideShowLoading();
        //显示加载更多
        news_Refresh_LoadMore.showLoadingMore();

        Log.i("xiala", "进入了这个下拉方法");
        nextPage = 1;

        //这是GitHubClient覆写gitApi当中的方法，获得仓库的数据，传入地址，和页数（上面定义好的）
        repoCall = GitHubClient.getInstance().searchRepos(
                "language:" + language.getPath(),
                nextPage
        );
        Log.i("xiala", "我是language" + language.getPath());
        Log.i("xiala", "走了执行");
        repoCall.enqueue(repoCallback);//执行方法

    }


    /*
    加载更多方法
     */
    public void loadMore() {
        //显示加载更多的视图
        news_Refresh_LoadMore.showLoadingMore();
        //这是GitHubClient覆写gitApi当中的方法，获得仓库的数据，传入地址，和页数（上面定义好的）
        repoCall = GitHubClient.getInstance().searchRepos("language:" + language.getPath()
                , nextPage);
        //启动加载更多的方法
        repoCall.enqueue(loadMoreCallback);

    }

    ;




    /*
    下拉刷新的具体实现
     */

    private final Callback<RepoResult> repoCallback = new Callback<RepoResult>() {
        @Override
        public void onResponse(Call<RepoResult> call, Response<RepoResult> response) {
            Log.i("xiala", "调用了下拉");
            //调用视图停止刷新方法
            news_Refresh_LoadMore.stopRefresh();

            //得到响应结果
            RepoResult repoResult = response.body();
            //如果为 空就调用显示错误的方法
            if (repoResult == null) {
                news_Refresh_LoadMore.showErrorView("结果为空");
                return;
            }

            //当前搜索的语言，没有仓库      ???
            if (repoResult.getTotalCount() <= 0) {
                //如果小于0，刷新结果就为空
                news_Refresh_LoadMore.refreshData(null);
                //调用显示空白的方法
                news_Refresh_LoadMore.showNullView();
                return;
            }

            // 取出当前语言下的所有仓库
            List<Repo> repoList = repoResult.getRepoList();
            news_Refresh_LoadMore.refreshData(repoList);
            // 下拉刷新成功(1), 下一面则更新为2
            nextPage = 2;
            Log.i("xiala", repoList.size() + "这是下拉的长度");
        }

        @Override
        public void onFailure(Call<RepoResult> call, Throwable t) {
            Log.i("xiala", t.getMessage());
            // 视图停止刷新
            news_Refresh_LoadMore.stopRefresh();
            news_Refresh_LoadMore.showMessage("repoCallback onFailure" + t.getMessage());
        }

    };


    /**
     * 加载更多的具体实现
     */
    private final Callback<RepoResult> loadMoreCallback = new Callback<RepoResult>() {
        @Override
        public void onResponse(Call<RepoResult> call, Response<RepoResult> response) {
            //得到响应的结果
            RepoResult repoResult = response.body();
            if (repoResult == null) {
                //如果为空，就显示显示错误的结果
                news_Refresh_LoadMore.showLoadingMoreError();
                return;
            }
            //取出当前语言下的所有库
            List<Repo> repoList = repoResult.getRepoList();
            news_Refresh_LoadMore.addMoreData(repoList);
            nextPage++;
        }

        @Override
        public void onFailure(Call<RepoResult> call, Throwable t) {
            // 视图停止刷新,
            news_Refresh_LoadMore.hideShowLoading();
            news_Refresh_LoadMore.showMessage("repoCallback onFailure" + t.getMessage());
        }
    };


}

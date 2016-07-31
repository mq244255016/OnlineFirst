package com.feicuiedu.gitdroid.myinterface;

import java.util.List;

/**
 * Created by MMQ on 2016/7/28.
 */
public interface News_Load_More_Interface {

    //加载更多
    void showLoadingMore();
    //加载更多错误
    void showLoadingMoreError();

    //加载完了，没有能加载的了
    void showLoadingNull();

    //隐藏加载更多
    void hideShowLoading();

    //加载数据
    void addMoreData(List<String> list);

}

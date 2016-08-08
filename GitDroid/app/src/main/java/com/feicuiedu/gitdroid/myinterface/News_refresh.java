package com.feicuiedu.gitdroid.myinterface;

import com.feicuiedu.gitdroid.entity.Repo;

import java.util.List;

/**
 * Created by MMQ on 2016/7/28.
 */
public interface News_refresh {
/*
    视图的所有显示的方法
     */

    //显示正常加载出来的内容
    public abstract void showCorrectData();

    //显示加载错误的时候的内容
    public abstract void showErrorView(String errorMessage);

    //显示空白时候的内容
    public abstract void showNullView();


    //停止刷新,把上面弹出的框收起来
    public abstract void stopRefresh();

    //显示提示信息
    public abstract void showMessage(String str);

    //刷新数据

    public abstract void refreshData(List<Repo> data);

}

package com.feicuiedu.gitdroid.myinterface;

/**
 * 一个继承了上拉刷新和下拉加载的接口，在NewsItemFragment就可以直接用实现这一个接口就可以
 * 并且两个接口的P层可以写在同一个里面
 * Created by MMQ on 2016/7/28.
 */
public interface News_Refresh_And_LoadMore extends News_refresh,News_Load_More_Interface {
}

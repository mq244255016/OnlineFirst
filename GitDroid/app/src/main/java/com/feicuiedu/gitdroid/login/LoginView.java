package com.feicuiedu.gitdroid.login;

/**
 * 这是登陆的Activity界面要继承的接口
 * Created by MMQ on 2016/7/30.
 */
public interface LoginView {

    //显示进度
    void showProgress();

    void showMessage(String msg);

    //重置WebView
    void resetWeb();

    //导航切换是Main页面
    void navigateToMain();
}

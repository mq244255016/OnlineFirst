package com.feicuiedu.gitdroid.login;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.activity.Home_main_Activity;
import com.feicuiedu.gitdroid.http.GitHubApi;
import com.feicuiedu.gitdroid.util.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

/**
 * 授权登陆
 * 1.GitHub会有一个授权url(用的webVIew)
 * 2.同意授权后，将重定向到另一url,带出临时授权码code
 * 3.用cide去授权，得到token
 * 4.使用token就能访问用户接口，得到用户数据
 * Created by MMQ on 2016/7/30.
 *
 * 因自己的代码出现位置错误，所以这里使用袁超代码，自己代码附在下面被注释
 */
public class LoginActivity extends AppCompatActivity implements LoginView {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.gifImageView)
    GifImageView gifImageView;
    private LoginPresenter loginPresenter;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_login);
    }

    @Override public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loginPresenter = new LoginPresenter(this);
        activityUtils=new ActivityUtils(this);
        initWebView();
    }

    private void initWebView() {
        // 删除所有的Cookie,主要为了清除以前的登陆记录
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        // 授权登陆URL
        webView.loadUrl(GitHubApi.AUTH_RUL);
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        // 主要为了监听进度
        webView.setWebChromeClient(webChromeClient);
        // 监听webview(url会刷新的)
        webView.setWebViewClient(webViewClient);
    }

    private WebChromeClient webChromeClient = new WebChromeClient() {
        @Override public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress >= 100) {
                gifImageView.setVisibility(View.GONE);
            }
        }
    };

    private WebViewClient webViewClient = new WebViewClient() {
        // 每当webview"刷新"时,此方法将触发 (密码输错了时！输对了时！等等情况web页面都会刷新变化的)
        @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // 检测是不是我们的CALL_BACK
            Uri uri = Uri.parse(url);
            if (GitHubApi.CALL_BACK.equals(uri.getScheme())) {
                // 获取code
                String code = uri.getQueryParameter("code");
                // 用code做登陆业务工作
                loginPresenter.login(code);
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    };

    @Override public void showProgress() {
        gifImageView.setVisibility(View.VISIBLE);
    }

    @Override public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override public void resetWeb() {
        initWebView();
    }

    @Override public void navigateToMain() {
        activityUtils.startActivity(Home_main_Activity.class);
        finish();
    }


//自己的代码，因为出现未知错误，这里被注释掉，改用袁超的代码
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        loginPresenter=new LoginPresenter(this);
//        ButterKnife.bind(this);
//    }
//
//
//    /*
//    自己创建的类，里面三个空的方法，为了来是代码看起来清晰一点
//     */
//    @Override
//    public void setLayout() {
//
//
//    }
//
//    @Override
//    public void inDate() {
//        initWebView();
//    }
//
//    @Override
//    public void setVIew() {
//
//    }
//
//    /*
//    创建的方法，一会要进行调用
//     */
//    private void initWebView() {
//        // 删除所有的Cookie,主要为了清除以前的登陆记录
//        CookieManager cookieManager = CookieManager.getInstance();
//        cookieManager.removeAllCookie();
//        // 授权登陆URL
//        webView.loadUrl(GitHubApi.AUTH_RUL);
//        webView.setFocusable(true);
//        webView.setFocusableInTouchMode(true);
//        // 主要为了监听进度
//        webView.setWebChromeClient(webChromeClient);
//        // 监听webview(url会刷新的)
//        webView.setWebViewClient(webViewClient);
//    }
//
//    //帮助webView处理各种通知
//    private WebChromeClient webChromeClient = new WebChromeClient() {
//        @Override public void onProgressChanged(WebView view, int newProgress) {
//            if (newProgress >= 100) {
//                gifImageView.setVisibility(View.GONE);
//            }
//        }
//    };
//    private WebViewClient webViewClient = new WebViewClient() {
//        // 每当webview"刷新"时,此方法将触发 (密码输错了时！输对了时！等等情况web页面都会刷新变化的)
//        @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            // 检测是不是我们的CALL_BACK
//            Uri uri = Uri.parse(url);
//            if (GitHubApi.CALL_BACK.equals(uri.getScheme())) {
//                // 获取code
//                String code = uri.getQueryParameter("code");
//                // 用code做登陆业务工作
//                loginPresenter .login(code);
//                return true;
//            }
//            return super.shouldOverrideUrlLoading(view, url);
//        }
//    };
//
//
//    /*
//    实现自己写的登陆接口，覆写的方法
//     */
//
//
//    @Override
//    public void showProgress() {
//        gifImageView.setVisibility(View.VISIBLE);
//
//    }
//
//    @Override
//    public void showMessage(String msg) {
//        activityUtils.showToast(msg);
//
//    }
//
//    @Override
//    public void resetWeb() {
//        initWebView();
//
//    }
//
//    @Override
//    public void navigateToMain() {
//        Intent i=new Intent(LoginActivity.this, Home_main_Activity.class);
//        startActivity(i);
//        finish();
//    }

}

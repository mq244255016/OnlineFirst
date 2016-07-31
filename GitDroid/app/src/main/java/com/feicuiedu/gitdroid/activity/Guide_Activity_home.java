package com.feicuiedu.gitdroid.activity;

import android.view.View;
import android.widget.Button;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.base.BaseActivity;
import com.feicuiedu.gitdroid.login.LoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MMQ on 2016/7/26.
 */
public class Guide_Activity_home extends BaseActivity {
    @Bind(R.id.btnLogin)
    Button btnLogin;
    @Bind(R.id.btnEnter)
    Button btnEnter;
    @Override
    public void setLayout() {
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    @Override
    public void inDate() {

    }



    @Override
    public void setVIew() {

    }





    @OnClick({R.id.btnLogin, R.id.btnEnter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                intentWhere(this, LoginActivity.class,0);
                break;
            case R.id.btnEnter:
                intentWhere(this,Home_main_Activity.class,0);
                break;
        }
    }


}

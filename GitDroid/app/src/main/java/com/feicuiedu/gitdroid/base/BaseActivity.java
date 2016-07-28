package com.feicuiedu.gitdroid.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by MMQ on 2016/7/27.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout();
        inDate();

        setVIew();
    }

    public abstract void setLayout();

    public abstract void inDate();



    public abstract void setVIew();


    public void intentWhere(Context ct,Class cs,int finsh){
        Intent intent=new Intent(ct,cs);
        startActivity(intent);

        if(finsh==0){
            finish();
        }

    }
}

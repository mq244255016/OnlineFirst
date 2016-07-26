package com.feicuiedu.gitdroid.fragment.guide_page;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.feicuiedu.gitdroid.R;

/**
 * Created by MMQ on 2016/7/26.
 */
public class Page_one extends FrameLayout {
    public Page_one(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    /*
    自定义方法，用来加载布局
     */
    public void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_0,this,true);
    }
}

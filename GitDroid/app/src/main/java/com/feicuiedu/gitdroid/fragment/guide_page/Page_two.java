package com.feicuiedu.gitdroid.fragment.guide_page;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.feicuiedu.gitdroid.R;

/**
 * Created by MMQ on 2016/7/26.
 * 引导界面的最后一张界面，在这个界面要实现里面一些控件的动画效果，使用第三方动画库
 */
public class Page_two extends FrameLayout {


    public Page_two(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /*
     自定义方法，用来加载布局
      */
    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_1, this, true);
    }
}

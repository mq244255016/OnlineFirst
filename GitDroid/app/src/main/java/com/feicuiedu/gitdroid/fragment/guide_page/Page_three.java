package com.feicuiedu.gitdroid.fragment.guide_page;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.feicuiedu.gitdroid.R;

/**
 * Created by MMQ on 2016/7/26.
 */
public class Page_three extends FrameLayout {

//    @Bind(R.id.ivBubble1)
    ImageView ivBubble1;
//    @Bind(R.id.ivBubble2)
    ImageView ivBubble2;
//    @Bind(R.id.ivBubble3)
    ImageView ivBubble3;

    public Page_three(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    /*
   自定义方法，用来加载布局
    */
    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_2, this, true);
        ivBubble1= (ImageView) findViewById(R.id.ivBubble1);
        ivBubble2= (ImageView) findViewById(R.id.ivBubble2);
        ivBubble3=(ImageView) findViewById(R.id.ivBubble3);

        ivBubble1.setVisibility(GONE);
        ivBubble2.setVisibility(GONE);
        ivBubble3.setVisibility(GONE);
    }

    public void runThread(){
        postDelayed(new Runnable() {
            @Override
            public void run() {
                ivBubble1.setVisibility(VISIBLE);
                YoYo.with(Techniques.FadeInRight).duration(3000).playOn(ivBubble1);

            }
        },100);

        postDelayed(new Runnable() {
            @Override
            public void run() {
                ivBubble2.setVisibility(VISIBLE);
                YoYo.with(Techniques.FadeInUp).duration(3000).playOn(ivBubble2);


            }
        },1500);

        postDelayed(new Runnable() {
            @Override
            public void run() {
                ivBubble3.setVisibility(VISIBLE);
                YoYo.with(Techniques.FadeInLeft).duration(3000).playOn(ivBubble3);

            }
        },3000);

    }
}

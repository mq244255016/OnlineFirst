package com.feicuiedu.gitdroid.fragment;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.adapter.Guide_Adapter;
import com.feicuiedu.gitdroid.fragment.guide_page.Page_three;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by MMQ on 2016/7/26.
 */
public class Guide_Fragment extends Fragment {
    View view;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.indicator)
    CircleIndicator indicator;
    private Guide_Adapter guide_adapter;

    private FrameLayout framelayout;//当前页面的layout布局

    private FrameLayout framePhone;//屏幕中间的手机

    private ImageView phoneFont_img;//手机屏幕中间的字体

    private int greenColor;
    private int colorRed;
    private int colorYellow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_splash_pager, container, false);
        setLayout();
        GetView();
        setView();

        return view;
    }

    public void setLayout() {
        guide_adapter=new Guide_Adapter(getContext());
        greenColor=getResources().getColor(R.color.colorGreen);
        colorRed = getResources().getColor(R.color.colorRed);
        colorYellow = getResources().getColor(R.color.colorYellow);
    }

    ;

    public void GetView() {
        ButterKnife.bind(this, view);
        framelayout= (FrameLayout) view.findViewById(R.id.content);
        framePhone= (FrameLayout) view.findViewById(R.id.layoutPhone);
        phoneFont_img= (ImageView) view.findViewById(R.id.ivPhoneFont);


    }

    ;

    public void setView() {
        viewPager.setAdapter(guide_adapter);
        //下面按钮的绑定viewPager
        indicator.setViewPager(viewPager);

        //添加颜色滑动改变背景颜色的监听
        viewPager.addOnPageChangeListener(setBackgroundColor);

        //滑动的时候实现手机的位移动画和手机的缩放
        viewPager.addOnPageChangeListener(setPhone);



    }

        /*
        根据手机屏幕的滑动和屏幕的偏移量，偏移量正好为0-1，同过属性动画的一个子类
        argbEvaluator来根据偏移量来改变背景的颜色
         */
        private ViewPager.OnPageChangeListener setBackgroundColor=new ViewPager.OnPageChangeListener() {
                final ArgbEvaluator argbEvaluator = new ArgbEvaluator();
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //这是第一个界面到第二个界面颜色的改变
                if(position==0){
                    //颜色的改变是动态的偏移量，从绿色到红色
                    int color= (int) argbEvaluator.evaluate(positionOffset,greenColor,colorRed);
                    framelayout.setBackgroundColor(color);
                    return;
                }
                if (position==1){
                    int color= (int) argbEvaluator.evaluate(positionOffset,colorRed,colorYellow);
                    framelayout.setBackgroundColor(color);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

        /*
        根据手机页面的移动，和偏移量的变化，实现引导界面手机的放大和缩小还有位移动画
         */
            private ViewPager.OnPageChangeListener setPhone=new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //第一个页面到第二个页面之前的偏移来设置
                if(position==0){
                    /*
                    手机的缩放,因为刚开始手机有个刚开始的大小，所以是3，
                    然后加上偏移量加上剩余的乘上百分比,实现从第一个界面直接跳转到第二个页面时中间手机的从小到大
                     */

                    float scale=0.3f+positionOffset*0.7f;
                    framePhone.setScaleX(scale);
                    framePhone.setScaleY(scale);

                    /*
                    手机的平移,因为偏移量是从0移动到1，所以这里就是从-360移动到0,因为没有计算屏幕的大小，
                    是直接给的固定值，所有当手机屏幕过小时可能出现移动到屏幕外不显示的情况。
                     */

                    int move= (int) ((positionOffset-1)*200);
                    framePhone.setTranslationX(move);

                    //手机字体的渐变效果，可以利用偏移量来实现其中图片的的从无到有

                    phoneFont_img.setAlpha(positionOffset);


                }
                //在第二个页面，通过偏移量，实现根据屏幕的滑动让让手机移出,这里使用的是偏移的px
                if(position==1){
                    framePhone.setTranslationX(-positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                /*
                进行判断，如果到了第三张页面，就调用第三正页面里的方法，实现第三张引导界面
                里面几个控件的动画效果。
                 */
                if(position==2){
                    Page_three page_three= (Page_three) guide_adapter.getList().get(position);
                    page_three.runThread();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    ;


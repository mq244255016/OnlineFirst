package com.feicuiedu.gitdroid.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.feicuiedu.gitdroid.fragment.guide_page.Page_one;
import com.feicuiedu.gitdroid.fragment.guide_page.Page_three;
import com.feicuiedu.gitdroid.fragment.guide_page.Page_two;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MMQ on 2016/7/26.
 */
public class Guide_Adapter extends PagerAdapter {
    Context Ct;
    List<View> list;

    public Guide_Adapter(Context ct) {
        Ct = ct;
        list=new ArrayList<>();
        list.add(new Page_one(Ct,null));
        list.add(new Page_two(Ct,null));
        list.add(new Page_three(Ct,null));

    }

    public List<View> getList() {
        return list;
    }

    public void setList(List<View> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position),0);
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

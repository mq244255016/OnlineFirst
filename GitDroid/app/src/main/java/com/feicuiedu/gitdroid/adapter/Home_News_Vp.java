package com.feicuiedu.gitdroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MMQ on 2016/7/27.
 */
public class Home_News_Vp extends FragmentPagerAdapter {
//    Context Ct;
   List<Fragment> list;
    List<String> tittle;
    LayoutInflater layoutInflater;

    public Home_News_Vp(FragmentManager fm) {
        super(fm);
        list=new ArrayList<>();
        tittle=new ArrayList<>();
    }

    public List<Fragment> getList() {
        return list;
    }

    public void setList(List<Fragment> list) {
        this.list = list;
    }

    public List<String> getTittle() {
        return tittle;
    }

    public void setTittle(List<String> tittle) {
        this.tittle = tittle;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tittle.get(position) ;
    }
}

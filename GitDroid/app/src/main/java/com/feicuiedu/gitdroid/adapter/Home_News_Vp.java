package com.feicuiedu.gitdroid.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.feicuiedu.gitdroid.fragment.News_Item_Fragment;
import com.feicuiedu.gitdroid.entity.Language;

import java.util.List;

/**
 * Created by MMQ on 2016/7/27.
 */
public class Home_News_Vp extends FragmentPagerAdapter {
//    Context Ct;
   List<Language> list;



    public Home_News_Vp(FragmentManager fm ,Context context) {
        super(fm);
//        list=new ArrayList<>();

        list=Language.getDefaultLanguages(context);
    }

    public List<Language> getList() {
        return list;
    }

    public void setList(List<Language> list) {
        this.list = list;
    }


    @Override
    public Fragment getItem(int position) {
        return News_Item_Fragment.getInstance(list.get(position)) ;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getName() ;
    }
}

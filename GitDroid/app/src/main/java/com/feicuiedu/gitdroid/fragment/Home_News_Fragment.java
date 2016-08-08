package com.feicuiedu.gitdroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.adapter.Home_News_Vp;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by MMQ on 2016/7/27.
 */
public class Home_News_Fragment extends Fragment {
    View view;
    List<Fragment> fragmentList;
    List<String> tittleList;
    Home_News_Vp mViewPagerAdapter;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hot_repo, container, false);
        ButterKnife.bind(this, view);
        Indate();
        GetView();
        setView();

        return view;
    }

    public void Indate() {
        mViewPagerAdapter = new Home_News_Vp(getChildFragmentManager(), getContext());
        tittleList = new ArrayList<>();
        fragmentList = new ArrayList<>();

    }

    ;

    public void GetView() {


    }

    ;

    public void setView() {

        Log.i("msg", "这是tittle的长度" + tittleList.size());
        Log.i("msg", "这是fraList的长度" + fragmentList.size());
        viewPager.setAdapter(mViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    ;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

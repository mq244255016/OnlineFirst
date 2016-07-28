package com.feicuiedu.gitdroid.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.base.BaseActivity;
import com.feicuiedu.gitdroid.fragment.Home_News_Fragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by MMQ on 2016/7/27.
 */
public class Home_main_Activity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.container)
    FrameLayout container;
    @Bind(R.id.navigationView)
    NavigationView navigationView;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toogle;
    Home_News_Fragment home_news_fragment;



    @Override
    public void setLayout() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void inDate() {
        home_news_fragment=new Home_News_Fragment();

    }



    @Override
    public void setVIew() {

        setSupportActionBar(toolbar);

        toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.togle_close);
        toogle.syncState();
        drawerLayout.setDrawerListener(toogle);

        showFramgent();

        navigationView.setNavigationItemSelectedListener(navigationListener);


    }

    private NavigationView.OnNavigationItemSelectedListener navigationListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.github_hot_repo:
                    Toast.makeText(Home_main_Activity.this, "最热门", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.github_hot_coder:
                    Toast.makeText(Home_main_Activity.this, "开发者", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.github_trend:
                    Toast.makeText(Home_main_Activity.this, "流行趋势", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    };

    public void showFramgent(){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.container,home_news_fragment);
        ft.commit();
    }


}
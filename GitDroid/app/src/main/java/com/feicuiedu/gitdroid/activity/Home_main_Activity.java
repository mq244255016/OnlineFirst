package com.feicuiedu.gitdroid.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.base.BaseActivity;
import com.feicuiedu.gitdroid.fragment.Home_News_Fragment;
import com.feicuiedu.gitdroid.login.LoginActivity;
import com.feicuiedu.gitdroid.login.UserRepo;
import com.feicuiedu.gitdroid.util.ActivityUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by MMQ on 2016/7/27.
 */
public class Home_main_Activity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
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

    private ImageLoader imageLoader;//按照网上的方法进行实例化，不然会出现没有初始化的错误


    private ActivityUtils activityUtils;//袁超所创建爱你工具类
    private Button btnLogin;//这是抽屉当中的登陆按钮

    private ImageView ivIcon;


    @Override
    public void setLayout() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    public void inDate() {
        home_news_fragment=new Home_News_Fragment();
        activityUtils = new ActivityUtils(this);

    }



    @Override
    public void setVIew() {

        //抽屉的监听
        setSupportActionBar(toolbar);

        //抽屉和Toobar的连用
        toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.togle_close);
        toogle.syncState();
        drawerLayout.setDrawerListener(toogle);

        showFramgent();

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(// 构建抽屉的监听
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();// 根据drawerlayout同步其当前状态

        /*
        登陆按钮的点击
         */

        //这是网上找到的find  navigation中的控件
        View headerLayout = navigationView.inflateHeaderView(R.layout.layout_nav_header_main);
        btnLogin = (Button) headerLayout.findViewById(R.id.btnLogin);
        ivIcon= (ImageView) headerLayout.findViewById(R.id.ivIcon);

        //袁超的方法，这里先注释掉
//        btnLogin = ButterKnife.findById(btnLogin.getHeaderView(0), R.id.btnLogin);
//        ivIcon = ButterKnife.findById(navigationView.getHeaderView(0), R.id.ivIcon);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                activityUtils.startActivity(LoginActivity.class);
                finish();
            }
        });

    }

    //自己之前写的在NavigationView当中的一些mneu的监听，这里注释掉
//    private NavigationView.OnNavigationItemSelectedListener navigationListener = new NavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
//
//                case R.id.github_hot_repo:
//                    Toast.makeText(Home_main_Activity.this, "最热门", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.github_hot_coder:
//                    Toast.makeText(Home_main_Activity.this, "开发者", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.github_trend:
//                    Toast.makeText(Home_main_Activity.this, "流行趋势", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//            return false;
//        }
//    };

    public void showFramgent(){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.container,home_news_fragment);
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 没有授权的话
        if (UserRepo.isEmpty()) {
            btnLogin.setText(R.string.login_github);
            return;
        }
        btnLogin.setText(R.string.switch_account);
        // 设置Title
        getSupportActionBar().setTitle(UserRepo.getUser().getName());
        // 设置用户头像
        String photoUrl = UserRepo.getUser().getAvatar();

        //授权后修改头像出现错误，使用前必须初始化设置,以下两局是按照网上的方法进行修改
        imageLoader =ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));

        imageLoader.getInstance().displayImage(photoUrl, ivIcon);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return false;
    }
}

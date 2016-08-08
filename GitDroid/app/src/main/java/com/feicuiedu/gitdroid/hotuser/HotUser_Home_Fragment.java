package com.feicuiedu.gitdroid.hotuser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.feicuiedu.gitdroid.Logical_Layer.HotUser_Detail_info;
import com.feicuiedu.gitdroid.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by MMQ on 2016/8/2.
 */
public class HotUser_Home_Fragment extends Fragment implements HotUser_logic.HotUser_Interface {
    View view;
    @Bind(R.id.hot_user_lv)
    ListView hotUserLv;
    HotUser_Adapter hotUserAdapter;
    @Bind(R.id.hot_ser_ptr)
    PtrClassicFrameLayout hotSerPtr;
    @Bind(R.id.hot_user_LoadError_Tv)
    TextView hotUserLoadErrorTv;
    private HotUser_logic hotUserLogic;//P层的声明

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hot_user, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }


    /*
    一些方法的实例化
     */
    public void init() {
        //P层的实例化，这里传入的是实现了接口的当前类
        hotUserLogic = new HotUser_logic(this);
        hotUserAdapter = new HotUser_Adapter(getContext());

        hotUserLv.setAdapter(hotUserAdapter);

        inRefresh();//刷新的方法

    }


    /*
    下拉刷新的监听和第三方的酷炫效果
     */
    public void inRefresh() {
        Log.i("hot", "进入了视图界面的竖线方法");
        //当前对象当作key，来确保太短时间连续刷新两次不会同时执行
        hotSerPtr.setLastUpdateTimeRelateObject(this);
        //关闭上啦的狂需要用的时间
        hotSerPtr.setDurationToCloseHeader(2000);

        //上拉刷新的监听，在这里调用p层加如数据的方法
        hotSerPtr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                Log.i("hot", "要启动逻辑层的方法");
                //调用P层加载数据的方法
                hotUserLogic.refresh_logic();

            }
        });
        // 以下代码（只是修改了header样式）
        StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.initWithString("I LIKE " + "mmm");
        header.setPadding(0, 60, 0, 60);
        // 修改Ptr的HeaderView效果
        hotSerPtr.setHeaderView(header);
        hotSerPtr.addPtrUIHandler(header);
        hotSerPtr.setBackgroundResource(R.color.colorBlue);
    }

    @Override
    public void hotRefreshData(List<HotUser_Detail_info> refreshList) {
        hotUserAdapter.setList(refreshList);
        Log.i("hot","这是主界面刷新的list长度"+refreshList.size());
        Log.i("hot","主界面"+refreshList.toString());
        hotUserAdapter.notifyDataSetChanged();
    }


    @Override//主视图的显示
    public void hotRefreshShowList() {
        hotSerPtr.setVisibility(View.VISIBLE);//下拉界面的显示
//        hotSerPtr.setVisibility(View.GONE);//下拉加载的视图影藏
        hotUserLoadErrorTv.setVisibility(View.GONE);//加载错误视图隐藏

    }

    @Override//加载错误的时候要显示的视图
    public void hotRefreshError() {
//        hotSerPtr.setVisibility(View.GONE);//下拉界面的隐藏
        hotSerPtr.setVisibility(View.GONE);//下拉加载的视图影藏
        hotUserLoadErrorTv.setVisibility(View.VISIBLE);//加载错误视图显示

    }

    @Override//刷新的停止
    public void hideRefreshView() {
        hotSerPtr.refreshComplete();//停止刷新的方法
    }
//--------------------------------------------------------------------------------------------
    @Override//加载更多获得数据的方法
    public void hotLoadMoreData(List<HotUser_Detail_info> loadList) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

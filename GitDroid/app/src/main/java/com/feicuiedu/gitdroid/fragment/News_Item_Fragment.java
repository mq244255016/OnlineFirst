package com.feicuiedu.gitdroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.feicuiedu.gitdroid.Logical_Layer.News_Fragment_Logic;
import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.adapter.News_Fragment_List_Adapter;
import com.feicuiedu.gitdroid.entity.Repo;
import com.feicuiedu.gitdroid.myinterface.News_Refresh_And_LoadMore;
import com.feicuiedu.gitdroid.entity.Language;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by MMQ on 2016/7/27.
 * 主界面ViewPaegr当中的每个子Item项，为Fragment,继承下拉刷新的接口
 * 覆写里面所需要的方法。在用逻辑层判断方法的实现时间，这边在调用逻辑层
 * 完成mvp设计模式。
 */
public class News_Item_Fragment extends Fragment implements News_Refresh_And_LoadMore {
    View view;
    @Bind(R.id.lvRepos)
    ListView lvRepos;
    @Bind(R.id.ptrClassicFrameLayout)
    PtrClassicFrameLayout ptrClassicFrameLayout;//第三方下拉动画效果
    @Bind(R.id.emptyView)
    TextView emptyView;//加载数据失败显示的内容
    @Bind(R.id.errorView)
    TextView errorView;//加载数据失败显示的内容

    private FooterView footerView;//上拉加载更多的视图

    News_Fragment_List_Adapter mListAdapter;
    News_Fragment_Logic mNews_Logic;//逻辑层的方法，视图的刷新

//    News_Load_More_Interface news_load_more_interface;


    //自定义的一个Key,之后要通过这个KEY,才能拿到数据
    private static final String KEY_LANGUAGE = "key_language";

    //自定义的单例，要通过这个方法才能拿到language。
    public static News_Item_Fragment getInstance(Language language) {
        News_Item_Fragment fragment = new News_Item_Fragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_LANGUAGE, language);
        fragment.setArguments(args);
        return fragment;
    }

    private Language getLanguage() {
        return (Language) getArguments().getSerializable(KEY_LANGUAGE);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        ButterKnife.bind(this, view);
        inDada();
        setView();
        return view;
    }

    //在onCreate中执行的方法，为了看着清晰。
    public void inDada() {
        mListAdapter = new News_Fragment_List_Adapter(getContext());


        //逻辑层方法，用来实现下拉刷新
        mNews_Logic = new News_Fragment_Logic(this, getLanguage());


    }

    ;

    //在onCreate中执行的方法，为了看着清晰。
    public void setView() {
        lvRepos.setAdapter(mListAdapter);
        initPullToRefresh();
        newsLoadMore();
    }

    ;


    //下拉刷新效果的设置
    public void initPullToRefresh() {
        //使用当前对象做为key，来记录上一次的刷新时间，如果两次下拉时间间隔太短就只执行一次
        ptrClassicFrameLayout.setLastUpdateTimeRelateObject(this);

        //关闭Head所用的时间
        ptrClassicFrameLayout.setDurationToCloseHeader(1500);

        //修改Heard下拉出来的样式
        //用这个对象来修改下拉出现的效果
        StoreHouseHeader header = new StoreHouseHeader(getContext());
        //设置下拉出现的字体
        header.initWithString("I Like" + " MQ");
        //距离四周的距离 左  上  右  下
        header.setPadding(0, 60, 0, 60);

        //酷炫的效果开始了
        ptrClassicFrameLayout.setHeaderView(header);
        ptrClassicFrameLayout.addPtrUIHandler(header);
        ptrClassicFrameLayout.setBackgroundResource(R.color.colorPrimary);

        //下拉刷新的监听,当下拉时，会触发此方法
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

                Log.i("xiala", "触发下拉的监听");
                //调用逻辑层代码，进行刷新，把当前对象传过去
                mNews_Logic.refresh();
            }
        });
    }



    /*
    视图的所有显示的方法
     */

    //显示正常加载出来的内容
    //下拉刷新的视图-----------------------------------------------------------------------
    @Override
    public void showCorrectData() {
        ptrClassicFrameLayout.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    ;

    //显示加载错误的时候的内容
    @Override
    public void showErrorView(String errorMessage) {
        ptrClassicFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    ;

    //显示空白时候的内容
    @Override
    public void showNullView() {
        ptrClassicFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    ;


    //停止刷新,把上面弹出的框收起来
    @Override
    public void stopRefresh() {
        ptrClassicFrameLayout.refreshComplete();
    }

    //显示提示信息
    @Override
    public void showMessage(String str) {

    }


    //刷新数据
    @Override
    public void refreshData(List<Repo> data) {
        Log.i("xiala", data + "这是list的长度");
        mListAdapter.setList(data);
        mListAdapter.notifyDataSetChanged();

    }

    ;

//---------更多-----------下拉刷新结束


    //上拉加载更多的方法-----------------------------------------------------
    public void newsLoadMore() {
        footerView = new FooterView(getContext());
        //第三方，判断状态
        Mugen.with(lvRepos, new MugenCallbacks() {
            @Override
            //判断滑动到底部，进行对应的操作
            public void onLoadMore() {
                //触发逻辑层里面的加载更多的方法
                mNews_Logic.loadMore();

            }

            @Override
            //是否在加载，避免重复的加载
            public boolean isLoading() {
                //返回第一个是确定上面有值，第二个是确定他的状态
                return lvRepos.getFooterViewsCount() > 0 && footerView.isLoading();
            }

            @Override
            //是否所有数据已经加载
            public boolean hasLoadedAllItems() {
                return false;
            }
        }).start();
    }


    @Override
    public void showLoadingMore() {
        lvRepos.addFooterView(footerView);
        footerView.loading();
    }

    @Override
    public void showLoadingMoreError() {
        footerView.loadError("加载错误");
    }

    @Override
    public void showLoadingNull() {
        footerView.loadNoMore();

    }

    @Override
    public void hideShowLoading() {
        lvRepos.removeFooterView(footerView);


    }

    @Override
    public void addMoreData(List<Repo> list) {
        //加载更多
        mListAdapter.setList(list);
        Log.i("shangla", list.size() + "这是上拉加载更多的");
        mListAdapter.notifyDataSetChanged();

    }


    @Override
    //解除绑定
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

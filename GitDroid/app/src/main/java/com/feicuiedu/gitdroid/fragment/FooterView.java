package com.feicuiedu.gitdroid.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.myinterface.LoadMore;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by MMQ on 2016/7/28.
 */
public class FooterView extends FrameLayout implements LoadMore {
    private static final int LOADING = 0;//加载中
    private static final int LOADNOMORE = 1;//没有加载更多
    private static final int LOADERROR = 2;//加载错误

    //加载状态
    private int isdLoding=LOADING;//默认为加载中
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.tv_complete)
    TextView tvComplete;
    @Bind(R.id.tv_error)
    TextView tvError;


    public FooterView(Context context) {
        super(context);
        init();
    }


    //界面的创建，加载界面。
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_load_footer, null);
        //对Butter的绑定
        ButterKnife.bind(this);
    }


    @Override
    //加载中
    public void loading() {

        isdLoding=LOADING;
        progressBar.setVisibility(VISIBLE);
        tvComplete.setVisibility(GONE);
        tvError.setVisibility(GONE);

    }

    @Override
    //加载错误
    public void loadError() {
        isdLoding=LOADERROR;
        progressBar.setVisibility(GONE);
        tvComplete.setVisibility(GONE);
        tvError.setVisibility(VISIBLE);

    }

    @Override
    //没有加载更多
    public void loadNoMore() {
        isdLoding=LOADNOMORE;
        progressBar.setVisibility(GONE);
        tvComplete.setVisibility(VISIBLE);
        tvError.setVisibility(GONE);

    }

    //错误的点击，如果出现加载错误，点击之后会重新加载
    @Override
    public void errorOnclick(OnClickListener onClickListener) {
        tvError.setOnClickListener(onClickListener);
    }
}

package com.feicuiedu.gitdroid.myinterface;

import android.view.View;

/**
 * Created by MMQ on 2016/7/28.
 */
public interface Foot_Load {
        //判断是否在加载中
        public abstract boolean isLoading();
        public abstract  void loading();

        public abstract  void loadError(String errorMsg);

        public abstract  void loadNoMore();

        public abstract  void errorOnclick(View.OnClickListener onClickListener);

}

package com.feicuiedu.gitdroid.myinterface;

import android.view.View;

/**
 * Created by MMQ on 2016/7/28.
 */
public interface LoadMore  {
        public abstract  void loading();

        public abstract  void loadError();

        public abstract  void loadNoMore();

        public abstract  void errorOnclick(View.OnClickListener onClickListener);

}

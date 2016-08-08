package com.feicuiedu.gitdroid.collect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.collect.collect_db.Collect_dbHelper;
import com.feicuiedu.gitdroid.collect.collect_db.Collect_db_Do;
import com.feicuiedu.gitdroid.collect.collect_db.Local_DB_do;
import com.feicuiedu.gitdroid.collect.collect_db.collect_entity.Collect_db_Table;
import com.feicuiedu.gitdroid.collect.collect_db.collect_entity.Local_Db_Helper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MMQ on 2016/8/3.
 *
 * 收藏界面的Fragment主界面
 */
public class Collect_Home_Fragment extends Fragment implements PopupMenu.OnMenuItemClickListener {
    View view;
    @Bind(R.id.left_Tv)
    TextView leftTv;
    @Bind(R.id.right_Btn)
    ImageButton rightBtn;
    @Bind(R.id.listView)
    ListView listView;

    Collect_Adapter collectAdapter;

    Collect_db_Do collectDbDo;//类别的数据库操作类

    Local_DB_do localDbDo;//里面数据的操作类

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorite, null);
        ButterKnife.bind(this, view);

        collectDbDo=new Collect_db_Do(Collect_dbHelper.getInstants(getContext()));
        localDbDo=new Local_DB_do(Local_Db_Helper.getInstants(getContext()));
        collectAdapter=new Collect_Adapter(getContext());
        listView.setAdapter(collectAdapter);
        return view;
    }



    @OnClick(R.id.right_Btn)
    public void onClick(View MenuView) {
        PopupMenu popupMenu=new PopupMenu(getContext(),MenuView);
        popupMenu.setOnMenuItemClickListener(this);

        //Menu项（这上面是全部和未分类，就是本身只在Menu上添加的东西）
        popupMenu.inflate(R.menu.menu_popup_repo_groups);

        /* 想mueu项上面添加自己的数据**/
        Menu menu=popupMenu.getMenu();
        List<Collect_db_Table> list=collectDbDo.findAll();
        Log.i("collect","这是收藏主界面的list"+list.size());
        /* 拿到集合之后，把数据添加到Menu中，一个ID,和名字**/
        for (Collect_db_Table  collectDbTable:list){
//
            menu.add(Menu.NONE,collectDbTable.getId(),Menu.NONE,collectDbTable.getName());
        }
        popupMenu.show();

    }



    @Override
    public boolean onMenuItemClick(MenuItem item) {
        String tittle =item.getTitle().toString();//拿到没个item点击的标题
        leftTv.setText(tittle);//每次点击之后左边的TextView变成对应的

        setDate(item.getItemId());//这里调用下面写的方法，传过去点击的item的id
        return true;
    }

    /*
    Menu中每个item的点击项点击的方法，在上面调用
     */
    private void setDate(int id){
        switch (id){
            case R.id.repo_group_all://收藏的所有的点击
            collectAdapter.setList(localDbDo.findAll());//点击所有的时候直接返回所有的内容
                break;
            case R.id.repo_group_no://未分类的点击，直接根据内容查找到没有分类的显示
            collectAdapter.setList(localDbDo.queryForNoGroup());
                break;
            default://根据点击的时候的id的不同，传入数据库查找方法中，根据不同id显示不同的视距
                collectAdapter.setList(localDbDo.queryForGroupId(id));

                break;
        }
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

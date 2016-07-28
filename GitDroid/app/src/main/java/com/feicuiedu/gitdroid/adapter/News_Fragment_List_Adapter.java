package com.feicuiedu.gitdroid.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feicuiedu.gitdroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by MMQ on 2016/7/27.
 */
public class News_Fragment_List_Adapter extends BaseAdapter {
    Context Ct;
    List<String> list;
    LayoutInflater layoutInflater;

    public News_Fragment_List_Adapter(Context ct) {
        Ct = ct;
        list = new ArrayList<>();
        layoutInflater = (LayoutInflater) Ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("adapter","进入了方法");

            convertView=layoutInflater.inflate(R.layout.layout_item_repo,null);

           ViewHolder vh=new ViewHolder(convertView);
            vh.tvRepoName.setText(list.get(position));
        Log.i("adapter",list.size()+"这是适配器的list长度");


        return convertView;

    }


    static class ViewHolder {
        @Bind(R.id.ivIcon)
        ImageView ivIcon;
        @Bind(R.id.tvRepoName)
        TextView tvRepoName;
        @Bind(R.id.tvRepoInfo)
        TextView tvRepoInfo;
        @Bind(R.id.tvRepoStars)
        TextView tvRepoStars;
        @Bind(R.id.layoutTexts)
        LinearLayout layoutTexts;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

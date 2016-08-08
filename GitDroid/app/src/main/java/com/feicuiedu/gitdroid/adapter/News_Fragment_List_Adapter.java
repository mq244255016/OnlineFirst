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
import com.feicuiedu.gitdroid.entity.Repo;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by MMQ on 2016/7/27.
 */
public class News_Fragment_List_Adapter extends BaseAdapter {
    Context Ct;
    List<Repo> list;//直接从网上拿到的数据的类型
    LayoutInflater layoutInflater;

    public News_Fragment_List_Adapter(Context ct) {
        Ct = ct;
        list = new ArrayList<>();
        layoutInflater = (LayoutInflater) Ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<Repo> getList() {
        return list;
    }

    public void setList(List<Repo> list) {
        this.list = list;
    }

    /*
    自己写的清空的方法
     */
    public void clear(){
        list.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("adapter","进入了方法");

            convertView=layoutInflater.inflate(R.layout.layout_item_repo,parent,false);

           ViewHolder vh=new ViewHolder(convertView);
            vh.tvRepoName.setText(list.get(position).getFullName());//仓库的名字
            vh.tvRepoInfo.setText(list.get(position).getDescription());//仓库的信息
            vh.tvRepoStars.setText(list.get(position).getStarCount()+"");//点赞的人数，这里记得要加“”因为是int类型

        //用ImageLoader来把网络地址转化成图片
        ImageLoader.getInstance().displayImage(list.get(position).getOwner().getAvatar(),vh.ivIcon);

        Log.i("adapter", list.size() + "这是适配器的list长度");


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

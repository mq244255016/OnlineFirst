package com.feicuiedu.gitdroid.hotuser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicuiedu.gitdroid.Logical_Layer.HotUser_Detail_info;
import com.feicuiedu.gitdroid.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MMQ on 2016/8/2.
 */
public class HotUser_Adapter extends BaseAdapter {
    Context context;
    List<HotUser_Detail_info> list;
    LayoutInflater layoutInflater;

    public HotUser_Adapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<HotUser_Detail_info> getList() {
        return list;
    }

    public void setList(List<HotUser_Detail_info> list) {
        this.list = list;
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


        ViewHolder vh =null;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.layout_hot_user_item, parent, false);
            vh=new ViewHolder();
            vh.layoutHotUserImg= (ImageView) convertView.findViewById(R.id.layout_hot_user_img);
            vh.layoutHotUserTv= (TextView) convertView.findViewById(R.id.layout_hot_user_tv);
            convertView.setTag(vh);
        }else{
            vh= (ViewHolder) convertView.getTag();
        }

        //使用ImageLoader来把网络地址转换为图片
        ImageLoader.getInstance().displayImage(list.get(position).getHeadImage(), vh.layoutHotUserImg);

        vh.layoutHotUserTv.setText(list.get(position).getUserName());
        return convertView;
    }

     class ViewHolder {
        ImageView layoutHotUserImg;

        TextView layoutHotUserTv;


    }
}

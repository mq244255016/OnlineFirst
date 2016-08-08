package com.feicuiedu.gitdroid.collect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.collect.collect_db.collect_entity.Local_DB_Info;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by MMQ on 2016/8/5.
 */
public class Collect_Adapter extends BaseAdapter {


    List<Local_DB_Info> list;
    LayoutInflater layoutInflater;

    public Collect_Adapter(Context context) {
        list = new ArrayList<>();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<Local_DB_Info> getList() {
        return list;
    }

    public void setList(List<Local_DB_Info> list) {
        this.list = list;
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
        if(convertView==null){
            convertView = layoutInflater.inflate(R.layout.layout_item_repo, parent, false);

            convertView.setTag(new ViewHolder(convertView));//绑定标签
        }
            ViewHolder vh= (ViewHolder) convertView.getTag();

        Local_DB_Info localDbInfo=list.get(position);

        vh.tvRepoName.setText(localDbInfo.getFullName());
        vh.tvRepoInfo.setText(localDbInfo.getDescription());
        vh.tvRepoStars.setText(String.format("stars : %d",localDbInfo.getStartCount()));//字符串转换方法，%d是整数转换成String类型

        ImageLoader.getInstance().displayImage(localDbInfo.getAvatar(),vh.ivIcon);//用url地址来转换成图片再设置

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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

package com.feicuiedu.gitdroid.collect.collect_db.collect_entity;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by MMQ on 2016/8/3.
 */
@DatabaseTable (tableName = "collectTable")
public class Collect_db_Table {


    /*  表里的两个属性，一个id，一个name **/
    @DatabaseField(id = true)
    private int id;

    @DatabaseField(columnName ="name")
    private String name;



    /*
    方法，返回一个本类型的集合，从本地打开，也就是repogroup.json里面
     */

    private static List<Collect_db_Table> tableList;

    public static List<Collect_db_Table> getTableList(Context Ct){
        if(tableList!=null) return tableList;

        /* 如果为空的话， 就从本地当中取，因为是json文件，所以用
        * 第三方直接转换为String类型,然后用Gson一键解析成list**/

        try {
            InputStream inputStream = Ct.getAssets().open("repogroup.json");
            String contetnt= IOUtils.toString(inputStream);
            Gson gson=new Gson();
            //一键解析成本类型的list
            tableList=gson.fromJson(contetnt, new TypeToken<List<Collect_db_Table>>() {
            }.getType());
            Log.i("collect",tableList.size()+"这是Table实体界面的list");
            return tableList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Collect_db_Table{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

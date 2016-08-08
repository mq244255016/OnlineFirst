package com.feicuiedu.gitdroid.collect.collect_db;

import android.util.Log;

import com.feicuiedu.gitdroid.collect.collect_db.collect_entity.Collect_db_Table;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by MMQ on 2016/8/3.
 */
public class Collect_db_Do {

    private Dao<Collect_db_Table, Long> dao;


    /*
    构造方法，如果实例化这个数据库操作类，就要传入DbHelper
     */
    public Collect_db_Do(Collect_dbHelper collectDbHelper) {
        try {
            dao = collectDbHelper.getDao(Collect_db_Table.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

        /** 数据库内容的添加 和 更新*/
         public void createOrUpdate(Collect_db_Table table){
             try {
                 dao.createOrUpdate(table);
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }


        /*数据库添加，添加整个list进去  **/
        public void createOrUpdate(List<Collect_db_Table> list){
            Log.i("collect","走了这个添加到这里的方法");
            for (Collect_db_Table table :list) {
                createOrUpdate(table);
            }
        }

    /*根据id来查询指定的内容**/
    public Collect_db_Table  findForId(long id){
        try {
            Collect_db_Table table=dao.queryForId(id);
            return table;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /* 查找所有的内容**/
    public List<Collect_db_Table> findAll(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }
    }
}
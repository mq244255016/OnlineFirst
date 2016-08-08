package com.feicuiedu.gitdroid.collect.collect_db;

import com.feicuiedu.gitdroid.collect.collect_db.collect_entity.Local_DB_Info;
import com.feicuiedu.gitdroid.collect.collect_db.collect_entity.Local_Db_Helper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by MMQ on 2016/8/4.
 */
public class Local_DB_do {
    private Dao<Local_DB_Info,Long> dao;

    /*
    Local数据库的操作类，用这个类必须传入LocalDbHelper，所以必须实例化
    创建数据库的类
     */
    public Local_DB_do(Local_Db_Helper localDbHelper) {
        try {
            dao=localDbHelper.getDao(Local_DB_Info.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /*
    收藏界面的内容的添加
     */
    public void localCreateOrUpdate(Local_DB_Info localDbInfo){
        try {
            dao.createOrUpdate(localDbInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    整个集合添加
     */
    public void localCreateOrUpdate(List<Local_DB_Info> list){
        for (Local_DB_Info localDbInfo:list){
            localCreateOrUpdate(localDbInfo);
        }
    }

    /*
    根据指定的ID进行查询
     */

    public Local_DB_Info findForId(long id){
        try {
           return dao.queryForId(id);
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }

    }




    /*
    查询所有
     */

    public List<Local_DB_Info> findAll(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }
    }

    /**
     * 查询本地仓库(根据不同的id，查找不同类型的数据并返回)
     *
     * @param groupId 类别ID号
     * @return 仓库列表
     */
    public List<Local_DB_Info> queryForGroupId(int groupId){
        try {
            return dao.queryForEq(Local_DB_Info.COLUMN_GROUP_ID, groupId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询本地仓库(未分类的)
     */
    public List<Local_DB_Info> queryForNoGroup(){
        try {
            return dao.queryBuilder().where().isNull(Local_DB_Info.COLUMN_GROUP_ID).query();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

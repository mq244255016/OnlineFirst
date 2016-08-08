package com.feicuiedu.gitdroid.collect.collect_db.collect_entity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.feicuiedu.gitdroid.collect.collect_db.Local_DB_do;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by MMQ on 2016/8/4.
 *
 * 收藏到本地的内容数据库的创建
 *
 * 这里要用单例模式，避免重复调用
 */
public class Local_Db_Helper extends OrmLiteSqliteOpenHelper {

    Context Ct;
    private static final String LOCAL_DB_NAME="Local.db";
    private static final int VERSION=1;


    private static  Local_Db_Helper localDbHelper;

    public static Local_Db_Helper getInstants(Context context){
        if(localDbHelper==null){
            localDbHelper=new Local_Db_Helper(context);
        }
        return localDbHelper;
    }
    private Local_Db_Helper(Context context) {
        super(context, LOCAL_DB_NAME, null, VERSION);
        this.Ct=context;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            //创建类别表  里面还没有数据 l
            TableUtils.createTableIfNotExists(connectionSource,Local_DB_Info.class);

            //把数据先添加到表中
            new Local_DB_do(this).localCreateOrUpdate(Local_DB_Info.getLocal_DB_Info(Ct));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}

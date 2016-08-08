package com.feicuiedu.gitdroid.collect.collect_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.feicuiedu.gitdroid.collect.collect_db.collect_entity.Collect_db_Table;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by MMQ on 2016/8/3.
 */
public class Collect_dbHelper extends OrmLiteSqliteOpenHelper {
    private static final String COLLECT_TABLE_NAME="collect.db";
    private static final  int VERSION=1;
    private static  Collect_dbHelper collectDbHelper;

    private  Context context;

    /*
    单例，避免重复创建
     */
    public static Collect_dbHelper getInstants(Context context){
        if(collectDbHelper==null){
            collectDbHelper=new Collect_dbHelper(context.getApplicationContext());
        }
        return collectDbHelper;
    }

    private Collect_dbHelper(Context context ) {
        super(context, COLLECT_TABLE_NAME, null, VERSION);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            //创建表
            TableUtils.createTableIfNotExists(connectionSource, Collect_db_Table.class);

            //把本地默认的数据添加到表中
            new Collect_db_Do(this).createOrUpdate(Collect_db_Table.getTableList(context));


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,Collect_db_Table.class,true);
            onCreate(database,connectionSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

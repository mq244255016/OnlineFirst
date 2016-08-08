package com.feicuiedu.gitdroid.collect.collect_db.collect_entity;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by MMQ on 2016/8/4.
 *我的收藏界面的内容的实体
 */
@DatabaseTable (tableName = "local_info")
public class Local_DB_Info {

    public static final String COLUMN_GROUP_ID = "group_id";

    @DatabaseField(id = true)
    private long id;

    @DatabaseField
    private String name;

    @DatabaseField(columnName = "full_name")
    @SerializedName("full_name")
    private String fullName;

    @DatabaseField
    private String description;

    @DatabaseField(columnName = "start_count")
    @SerializedName("stargazers_count")
    private int startCount;

    @DatabaseField(columnName = "fork_count")
    @SerializedName("forks_count")
    private int forkCount;

    @DatabaseField(columnName = "avatar_url")
    @SerializedName("avatar_url")
    private String avatar;

    // 是一个外键,可以为null
    @DatabaseField(columnName = COLUMN_GROUP_ID,foreign = true,canBeNull = true)
    @SerializedName("group")
    private Collect_db_Table dbTable;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public static String getColumnGroupId() {
        return COLUMN_GROUP_ID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStartCount() {
        return startCount;
    }

    public void setStartCount(int startCount) {
        this.startCount = startCount;
    }

    public int getForkCount() {
        return forkCount;
    }

    public void setForkCount(int forkCount) {
        this.forkCount = forkCount;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Collect_db_Table getDbTable() {
        return dbTable;
    }

    public void setDbTable(Collect_db_Table dbTable) {
        this.dbTable = dbTable;
    }

    public static List<Local_DB_Info> getLocal_DB_Info(Context context){
        try {
            InputStream inputStream=context.getAssets().open("defaultrepos.json");
            String data= org.apache.commons.io.IOUtils.toString(inputStream);
            Gson gson=new Gson();
            return gson.fromJson(data, new TypeToken<List<Local_DB_Info>>(){}.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

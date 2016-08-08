package com.feicuiedu.gitdroid.entity;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by MMQ on 2016/8/1.
 * 这个界面用于查找数据当中的标题属性，用来修改TabLayout的名字
 */
public class Language implements Serializable {

    //用于搜索
    private String path;

    //用于显示
    private String name;

    private static List<Language> languageList;//用来接受Language类型的list文件

    //一个方法，用于接受网上的数据并在其中转换，返回Language类型的list
    public static List<Language> getDefaultLanguages(Context context) {
        if (languageList != null) {
            return languageList;
        } else {

            String content = null;
            try {
                //打开自己的Assets本地的文件langs，里面的langs,json文件，这是一会拿数据时需要的
                InputStream inputStream = context.getAssets().open("langs.json");
                //直接用第三方的Io,来把输入流直接转换成String类型
                content = IOUtils.toString(inputStream);
                Gson gson = new Gson();
                //利用Gson一键解析,把数据解析成Language类型的list

                Log.i("我是解析出来的language", content.toString());

                languageList = gson.fromJson(content, new TypeToken<List<Language>>() {
                        }.getType()
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //这里在else中返回编译报错
        return languageList;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }

    @Override
    public String toString() {
        return "Language{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}





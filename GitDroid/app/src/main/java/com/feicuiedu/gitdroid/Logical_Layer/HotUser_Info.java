package com.feicuiedu.gitdroid.Logical_Layer;

import java.util.List;

/**
 * Created by MMQ on 2016/8/2.
 * 通过粉丝数查询出来的开发者的属性的外层界面，通过里面的items的list，拿出具体
 * 用户的数据
 */

public class HotUser_Info {
    private int total_count;
    private boolean incomplete_results;
    private List<HotUser_Detail_info> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<HotUser_Detail_info> getItems() {
        return items;
    }

    public void setItems(List<HotUser_Detail_info> items) {
        this.items = items;
    }
}

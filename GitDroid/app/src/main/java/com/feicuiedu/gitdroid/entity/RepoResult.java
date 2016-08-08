package com.feicuiedu.gitdroid.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by MMQ on 2016/8/1.
 * 搜索仓库响应结果
 * 上面的SerializedName是保持和网上数据相同的名字，方便之后的一键解析
 */
public class RepoResult {
    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<Repo> repoList;

    public int getTotalCount() {

        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<Repo> getRepoList() {
        return repoList;
    }

    public void setRepoList(List<Repo> repoList) {
        this.repoList = repoList;
    }
}

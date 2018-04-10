package com.qingmang.loan.entity;

import java.util.List;

/**
 * Created by jiangpw
 * on 2018/4/10 12:13
 */
public class LoanListEntity {
    private int pageNumber;
    private int pageSize;
    private int totalCount;
    private int pageCount;
    private List<LoanListContent> content;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<LoanListContent> getContent() {
        return content;
    }

    public void setContent(List<LoanListContent> content) {
        this.content = content;
    }
}

package com.hou.entity;

import java.util.List;

public class PaginatedSources<T> {

    private List<Sources> sources;
    private long totalCount;
    private int currentPage;
    private int pageSize;

    // 构造方法
    public PaginatedSources(List<Sources> sources, long totalCount, int currentPage, int pageSize) {
        this.sources = sources;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    // Getter 和 Setter 方法
    public List<Sources> getSources() {
        return sources;
    }

    public void setSources(List<Sources> sources) {
        this.sources = sources;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    // 辅助方法：判断是否有上一页
    public boolean hasPreviousPage() {
        return currentPage > 0;
    }

    // 辅助方法：判断是否有下一页
    public boolean hasNextPage() {
        return currentPage < getTotalPages();
    }

    // 辅助方法：计算总页数
    public int getTotalPages() {
        return (int) Math.ceil((double) totalCount / pageSize);
    }
}
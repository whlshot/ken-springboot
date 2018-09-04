package com.ken.model;

public class PageParam {

    private Integer pageIndex;
    private Integer pageSize;
    private Integer total;

    public PageParam() {
    }

    public PageParam(Integer pageIndex, Integer pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex != null && pageIndex != 0 ? pageIndex : 1;
    }

    public void setPageIndex(Integer pageIndex) {
        if (pageIndex == null || pageIndex <= 0) {
            pageIndex = 1;
        }
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize != null && pageSize != 0 ? pageSize : 10;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 计算分页
     *
     * @param total
     * @return
     */
    public int[] getPageParams(Integer total) {
        if (this.pageIndex == null || this.pageIndex < 1) {
            this.pageIndex = 1;
        }
        if (this.pageSize == null || this.pageSize < 1) {
            this.pageSize = 10;
        }
        int firstResult = (this.pageIndex - 1) * this.pageSize;
        int maxResult = this.pageSize;
        this.total = total;

        // 校验分页情况
        if (firstResult >= total || firstResult < 0) {
            firstResult = 0;
            this.pageIndex = 1;
        }
        return new int[]{firstResult, maxResult};
    }
}

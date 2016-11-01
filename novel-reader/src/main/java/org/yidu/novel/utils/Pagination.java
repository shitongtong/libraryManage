//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.yidu.novel.utils;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
    private int pageSize = 0;
    private int pageNumber = 0;
    private String sortColumn = null;
    private String sortOrder = "ASC";
    private int totalRecords = 0;
    private int pageRecords = 0;
    private int start = 0;
    private int end = 0;
    private int totalPages = 0;
    private String sortClass = null;
    private List<Integer> pageNumberList = new ArrayList();

    public boolean getPreviousFlag() {
        return this.pageNumber > 1;
    }

    public boolean getNextFlag() {
        return this.totalPages != this.pageNumber;
    }

    public void setPreperties(int count) {
        this.totalRecords = count;
        this.totalPages = (int)Math.ceil((double)count / (double)this.pageSize);
        this.start = this.pageSize * this.pageNumber - this.pageSize;
        this.end = this.pageSize;
        if(this.pageSize == 0) {
            this.start = 0;
            this.end = count;
            this.totalPages = 1;
        }

        if(this.sortOrder.equals("ASC")) {
            this.sortClass = "order1";
        } else {
            this.sortClass = "order2";
        }

    }

    public Pagination(int pageSize, int pageNumber) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalRecords() {
        return this.totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageRecords() {
        return this.pageRecords;
    }

    public void setPageRecords(int pageRecords) {
        this.pageRecords = pageRecords;
    }

    public int getStart() {
        return this.start != 0?this.start:(this.pageNumber - 1) * this.pageSize;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return this.end != 0?this.end:this.pageNumber * this.pageSize;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getSortOrder() {
        return this.sortOrder == null?"":this.sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSortColumn() {
        return this.sortColumn == null?"":this.sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortClass() {
        return this.sortClass;
    }

    public void setSortClass(String sortClass) {
        this.sortClass = sortClass;
    }

    public String getSortInfo() {
        String pageinfo = "";
        if(this.sortColumn != null && this.sortColumn.length() > 0) {
            pageinfo = pageinfo + " ORDER BY " + this.sortColumn + " " + this.sortOrder;
        }

        return pageinfo;
    }

    public List<Integer> getPageNumberList() {
        int i;
        if(this.totalPages <= 10) {
            for(i = 1; i <= this.totalPages; ++i) {
                this.pageNumberList.add(Integer.valueOf(i));
            }
        } else if(this.pageNumber < 6) {
            for(i = 1; i <= 10; ++i) {
                this.pageNumberList.add(Integer.valueOf(i));
            }
        } else if(this.totalPages - this.pageNumber > 5) {
            for(i = this.pageNumber - 5; i < this.pageNumber + 5; ++i) {
                this.pageNumberList.add(Integer.valueOf(i));
            }
        } else {
            for(i = this.totalPages - 9; i <= this.totalPages; ++i) {
                this.pageNumberList.add(Integer.valueOf(i));
            }
        }

        return this.pageNumberList;
    }

    public String toString() {
        return "Pagination [pageSize=" + this.pageSize + ", pageNumber=" + this.pageNumber + ", sortColumn=" + this.sortColumn + ", sortOrder=" + this.sortOrder + ", totalRecords=" + this.totalRecords + ", pageRecords=" + this.pageRecords + ", start=" + this.start + ", end=" + this.end + ", totalPages=" + this.totalPages + ", sortClass=" + this.sortClass + "]";
    }
}

package com.example.graduate_sever.common;

import java.util.List;

public class MyShenBaoRes {
    private List<List<TableData>> tableData;
    long pageTotal;

    public MyShenBaoRes(List<List<TableData>> tableData, long pageTotal) {
        this.tableData = tableData;
        this.pageTotal = pageTotal;
    }

    public List<List<TableData>> getTableData() {
        return tableData;
    }

    public void setTableData(List<List<TableData>> tableData) {
        this.tableData = tableData;
    }

    public long getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(long pageTotal) {
        this.pageTotal = pageTotal;
    }
}

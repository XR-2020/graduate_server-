package com.example.graduate_sever.common;

import com.example.graduate_sever.common.DaiShenHeTableData;
import com.example.graduate_sever.common.TableData;

import java.util.List;

public class MyDaiShenBaoRes {
    private List<DaiShenHeTableData> tableData;
    long pageTotal;

    public MyDaiShenBaoRes(List<DaiShenHeTableData> tableData, long pageTotal) {
        this.tableData = tableData;
        this.pageTotal = pageTotal;
    }

    public List<DaiShenHeTableData> getTableData() {
        return tableData;
    }

    public void setTableData(List<DaiShenHeTableData> tableData) {
        this.tableData = tableData;
    }

    public long getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(long pageTotal) {
        this.pageTotal = pageTotal;
    }
}

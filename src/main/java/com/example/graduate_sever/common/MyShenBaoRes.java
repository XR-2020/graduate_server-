package com.example.graduate_sever.common;

import java.util.List;

public class MyShenBaoRes {
    private List<TableData> tableData;
    long pageTotal;

    public MyShenBaoRes(List<TableData> tableData, long pageTotal) {
        this.tableData = tableData;
        this.pageTotal = pageTotal;
    }

}

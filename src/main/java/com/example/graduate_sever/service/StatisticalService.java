package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.StatisticalDTO;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.model.ZheXian;

import java.util.List;

public interface StatisticalService {
     List<ZheXian> selectAll(StatisticalDTO dto);
     ResVO SearchDetail(StatisticalDTO dto);
     List<TableData> selectChanXueYan(String date1,String date2);
     List<TableData> hengxiangkeyan(String date1,String date2);
     List<TableData> jiaoyanlunwen(String date1,String date2);
     List<TableData> jiaoyanxiangmu(String date1,String date2);
     List<TableData> jiaoyuguihua(String date1,String date2);
     List<TableData> keyanlunwen(String date1,String date2);
     List<TableData> keyanxiangmujiexiang(String date1,String date2);
     List<TableData> pingguzhongxin(String date1,String date2);
     List<TableData> rongyuchenghao(String date1,String date2);
     List<TableData> ruanjianzhuzuo(String date1,String date2);
     List<TableData> xuekejingsai(String date1,String date2);
     List<TableData> zhuanli(String date1,String date2);
     List<TableData> zhuzuo(String date1,String date2);
     List<TableData> zongxiangkeyan(String date1,String date2);

}

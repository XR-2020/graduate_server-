package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.StatisticalDTO;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.model.ZheXian;

import java.util.List;

public interface NewStatisticalService {

     List<TableData> selectxiaowaishijianjidi(String date1, String date2);

     List<TableData> selectshijianlixiang(String date1, String date2);

     List<TableData> selectshijianjiexiang(String date1, String date2);

     List<TableData> selectjiaocaiyejidian(String date1, String date2);

     List<TableData> selectjiaoyanyeji(String date1, String date2);

     List<TableData> selectjiaoyanlunwen(String date1, String date2);

     List<TableData> selectyouxiubishe(String date1, String date2);

     List<TableData> selectzongxiangjieti(String date1, String date2);

     List<TableData> selectkeyanhuojiang(String date1, String date2);

}

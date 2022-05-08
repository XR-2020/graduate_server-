package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.StatisticalDTO;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.model.ZheXian;

import java.util.List;

public interface NewStatisticalService {

     List<TableData> selectxiaowaishijianjidi(String date1, String date2,Integer badge);

     List<TableData> selectshijianlixiang(String date1, String date2,Integer badge);

     List<TableData> selectshijianjiexiang(String date1, String date2,Integer badge);

     List<TableData> selectjiaocaiyejidian(String date1, String date2,Integer badge);

     List<TableData> selectjiaoyanyeji(String date1, String date2,Integer badge);

     List<TableData> selectjiaoyanlunwen(String date1, String date2,Integer badge);

     List<TableData> selectyouxiubishe(String date1, String date2,Integer badge);

     List<TableData> selectzongxiangjieti(String date1, String date2,Integer badge);

     List<TableData> selectkeyanhuojiang(String date1, String date2,Integer badge);
     

    List<TableData> selectzhuanli(String date1, String date2, Integer badge);

     List<TableData> selecthengxiangkeyan(String date1, String date2, Integer badge);

     List<TableData> selectzongxiangkeyan(String date1, String date2, Integer badge);

     List<TableData> selectzhuzuo(String date1, String date2, Integer badge);

     List<TableData> selectkeyanlunwen(String date1, String date2, Integer badge);

     List<TableData> selectruanjianzhuzuoquan(String date1, String date2, Integer badge);

     List<TableData> selectkeyanxiangmujiexiang(String date1, String date2, Integer badge);

     List<TableData> selectxuekejingsai(String date1, String date2, Integer badge);

     List<TableData> selectrongyuchenghao(String date1, String date2, Integer badge);
}

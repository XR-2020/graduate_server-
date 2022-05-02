package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.common.*;
import com.example.graduate_sever.common.DTO.SheKeDTO;
import com.example.graduate_sever.common.EditUO.EditChanXueYanUO;
import com.example.graduate_sever.common.EditUO.SheKeChuEditUO;
import com.example.graduate_sever.common.UO.SheKeChuUO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.SheKeChuEntity;
import com.example.graduate_sever.model.Teacher;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface SheKeChuService {
     ResVO getAllKeYanHuoJiang(SheKeDTO dTO);
     ResVO getSearchKeYanHuoJiang(SheKeDTO dTO);
     JsonBean deleteKeYanHuoJiang(List<Integer> ids,String type);
     JsonBean deleteOneKeYanHuoJiang(Integer id,String type);
     JsonBean getKeYanHuoJiangDetail(Integer id,String type);
     JsonBean insertSheKeChu(SheKeChuUO uo) throws Exception;

    List<Integer> getKeYanHuoJiangDetailBadge(Integer id,String type);

    int editSheKeChu(SheKeChuEditUO uo);

    List<TableData> waitingSheKeChu(DTO dTO, String type);

    long waitingSheKeChuPageToTal(String type);

    Metails getSheKeChuMetails(Integer id);

    int passSheKeChuChu(Integer id, Integer ispass);
}

package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.SheKeDTO;
import com.example.graduate_sever.common.EditUO.EditNewSystemUO;
import com.example.graduate_sever.common.EditUO.KeJiChuEditUO;
import com.example.graduate_sever.common.EditUO.SheKeChuEditUO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.common.UO.KeJiChuUO;
import com.example.graduate_sever.common.UO.NewSystemUO;
import com.example.graduate_sever.common.UO.SheKeChuUO;

import java.util.List;

public interface KeJiChuService {

    ResVO getAllKeJiChu(DTO dTO);

    ResVO getSearchKeJiChu(DTO dTO);

    JsonBean deleteKeJiChu(List<Integer> ids);

    JsonBean deleteOneKeJiChu(Integer id, String type);

    JsonBean getKeJiChuDetail(Integer id, String type);

    JsonBean insertKeJiChu(KeJiChuUO uo);

    List<Integer> getKeJiChuBadge(Integer id, String type);

    int editKeJiChu(KeJiChuEditUO uo);

    Metails getKeJiChuMetails(Integer id);

    int passKeJiChu(Integer id, Integer ispass);

    List<TableData> waitingKeJiChu(DTO dTO, String type);

    long waitingKeJiChuPageToTal(String type);
}

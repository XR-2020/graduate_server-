package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.JiaoYanLunWenEntity;

public interface JiaoYanLunWenMuService {
     ResVO getAllJiaoYanLunWen(DTO jiaoYanXiangMuDTO);
     ResVO getSearchJiaoYanLunWen(DTO jiaoYanXiangMuDTO);
     JsonBean deleteJiaoYanLunWen(int[] ids);
     JsonBean deleteOneJiaoYanLunWen(Integer id);
     JsonBean getJiaoYanLunWenDetial(Integer id);
     JsonBean insertJiaoYanLunWen(JiaoYanLunWenEntity entity,Integer[] people);
}

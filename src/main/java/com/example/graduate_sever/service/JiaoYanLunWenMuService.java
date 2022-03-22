package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;

public interface JiaoYanLunWenMuService {
     ResVO getAllJiaoYanLunWen(DTO jiaoYanXiangMuDTO);
     ResVO getSearchJiaoYanLunWen(DTO jiaoYanXiangMuDTO);
     JsonBean deleteJiaoYanLunWen(int[] ids);
     JsonBean deleteOneJiaoYanLunWen(Integer id);
     JsonBean getJiaoYanLunWenDetial(Integer id);
}

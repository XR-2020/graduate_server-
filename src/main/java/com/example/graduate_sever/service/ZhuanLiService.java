package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;

public interface ZhuanLiService {
     ResVO getAllZhuanLi(DTO jiaoYanXiangMuDTO);
     ResVO getSearchZhuanLi(DTO jiaoYanXiangMuDTO);
     JsonBean deleteZhuanLi(int[] ids);
     JsonBean deleteOneZhuanLi(Integer id);
     JsonBean getZhuanLiDetial(Integer id);
}

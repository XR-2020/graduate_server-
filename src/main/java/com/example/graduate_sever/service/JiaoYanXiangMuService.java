package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;

public interface JiaoYanXiangMuService {
     ResVO getAllJiaoYan(DTO jiaoYanXiangMuDTO);
     ResVO getSearchJiaoYan(DTO jiaoYanXiangMuDTO);
     JsonBean deleteJiaoYan(int[] ids);
     JsonBean deleteOneJiaoYan(Integer id);
     JsonBean getJiaoYanDetial(Integer id);
}

package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.JiaoYanXiangMuEntity;

public interface JiaoYanXiangMuService {
     ResVO getAllJiaoYan(DTO dTO);
     ResVO getSearchJiaoYan(DTO dTO);
     JsonBean deleteJiaoYan(int[] ids);
     JsonBean deleteOneJiaoYan(Integer id);
     JsonBean getJiaoYanDetial(Integer id);
     JsonBean insertJiaoYan(JiaoYanXiangMuEntity entity);
}

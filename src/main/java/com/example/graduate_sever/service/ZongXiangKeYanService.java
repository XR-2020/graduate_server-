package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;

public interface ZongXiangKeYanService {
     ResVO getAllZongXiangKeYan(DTO jiaoYanXiangMuDTO);
     ResVO getSearchZongXiangKeYan(DTO jiaoYanXiangMuDTO);
     JsonBean deleteZongXiangKeYan(int[] ids);
     JsonBean deleteOneZongXiangKeYan(Integer id);
     JsonBean getZongXiangKeYanDetial(Integer id);
}

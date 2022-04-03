package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.HeBingEntity;

public interface HengXiangKeYanService {
     ResVO getAllHengXiangKeYan(DTO dTO);
     ResVO getSearchHengXiangKeYan(DTO dTO);
     JsonBean deleteHengXiangKeYan(int[] ids);
     JsonBean deleteOneHengXiangKeYan(Integer id);
     JsonBean getHengXiangKeYanDetial(Integer id);
     JsonBean insertHengXiangKeYan(HeBingEntity entity);
}

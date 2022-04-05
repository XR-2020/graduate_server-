package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.HeBingEntity;

public interface KeYanLunWenService {
     ResVO getAllKeYanLunWen(DTO dTO);
     ResVO getSearchKeYanLunWen(DTO dTO);
     JsonBean deleteKeYanLunWen(int[] ids);
     JsonBean deleteOneKeYanLunWen(Integer id);
     JsonBean getKeYanLunWenDetial(Integer id);
     JsonBean insertKeYanLunWen(HeBingEntity entity,Integer[] people);
}

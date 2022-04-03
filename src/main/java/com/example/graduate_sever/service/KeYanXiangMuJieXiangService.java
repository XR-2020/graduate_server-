package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.HeBingEntity;

public interface KeYanXiangMuJieXiangService {
     ResVO getAllKeYanXiangMuJieXiang(DTO dTO);
     ResVO getSearchKeYanXiangMuJieXiang(DTO dTO);
     JsonBean deleteKeYanXiangMuJieXiang(int[] ids);
     JsonBean deleteOneKeYanXiangMuJieXiang(Integer id);
     JsonBean getKeYanXiangMuJieXiangDetial(Integer id);
     JsonBean insertKeYanXiangMuJieXiang(HeBingEntity entity);
}

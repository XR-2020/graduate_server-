package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.HeBingEntity;

public interface RuanJianZhuZuoService {
     ResVO getAllRuanJianZhuZuo(DTO jiaoYanXiangMuDTO);
     ResVO getSearchRuanJianZhuZuo(DTO jiaoYanXiangMuDTO);
     JsonBean deleteRuanJianZhuZuo(int[] ids);
     JsonBean deleteOneRuanJianZhuZuo(Integer id);
     JsonBean getRuanJianZhuZuoDetial(Integer id);
     JsonBean insertRuanJianZhuZuo(HeBingEntity entity,Integer[] people);
}

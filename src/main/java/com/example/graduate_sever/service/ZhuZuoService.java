package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;

public interface ZhuZuoService {
     ResVO getAllZhuZuo(DTO dTO);
     ResVO getSearchZhuZuo(DTO dTO);
     JsonBean deleteZhuZuo(int[] ids);
     JsonBean deleteOneZhuZuo(Integer id);
     JsonBean getZhuZuoDetial(Integer id);
}

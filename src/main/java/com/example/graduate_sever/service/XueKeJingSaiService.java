package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;

public interface XueKeJingSaiService {
     ResVO getAllJingSai(DTO dTO);
     ResVO getSearchJingSai(DTO dTO);
     JsonBean deleteJingSai(int[] ids);
     JsonBean deleteOneJingSai(Integer id);
     JsonBean getJingSaiDetial(Integer id);
}

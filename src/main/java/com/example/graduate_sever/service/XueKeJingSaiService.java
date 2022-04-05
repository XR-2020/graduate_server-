package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.CompetitionEntity;

public interface XueKeJingSaiService {
     ResVO getAllJingSai(DTO dTO);
     ResVO getSearchJingSai(DTO dTO);
     JsonBean deleteJingSai(int[] ids);
     JsonBean deleteOneJingSai(Integer id);
     JsonBean getJingSaiDetial(Integer id);
     JsonBean insertJingSai(CompetitionEntity entity,Integer[] people);
}

package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.StatisticalDTO;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.model.ZheXian;

import java.util.List;

public interface StatisticalService {
     List<ZheXian> selectAll(StatisticalDTO dto);
     ResVO SearchDetail(StatisticalDTO dto);
}

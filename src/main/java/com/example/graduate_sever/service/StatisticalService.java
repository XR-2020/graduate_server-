package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.StatisticalDTO;
import com.example.graduate_sever.common.ResVO;

public interface StatisticalService {
     ResVO selectAll(StatisticalDTO dto);
     ResVO SearchDetail(StatisticalDTO dto);
}

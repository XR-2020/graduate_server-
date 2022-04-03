package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.StatisticalMapper;
import com.example.graduate_sever.common.DTO.StatisticalDTO;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("StatisticalService")
public class Statisticalimpl implements StatisticalService {
    @Autowired
    private StatisticalMapper mapper;

    @Override
    public ResVO selectAll(StatisticalDTO dto) {
        return null;
    }

    @Override
    public ResVO SearchDetail(StatisticalDTO dto) {
        return null;
    }
}

package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.StatisticalDTO;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.model.ZheXian;
import com.example.graduate_sever.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Statistical {
    @Autowired
    private StatisticalService service;

    @GetMapping("/SearchAll")
    public List<ZheXian> SearchAll(StatisticalDTO dto){
        return service.selectAll(dto);
    }

    @GetMapping("/SearchDetail")
    public ResVO SearchDetail(StatisticalDTO dto){
        return service.SearchDetail(dto);
    }


}

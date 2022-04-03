package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.StatisticalDTO;
import com.example.graduate_sever.common.ResVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Statistical {
    @Autowired
    private Statistical service;

    @GetMapping("/SearchAll")
    public ResVO SearchAll(StatisticalDTO dto){
        return service.SearchAll(dto);
    }

    @GetMapping("/SearchDetail")
    public ResVO SearchDetail(StatisticalDTO dto){
        return service.SearchDetail(dto);
    }


}

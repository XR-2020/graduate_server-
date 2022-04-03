package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.KeYanLunWenService;
import com.example.graduate_sever.service.ZhuZuoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeYanLunWen {
    @Autowired
    private KeYanLunWenService service;

    @GetMapping("/getAllKeYanLunWen")
    public ResVO getAllKeYanLunWen(DTO dTO) {
        return service.getAllKeYanLunWen(dTO);
    }

    @GetMapping("/getSearchKeYanLunWen")
    public ResVO getSearchKeYanLunWen(DTO dTO) {
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return service.getSearchKeYanLunWen(dTO);
    }

    @PostMapping("/deleteKeYanLunWen")
    public JsonBean deleteKeYanLunWen(int[] ids) {
        return service.deleteKeYanLunWen(ids);
    }

    @PostMapping("/deleteOneKeYanLunWen")
    public JsonBean deleteOneKeYanLunWen(Integer id) {
        return service.deleteOneKeYanLunWen(id);
    }

    @GetMapping("/getKeYanLunWenDetail")
    public JsonBean getKeYanLunWenDetail(Integer id) {
        System.out.println(id);

        return service.getKeYanLunWenDetial(id);
    }
}
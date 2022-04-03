package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.HonorUO;
import com.example.graduate_sever.entity.HonorEntity;
import com.example.graduate_sever.service.HengXiangKeYanService;
import com.example.graduate_sever.service.ZhuanLiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HengXiangKeYan {
    @Autowired
    private HengXiangKeYanService service;

    @GetMapping("/getAllHengXiangKeYan")
    public ResVO getAllHengXiangKeYan(DTO dTO){
        return service.getAllHengXiangKeYan(dTO);
    }

    @GetMapping("/getSearchHengXiangKeYan")
    public ResVO getSearchHengXiangKeYan(DTO dTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return service.getSearchHengXiangKeYan(dTO);}

    @PostMapping("/deleteHengXiangKeYan")
    public JsonBean deleteHengXiangKeYan(int[] ids){return  service.deleteHengXiangKeYan(ids);}

    @PostMapping("/deleteOneHengXiangKeYan")
    public  JsonBean deleteOneHengXiangKeYan(Integer id){return service.deleteOneHengXiangKeYan(id);}

    @GetMapping("/getHengXiangKeYanDetail")
    public JsonBean getHengXiangKeYanDetail(Integer id){
        System.out.println(id);

        return service.getHengXiangKeYanDetial(id);}



}

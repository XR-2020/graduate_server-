package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.HengXiangKeYanService;
import com.example.graduate_sever.service.ZongXiangKeYanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZongXiangKeYan {
    @Autowired
    private ZongXiangKeYanService service;

    @GetMapping("/getAllZongXiangKeYan")
    public ResVO getAllZongXiangKeYan(DTO dTO){
        return service.getAllZongXiangKeYan(dTO);
    }

    @GetMapping("/getSearchZongXiangKeYan")
    public ResVO getSearchZongXiangKeYan(DTO dTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return service.getSearchZongXiangKeYan(dTO);}

    @PostMapping("/deleteZongXiangKeYan")
    public JsonBean deleteZongXiangKeYan(int[] ids){return  service.deleteZongXiangKeYan(ids);}

    @PostMapping("/deleteOneZongXiangKeYan")
    public  JsonBean deleteOneZongXiangKeYan(Integer id){return service.deleteOneZongXiangKeYan(id);}

    @GetMapping("/getZongXiangKeYanDetail")
    public JsonBean getZongXiangKeYanDetail(Integer id){
        System.out.println(id);
        return service.getZongXiangKeYanDetial(id);}

}

package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.HengXiangKeYanService;
import com.example.graduate_sever.service.ZhuZuoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZhuZuo {
    @Autowired
    private ZhuZuoService service;

    @GetMapping("/getAllZhuZuo")
    public ResVO getAllZhuZuo(DTO dTO){
        return service.getAllZhuZuo(dTO);
    }

    @GetMapping("/getSearchZhuZuo")
    public ResVO getSearchZhuZuo(DTO dTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return service.getSearchZhuZuo(dTO);}

    @PostMapping("/deleteZhuZuo")
    public JsonBean deleteZhuZuo(int[] ids){return  service.deleteZhuZuo(ids);}

    @PostMapping("/deleteOneZhuZuo")
    public  JsonBean deleteOneZhuZuo(Integer id){return service.deleteOneZhuZuo(id);}

    @GetMapping("/getZhuZuoDetail")
    public JsonBean getZhuZuoDetail(Integer id){
        System.out.println(id);

        return service.getZhuZuoDetial(id);}

}

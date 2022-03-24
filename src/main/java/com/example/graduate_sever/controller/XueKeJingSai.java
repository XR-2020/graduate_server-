package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.XueKeJingSaiService;
import com.example.graduate_sever.service.ZhuZuoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XueKeJingSai {
    @Autowired
    private XueKeJingSaiService service;

    @GetMapping("/getAllJingSai")
    public ResVO getAllJingSai(DTO dTO){
        return service.getAllJingSai(dTO);
    }

    @GetMapping("/getSearchJingSai")
    public ResVO getSearchJingSai(DTO dTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return service.getSearchJingSai(dTO);}

    @PostMapping("/deleteJingSai")
    public JsonBean deleteJingSai(int[] ids){return  service.deleteJingSai(ids);}

    @PostMapping("/deleteOneJingSai")
    public  JsonBean deleteOneJingSai(Integer id){return service.deleteOneJingSai(id);}

    @GetMapping("/getJingSaiDetail")
    public JsonBean getJingSaiDetail(Integer id){
        System.out.println(id);

        return service.getJingSaiDetial(id);}

}

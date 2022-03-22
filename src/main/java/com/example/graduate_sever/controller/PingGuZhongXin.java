package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.JiaoYanLunWenMuService;
import com.example.graduate_sever.service.PingGuZhongXinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingGuZhongXin {
    @Autowired
    private PingGuZhongXinService pingGuZhongXinService;

    @GetMapping("/getAllPingGuZhongXin")
    public ResVO getAllPingGuZhongXin(DTO jiaoYanXiangMuDTO){
        return pingGuZhongXinService.getAllPingGuZhongXin(jiaoYanXiangMuDTO);
    }

    @GetMapping("/getSearchPingGuZhongXin")
    public ResVO getSearchPingGuZhongXin(DTO jiaoYanXiangMuDTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return pingGuZhongXinService.getSearchPingGuZhongXin(jiaoYanXiangMuDTO);}

    @PostMapping("/deletePingGuZhongXin")
    public JsonBean deletePingGuZhongXin(int[] ids){return  pingGuZhongXinService.deletePingGuZhongXin(ids);}

    @PostMapping("/deleteOnePingGuZhongXin")
    public  JsonBean deleteOnePingGuZhongXin(Integer id){return pingGuZhongXinService.deleteOnePingGuZhongXin(id);}

    @GetMapping("/getPingGuZhongXinDetail")
    public JsonBean getPingGuZhongXinDetail(Integer id){
        System.out.println(id);

        return pingGuZhongXinService.getPingGuZhongXinDetial(id);}

}

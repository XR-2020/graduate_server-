package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.JiaoYanLunWenMuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JiaoYanLunWen {
    @Autowired
    private JiaoYanLunWenMuService jiaoYanLunWenMuService;

    @GetMapping("/getAllJiaoYanLunWen")
    public ResVO getAllJiaoYanLunWen(DTO jiaoYanXiangMuDTO){
        return jiaoYanLunWenMuService.getAllJiaoYanLunWen(jiaoYanXiangMuDTO);
    }

    @GetMapping("/getSearchJiaoYanLunWen")
    public ResVO getSearchJiaoYanLunWen(DTO jiaoYanXiangMuDTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return jiaoYanLunWenMuService.getSearchJiaoYanLunWen(jiaoYanXiangMuDTO);}

    @PostMapping("/deleteJiaoYanLunWen")
    public JsonBean deleteJiaoYanLunWen(int[] ids){return  jiaoYanLunWenMuService.deleteJiaoYanLunWen(ids);}

    @PostMapping("/deleteOneJiaoYanLunWen")
    public  JsonBean deleteOneJiaoYanLunWen(Integer id){return jiaoYanLunWenMuService.deleteOneJiaoYanLunWen(id);}

    @GetMapping("/getJiaoYanLunWenDetail")
    public JsonBean getJiaoYanLunWenDetail(Integer id){
        System.out.println(id);

        return jiaoYanLunWenMuService.getJiaoYanLunWenDetial(id);}

}

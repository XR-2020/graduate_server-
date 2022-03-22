package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.JiaoYuGuiHuaService;
import com.example.graduate_sever.service.ZhuanLiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZhuanLi {
    @Autowired
    private ZhuanLiService zhuanLiService;

    @GetMapping("/getAllZhuanLi")
    public ResVO getAllJiaoYuGuiHua(DTO dTO){
        return zhuanLiService.getAllZhuanLi(dTO);
    }

    @GetMapping("/getSearchZhuanLi")
    public ResVO getSearchJiaoYuGuiHua(DTO dTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return zhuanLiService.getSearchZhuanLi(dTO);}

    @PostMapping("/deleteZhuanLi")
    public JsonBean deleteJiaoYuGuiHua(int[] ids){return  zhuanLiService.deleteZhuanLi(ids);}

    @PostMapping("/deleteOneZhuanLi")
    public  JsonBean deleteOnePingGuZhongXin(Integer id){return zhuanLiService.deleteOneZhuanLi(id);}

    @GetMapping("/getZhuanLiDetail")
    public JsonBean getPingGuZhongXinDetail(Integer id){
        System.out.println(id);

        return zhuanLiService.getZhuanLiDetial(id);}

}

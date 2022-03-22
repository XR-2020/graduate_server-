package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.JiaoYuGuiHuaService;
import com.example.graduate_sever.service.PingGuZhongXinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JiaoYuGuiHua {
    @Autowired
    private JiaoYuGuiHuaService pingGuZhongXinService;

    @GetMapping("/getAllJiaoYuGuiHua")
    public ResVO getAllJiaoYuGuiHua(DTO dTO){
        return pingGuZhongXinService.getAllJiaoYuGuiHua(dTO);
    }

    @GetMapping("/getSearchJiaoYuGuiHua")
    public ResVO getSearchJiaoYuGuiHua(DTO dTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return pingGuZhongXinService.getSearchJiaoYuGuiHua(dTO);}

    @PostMapping("/deleteJiaoYuGuiHua")
    public JsonBean deleteJiaoYuGuiHua(int[] ids){return  pingGuZhongXinService.deleteJiaoYuGuiHua(ids);}

    @PostMapping("/deleteOneJiaoYuGuiHua")
    public  JsonBean deleteOnePingGuZhongXin(Integer id){return pingGuZhongXinService.deleteOneJiaoYuGuiHua(id);}

    @GetMapping("/getJiaoYuGuiHuaDetail")
    public JsonBean getPingGuZhongXinDetail(Integer id){
        System.out.println(id);

        return pingGuZhongXinService.getJiaoYuGuiHuaDetial(id);}

}

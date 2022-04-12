package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.HengXiangKeYanService;
import com.example.graduate_sever.service.RuanJianZhuZuoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RuanJianZhuZuo {
    @Autowired
    private RuanJianZhuZuoService service;

    @GetMapping("/getAllRuanJianZhuZuo")
    public ResVO getAllRuanJianZhuZuo(DTO dTO){
        return service.getAllRuanJianZhuZuo(dTO);
    }

    @GetMapping("/getSearchRuanJianZhuZuo")
    public ResVO getSearchRuanJianZhuZuo(DTO dTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return service.getSearchRuanJianZhuZuo(dTO);}

    @PostMapping("/deleteRuanJianZhuZuo")
    public JsonBean deleteRuanJianZhuZuo(int[] ids){return  service.deleteRuanJianZhuZuo(ids);}

    @PostMapping("/deleteOneRuanJianZhuZuo")
    public  JsonBean deleteOneRuanJianZhuZuo(Integer id){return service.deleteOneRuanJianZhuZuo(id);}

    @GetMapping("/getRuanJianZhuZuoDetail")
    public JsonBean getRuanJianZhuZuoDetail(Integer id){
        System.out.println(id);

        return service.getRuanJianZhuZuoDetial(id);}

    @GetMapping("/getRuanJianZhuZuoMetails")
    public byte[] getRuanJianZhuZuoMetails(Integer id){
        return service.getRuanJianZhuZuoMetails(id).getMetails();
    }

    @RequestMapping(value = "/passRuanJianZhuZuo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int passRuanJianZhuZuo(Integer id,Integer ispass){
        return service.passRuanJianZhuZuo(id,ispass);
    }
}

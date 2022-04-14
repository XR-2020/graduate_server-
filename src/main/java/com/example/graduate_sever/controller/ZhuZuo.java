package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.HengXiangKeYanService;
import com.example.graduate_sever.service.ZhuZuoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getZhuZuoDetailBadge")
    public JsonBean getZhuZuoDetailBadge(Integer id){
        System.out.println(id);

        return new JsonBean(200,"",service.getZhuZuoDetailBadge(id));}

    @GetMapping("/getZhuZuoMetails")
    public byte[] getZhuZuoMetails(Integer id){
        return service.getZhuZuoMetails(id).getMetails();
    }

    @RequestMapping(value = "/passZhuZuo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int passZhuZuo(Integer id,Integer ispass){
        return service.passZhuZuo(id,ispass);
    }
}

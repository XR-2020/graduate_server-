package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.ZongXiangKeYanXiangMuUO;
import com.example.graduate_sever.entity.ZongXiangKeYanXiangMuEntity;
import com.example.graduate_sever.service.HengXiangKeYanService;
import com.example.graduate_sever.service.ZongXiangKeYanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/updateZongXiangKeYan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean updateZongXiangKeYan(@RequestBody ZongXiangKeYanXiangMuUO uo){
        Integer[] people=uo.getPeople();
        ZongXiangKeYanXiangMuEntity element=null;
        JsonBean jsonBean=null;
        int role=uo.getRole();
        if (role!=4&&role!=1){
            element=new ZongXiangKeYanXiangMuEntity(0,uo.getFinishtime(),uo.getLevel(),uo.getType(),uo.getLixiang(),uo.getPartment(),uo.getName(), uo.getShenbao());
        }else {
            element=new ZongXiangKeYanXiangMuEntity(1,uo.getFinishtime(),uo.getLevel(),uo.getType(),uo.getLixiang(),uo.getPartment(),uo.getName(), uo.getShenbao());
        }

        return  service.shenBaoZongXiangKeYan(element,people);
    }
}

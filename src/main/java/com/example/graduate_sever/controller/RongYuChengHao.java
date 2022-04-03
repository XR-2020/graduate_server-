package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.HonorUO;
import com.example.graduate_sever.entity.HonorEntity;
import com.example.graduate_sever.service.RongYuChengHaoService;
import com.example.graduate_sever.service.ZhuanLiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RongYuChengHao {
    @Autowired
    private RongYuChengHaoService service;

    @GetMapping("/getAllRongYu")
    public ResVO getAllRongYu(DTO dTO){
        return service.getAllRongYu(dTO);
    }

    @GetMapping("/getSearchRongYu")
    public ResVO getSearchRongYu(DTO dTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return service.getSearchRongYu(dTO);}

    @PostMapping("/deleteRongYu")
    public JsonBean deleteRongYu(int[] ids){return  service.deleteRongYu(ids);}

    @PostMapping("/deleteOneRongYu")
    public  JsonBean deleteOneRongYu(Integer id){return service.deleteOneRongYu(id);}

//    @GetMapping("/getRongYuDetail")
//    public JsonBean getRongYuDetail(Integer id){
//        System.out.println(id);
//
//        return service.getZhuanLiDetial(id);}

    @RequestMapping(value = "/updateHonor", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean updateHonor(@RequestBody HonorUO uo){
        System.out.println(uo);
        HonorEntity element=null;
        JsonBean jsonBean=null;
        int role=uo.getRole();
        if (role!=4){
            element=new HonorEntity(0,uo.getName(),uo.getLevel(),uo.getFinishtime());
        }else {
            element=new HonorEntity(1,uo.getName(),uo.getLevel(),uo.getFinishtime());
        }

        return  service.insertRongYuChengHao(element);
    }

}

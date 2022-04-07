package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.PingGuZhongXinXiangGuanUO;
import com.example.graduate_sever.entity.PingGuZhongXinXiangGuanEntity;
import com.example.graduate_sever.service.JiaoYanLunWenMuService;
import com.example.graduate_sever.service.PingGuZhongXinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/updatePingGuZhongXin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean updatePingGuZhongXin(@RequestBody PingGuZhongXinXiangGuanUO uo){
        Integer[] people=uo.getPeople();
        PingGuZhongXinXiangGuanEntity element=null;
        JsonBean jsonBean=null;
        int role=uo.getRole();
        if (role!=4&&role!=1){
            element=new PingGuZhongXinXiangGuanEntity(0,uo.getFinishtime(),uo.getGrade(),uo.getPartment(),uo.getName(),people[0]);
        }else {
            element=new PingGuZhongXinXiangGuanEntity(1,uo.getFinishtime(),uo.getGrade(),uo.getPartment(),uo.getName(),people[0]);
        }

        return  pingGuZhongXinService.insertPingGuZhongXin(element,people);
    }

}

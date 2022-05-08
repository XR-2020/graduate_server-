package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.SheKeDTO;
import com.example.graduate_sever.common.EditUO.KeJiChuEditUO;
import com.example.graduate_sever.common.EditUO.SheKeChuEditUO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.KeJiChuUO;
import com.example.graduate_sever.common.UO.NewSystemUO;
import com.example.graduate_sever.common.UO.SheKeChuUO;
import com.example.graduate_sever.service.KeJiChuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KeJiChu {
    @Autowired
    private KeJiChuService keJiChuService;

    @GetMapping("/getAllKeJiChu")
    public ResVO getAllKeJiChu(DTO dTO){
        return keJiChuService.getAllKeJiChu(dTO);
    }

    @GetMapping("/getSearchKeJiChu")
    public ResVO getSearchKeJiChu (DTO dTO){
        return keJiChuService.getSearchKeJiChu(dTO);}

    @RequestMapping(value = "/deleteKeJiChu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean deleteKeJiChu(@RequestParam(value = "ids") List<Integer> ids){
        return  keJiChuService.deleteKeJiChu(ids);}

    @PostMapping("/deleteOneKeJiChu")
    public  JsonBean deleteOneKeJiChu (Integer id,String type){return keJiChuService.deleteOneKeJiChu(id,type);}

    @GetMapping("/getKeJiChuDetail")
    public JsonBean getKeJiChuDetail (Integer id,String type){
        return keJiChuService.getKeJiChuDetail(id,type);}

    @RequestMapping(value = "/insertKeJiChu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean insertKeJiChu (@RequestBody KeJiChuUO uo) throws Exception {
       return  keJiChuService.insertKeJiChu(uo);
    }
    @GetMapping("/getKeJiChuBadge")
    public JsonBean getKeJiChuBadge(Integer id,String type){
        return new JsonBean(200,"",keJiChuService.getKeJiChuBadge(id,type));}

    @RequestMapping(value = "/editKeJiChu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean editKeJiChu (@RequestBody KeJiChuEditUO uo) throws Exception {
        return  new JsonBean(200,"",keJiChuService.editKeJiChu(uo));
    }

    @GetMapping("/getKeJiChuMetails")
    public byte[] getKeJiChuMetails(Integer id){
        return keJiChuService.getKeJiChuMetails(id).getMetails();
    }

    @RequestMapping(value = "/passKeJiChu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int passKeJiChu(Integer id,Integer ispass){
        return keJiChuService.passKeJiChu(id,ispass);
    }

}

package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.SheKeDTO;
import com.example.graduate_sever.common.EditUO.EditChanXueYanUO;
import com.example.graduate_sever.common.EditUO.SheKeChuEditUO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.ChanXueYanUO;
import com.example.graduate_sever.common.UO.SheKeChuUO;
import com.example.graduate_sever.common.WebSiteDateConfig;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.model.Teacher;
import com.example.graduate_sever.service.ChanXueYanService;
import com.example.graduate_sever.service.SheKeChuService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

@RestController
public class SheKeChu {
    @Autowired
    private SheKeChuService sheKeChuService;

    @GetMapping("/getAllSheKeChu")
    public ResVO getAllSheKeChu(SheKeDTO dTO){
        return sheKeChuService.getAllKeYanHuoJiang(dTO);
    }

    @GetMapping("/getSearchSheKeChu")
    public ResVO getSearchSheKeChu(SheKeDTO dTO){
        return sheKeChuService.getSearchKeYanHuoJiang(dTO);}

    @RequestMapping(value = "/deleteKeYanHuoJiang", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean deleteKeYanHuoJiang(@RequestParam(value = "ids") List<Integer> ids,@RequestParam(value = "type") String type){
        return  sheKeChuService.deleteKeYanHuoJiang(ids,type);}

    @PostMapping("/deleteOneKeYanHuoJiang")
    public  JsonBean deleteOneKeYanHuoJiang(Integer id,String type){return sheKeChuService.deleteOneKeYanHuoJiang(id,type);}

    @GetMapping("/getSheKeChuDetail")
    public JsonBean getSheKeChuDetail(Integer id,String type){
        return sheKeChuService.getKeYanHuoJiangDetail(id,type);}

    @RequestMapping(value = "/insertSheKeChu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean insertSheKeChu(@RequestBody SheKeChuUO uo) throws Exception {
       return  sheKeChuService.insertSheKeChu(uo);
    }

//    @GetMapping("/getChanXueYanMetails")
//    public byte[] getChanXueYanMetails(Integer id){
//        return chanXueYanService.getchanxueyanMetails(id).getMetails();
//    }

//    @RequestMapping(value = "/passChanXueYan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public int passChanXueYan(Integer id,Integer ispass){
//        return chanXueYanService.passChanXueYan(id,ispass);
//    }

    @GetMapping("/getSheKeChuDetailBadge")
    public JsonBean getSheKeChuDetailBadge(Integer id,String type){
        return new JsonBean(200,"",sheKeChuService.getKeYanHuoJiangDetailBadge(id,type));}

    @RequestMapping(value = "/editSheKeChu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean editSheKeChu(@RequestBody SheKeChuEditUO uo) throws Exception {
        return  new JsonBean(200,"",sheKeChuService.editSheKeChu(uo));
    }

    @GetMapping("/getSheKeChuMetails")
    public byte[] getSheKeChuMetails(Integer id){
        return sheKeChuService.getSheKeChuMetails(id).getMetails();
    }

    @RequestMapping(value = "/passSheKeChuChu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int passSheKeChuChu(Integer id,Integer ispass){
        return sheKeChuService.passSheKeChuChu(id,ispass);
    }
}

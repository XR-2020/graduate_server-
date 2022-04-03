package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.HonorUO;
import com.example.graduate_sever.common.UO.JiaoYanLunWenUO;
import com.example.graduate_sever.entity.HonorEntity;
import com.example.graduate_sever.entity.JiaoYanLunWenEntity;
import com.example.graduate_sever.service.JiaoYanLunWenMuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class JiaoYanLunWen {
    @Autowired
    private JiaoYanLunWenMuService jiaoYanLunWenMuService;

    @GetMapping("/getAllJiaoYanLunWen")
    public ResVO getAllJiaoYanLunWen(DTO jiaoYanXiangMuDTO) {
        return jiaoYanLunWenMuService.getAllJiaoYanLunWen(jiaoYanXiangMuDTO);
    }

    @GetMapping("/getSearchJiaoYanLunWen")
    public ResVO getSearchJiaoYanLunWen(DTO jiaoYanXiangMuDTO) {
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return jiaoYanLunWenMuService.getSearchJiaoYanLunWen(jiaoYanXiangMuDTO);
    }

    @PostMapping("/deleteJiaoYanLunWen")
    public JsonBean deleteJiaoYanLunWen(int[] ids) {
        return jiaoYanLunWenMuService.deleteJiaoYanLunWen(ids);
    }

    @PostMapping("/deleteOneJiaoYanLunWen")
    public JsonBean deleteOneJiaoYanLunWen(Integer id) {
        return jiaoYanLunWenMuService.deleteOneJiaoYanLunWen(id);
    }

    @GetMapping("/getJiaoYanLunWenDetail")
    public JsonBean getJiaoYanLunWenDetail(Integer id) {
        System.out.println(id);
        return jiaoYanLunWenMuService.getJiaoYanLunWenDetial(id);
    }

    @RequestMapping(value = "/updateJiaoYanLunWen", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean updateJiaoYanLunWen(@RequestBody JiaoYanLunWenUO uo){
        System.out.println(uo);
        JiaoYanLunWenEntity element=null;
        JsonBean jsonBean=null;
        int role=uo.getRole();
        if (role!=2&&role!=4){
            element=new JiaoYanLunWenEntity(0,uo.getFinishtime(),uo.getPartment(),uo.getName());
        }else {
            element=new JiaoYanLunWenEntity(1,uo.getFinishtime(),uo.getPartment(),uo.getName());
        }

        return jiaoYanLunWenMuService.insertJiaoYanLunWen(element);
    }
}
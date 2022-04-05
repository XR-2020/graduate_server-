package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.JiaoYanXiangMuUO;
import com.example.graduate_sever.entity.JiaoYanXiangMuEntity;
import com.example.graduate_sever.service.JiaoYanXiangMuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class JiaoYanXiangMu {
    @Autowired
    private JiaoYanXiangMuService jiaoYanXiangMuService;

    @GetMapping("/getAllJiaoYan")
    public ResVO getAllJiaoYan(DTO jiaoYanXiangMuDTO){
        return jiaoYanXiangMuService.getAllJiaoYan(jiaoYanXiangMuDTO);
    }

    @GetMapping("/getSearchJiaoYan")
    public ResVO getSearchJiaoYan(DTO jiaoYanXiangMuDTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return jiaoYanXiangMuService.getSearchJiaoYan(jiaoYanXiangMuDTO);}

    @PostMapping("/deleteJiaoYan")
    public JsonBean deleteJiaoYan(int[] ids){return  jiaoYanXiangMuService.deleteJiaoYan(ids);}

    @PostMapping("/deleteOneJiaoYan")
    public  JsonBean deleteOneJiaoYan(Integer id){return jiaoYanXiangMuService.deleteOneJiaoYan(id);}

    @GetMapping("/getJiaoYanDetial")
    public JsonBean getJiaoYanDetial(Integer id){
        System.out.println(id);

        return jiaoYanXiangMuService.getJiaoYanDetial(id);}

    @RequestMapping(value = "/updateJiaoYan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean updateJiaoYan(@RequestBody JiaoYanXiangMuUO uo){
        Integer[] people=uo.getPeople();
        JiaoYanXiangMuEntity element=null;
        JsonBean jsonBean=null;
        int role=uo.getRole();
        if (role!=1&&role!=4){
            element=new JiaoYanXiangMuEntity(0,uo.getFinishtime(),uo.getLianghua(),uo.getWenhao(),uo.getPartment(),uo.getName());
        }else {
            element=new JiaoYanXiangMuEntity(1,uo.getFinishtime(),uo.getLianghua(),uo.getWenhao(),uo.getPartment(),uo.getName());}

        return  jiaoYanXiangMuService.insertJiaoYan(element,people);
    }

}

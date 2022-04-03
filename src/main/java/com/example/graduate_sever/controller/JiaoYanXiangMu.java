package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.JiaoYanXiangMuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //    @RequestMapping(value = "/updateHonor", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public JsonBean updateHonor(@RequestBody HonorUO uo){
//        System.out.println(uo);
//        HonorEntity element=null;
//        JsonBean jsonBean=null;
//        int role=uo.getRole();
//        if (role!=4){
//            element=new HonorEntity(0,uo.getName(),uo.getLevel(),uo.getFinishtime());
//        }else {
//            element=new HonorEntity(1,uo.getName(),uo.getLevel(),uo.getFinishtime());
//        }
//
//        return  service.insertRongYuChengHao(element);
//    }

}

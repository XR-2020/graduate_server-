package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.ChanXueYanUO;
import com.example.graduate_sever.common.UO.HeBingUO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HeBing {
    @Autowired
    private ZhuanLiService zhuanLiService;
    @Autowired
    private HengXiangKeYanService hengXiangKeYanService;
    @Autowired
    private ZhuZuoService zhuZuoService;
    @Autowired
    private KeYanLunWenService keYanLunWenService;
    @Autowired
    private RuanJianZhuZuoService ruanJianZhuZuoService;
    @Autowired
    private KeYanXiangMuJieXiangService keYanXiangMuJieXiangService;
    @RequestMapping(value = "/updateHeBing", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean updateHeBing(@RequestBody HeBingUO uo){
        Integer role=uo.getRole();
        HeBingEntity element=null;
        JsonBean jsonBean=null;
        Integer[] people=uo.getPeople();
        if (role!=3&&role!=4) {
            element = new HeBingEntity(0, uo.getFinishtime(), uo.getPartment(), uo.getName());
        }else {
            element = new HeBingEntity(1, uo.getFinishtime(), uo.getPartment(), uo.getName());
        }
        switch (uo.getType()){
           //专利
           case 2:{
               jsonBean=zhuanLiService.insertZhuanLi(element,people);
               break;
           }
           //横向科研项目
           case 3:{
               jsonBean=hengXiangKeYanService.insertHengXiangKeYan(element,people);
               break;
           }
           //著作
           case 4:{
               jsonBean=zhuZuoService.insertZhuZuo(element,people);
               break;
           }
           //科研论文
           case 5:{
               jsonBean=keYanLunWenService.insertKeYanLunWen(element,people);
               break;
           }
           //软件著作权
           case 6:{
               jsonBean=ruanJianZhuZuoService.insertRuanJianZhuZuo(element,people);
               break;
           }
           //科研项目结项
           case 7:{
               jsonBean=keYanLunWenService.insertKeYanLunWen(element,people);
               break;
           }
       }
        return jsonBean;
    }


}

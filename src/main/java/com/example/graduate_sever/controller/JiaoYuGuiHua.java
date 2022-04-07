package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.JiaoYuGuiHuaXiangMuUO;
import com.example.graduate_sever.entity.JiaoYuGuiHuaXiangMuEntity;
import com.example.graduate_sever.service.JiaoYuGuiHuaService;
import com.example.graduate_sever.service.PingGuZhongXinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class JiaoYuGuiHua {
    @Autowired
    private JiaoYuGuiHuaService jiaoYuGuiHuaService;

    @GetMapping("/getAllJiaoYuGuiHua")
    public ResVO getAllJiaoYuGuiHua(DTO dTO){
        return jiaoYuGuiHuaService.getAllJiaoYuGuiHua(dTO);
    }

    @GetMapping("/getSearchJiaoYuGuiHua")
    public ResVO getSearchJiaoYuGuiHua(DTO dTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return jiaoYuGuiHuaService.getSearchJiaoYuGuiHua(dTO);}

    @PostMapping("/deleteJiaoYuGuiHua")
    public JsonBean deleteJiaoYuGuiHua(int[] ids){return  jiaoYuGuiHuaService.deleteJiaoYuGuiHua(ids);}

    @PostMapping("/deleteOneJiaoYuGuiHua")
    public  JsonBean deleteOnePingGuZhongXin(Integer id){return jiaoYuGuiHuaService.deleteOneJiaoYuGuiHua(id);}

    @GetMapping("/getJiaoYuGuiHuaDetail")
    public JsonBean getJiaoYuGuiHuaDetail(Integer id){
        System.out.println(id);

        return jiaoYuGuiHuaService.getJiaoYuGuiHuaDetial(id);}

    @RequestMapping(value = "/updateJiaoYuGuiHua", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean updateJiaoYuGuiHua(@RequestBody JiaoYuGuiHuaXiangMuUO uo){
        Integer[] people=uo.getPeople();
        JiaoYuGuiHuaXiangMuEntity element=null;
        JsonBean jsonBean=null;
        int role=uo.getRole();
        if (role!=4){
            element=new JiaoYuGuiHuaXiangMuEntity(0,uo.getFinishtime(),uo.getPartment(),uo.getName(),uo.getGrade(),uo.getLevel(),uo.getDanwei());
        }else {
            element=new JiaoYuGuiHuaXiangMuEntity(1,uo.getFinishtime(),uo.getPartment(),uo.getName(),uo.getGrade(),uo.getLevel(),uo.getDanwei());
        }

        return  jiaoYuGuiHuaService.insertJiaoYuGuiHua(element,people);
    }

}

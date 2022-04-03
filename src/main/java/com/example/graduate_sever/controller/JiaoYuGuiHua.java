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
    private JiaoYuGuiHuaService pingGuZhongXinService;

    @GetMapping("/getAllJiaoYuGuiHua")
    public ResVO getAllJiaoYuGuiHua(DTO dTO){
        return pingGuZhongXinService.getAllJiaoYuGuiHua(dTO);
    }

    @GetMapping("/getSearchJiaoYuGuiHua")
    public ResVO getSearchJiaoYuGuiHua(DTO dTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return pingGuZhongXinService.getSearchJiaoYuGuiHua(dTO);}

    @PostMapping("/deleteJiaoYuGuiHua")
    public JsonBean deleteJiaoYuGuiHua(int[] ids){return  pingGuZhongXinService.deleteJiaoYuGuiHua(ids);}

    @PostMapping("/deleteOneJiaoYuGuiHua")
    public  JsonBean deleteOnePingGuZhongXin(Integer id){return pingGuZhongXinService.deleteOneJiaoYuGuiHua(id);}

    @GetMapping("/getJiaoYuGuiHuaDetail")
    public JsonBean getPingGuZhongXinDetail(Integer id){
        System.out.println(id);

        return pingGuZhongXinService.getJiaoYuGuiHuaDetial(id);}

    @RequestMapping(value = "/updateJiaoYuGuiHua", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean updateJiaoYuGuiHua(@RequestBody JiaoYuGuiHuaXiangMuUO uo){
        System.out.println(uo);
        JiaoYuGuiHuaXiangMuEntity element=null;
        JsonBean jsonBean=null;
        int role=uo.getRole();
        if (role!=4){
            element=new JiaoYuGuiHuaXiangMuEntity(0,uo.getFinishtime(),uo.getPartment(),uo.getName(),uo.getGrade(),uo.getLevel(),uo.getDanwei());
        }else {
            element=new JiaoYuGuiHuaXiangMuEntity(1,uo.getFinishtime(),uo.getPartment(),uo.getName(),uo.getGrade(),uo.getLevel(),uo.getDanwei());
        }

        return  pingGuZhongXinService.insertJiaoYuGuiHua(element);
    }

}

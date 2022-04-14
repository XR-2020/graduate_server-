package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.KeYanXiangMuJieXiangService;
import com.example.graduate_sever.service.ZhuanLiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class KeYanXiangMuJieXiang {
    @Autowired
    private KeYanXiangMuJieXiangService service;

    @GetMapping("/getAllKeYanXiangMuJieXiang")
    public ResVO getAllKeYanXiangMuJieXiang(DTO dTO){
        return service.getAllKeYanXiangMuJieXiang(dTO);
    }

    @GetMapping("/getSearchKeYanXiangMuJieXiang")
    public ResVO getSearchKeYanXiangMuJieXiang(DTO dTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return service.getSearchKeYanXiangMuJieXiang(dTO);}

    @PostMapping("/deleteKeYanXiangMuJieXiang")
    public JsonBean deleteKeYanXiangMuJieXiang(int[] ids){return  service.deleteKeYanXiangMuJieXiang(ids);}

    @PostMapping("/deleteOneKeYanXiangMuJieXiang")
    public  JsonBean deleteOneKeYanXiangMuJieXiang(Integer id){return service.deleteOneKeYanXiangMuJieXiang(id);}

    @GetMapping("/getKeYanXiangMuJieXiangDetail")
    public JsonBean getKeYanXiangMuJieXiangDetail(Integer id){
        System.out.println(id);

        return service.getKeYanXiangMuJieXiangDetial(id);}

    @GetMapping("/getKeYanXiangMuJieXiangDetailBadge")
    public JsonBean getKeYanXiangMuJieXiangDetailBadge(Integer id){
        System.out.println(id);

        return new JsonBean(200,"",service.getKeYanXiangMuJieXiangDetailBadge(id));}

    @GetMapping("/getKeYanXiangMuJieXiangMetails")
    public byte[] getKeYanXiangMuJieXiangMetails(Integer id){
        return service.getKeYanXiangMuJieXiangMetails(id).getMetails();
    }

    @RequestMapping(value = "/passKeYanXiangMuJieXiang", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int passKeYanXiangMuJieXiang(Integer id,Integer ispass){
        return service.passKeYanXiangMuJieXiang(id,ispass);
    }
}

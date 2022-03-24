package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.ChanXueYanUO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.service.ChanXueYanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChanXueYan {
    @Autowired
    private ChanXueYanService chanXueYanService;

    @GetMapping("/getAllChanXueYan")
    public ResVO getAllChanXueYan(DTO chanXueYanDTO){
        return chanXueYanService.selectAll(chanXueYanDTO);
    }

    @GetMapping("/getSearchChanXueYan")
    public ResVO getSearchChanXueYan(DTO chanXueYanDTO){
        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return chanXueYanService.getSearchChanXueYan(chanXueYanDTO);}

    @PostMapping("/deleteChanXueYan")
    public JsonBean deleteChanXueYan(int[] ids){return  chanXueYanService.deleteChanXueYan(ids);}

    @PostMapping("/deleteOneChanXueYan")
    public  JsonBean deleteOneChanXueYan(Integer id){return chanXueYanService.deleteOneChanXueYan(id);}

    @GetMapping("/getChanXueYanDetail")
    public JsonBean getChanXueYanDetail(Integer id){
        System.out.println(id);
        return chanXueYanService.getChanXueYanDetail(id);}
    @RequestMapping(value = "/insertChanXueYan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean updateChanXueYan(@RequestBody ChanXueYanUO uo){
        System.out.println(uo);
        ChanXueYanEntity element=new ChanXueYanEntity();
        element.setBadge(uo.getFirstpeople());
        element.setFinishtime(uo.getFinishtime());
        element.setName(uo.getName());
        element.setPartment(uo.getPartment());
        element.setLianghua(uo.getLianghua());
        element.setWenhao(uo.getWenhao());
        return  chanXueYanService.insertChanXueYan(element);
    }

}

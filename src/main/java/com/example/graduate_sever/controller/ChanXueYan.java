package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.ChanXueYanDTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.ChanXueYanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChanXueYan {
    @Autowired
    private ChanXueYanService chanXueYanService;

    @GetMapping("/getAllChanXueYan")
    public ResVO getAllChanXueYan(ChanXueYanDTO chanXueYanDTO){
        return chanXueYanService.selectAll(chanXueYanDTO);
    }

    @GetMapping("/getSearchChanXueYan")
    public ResVO getSearchChanXueYan(ChanXueYanDTO chanXueYanDTO){
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

}

package com.example.graduate_sever.controller;


import com.example.graduate_sever.GraduateSeverApplication;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.ChanXueYanUO;
import com.example.graduate_sever.common.WebCookie;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.model.Teacher;
import com.example.graduate_sever.service.ChanXueYanService;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public JsonBean insertChanXueYan(@RequestBody ChanXueYanUO uo){
        ChanXueYanEntity element=new ChanXueYanEntity();
        Integer[] people=uo.getPeople();
        Integer role=uo.getRole();
        element.setFinishtime(uo.getFinishtime());
        element.setName(uo.getName());
        element.setPartment(uo.getPartment());
        element.setLianghua(uo.getLianghua());
        element.setWenhao(uo.getWenhao());
        if (role!=4&&role!=1){
            element.setStatus(0);
        }else {
            element.setStatus(1);
        }
        element.setBadge(uo.getShenbao());
        return  chanXueYanService.shenBaoChanXueYan(element,people);
    }
    @GetMapping("/getTeacherList")
    public List<Object> getTeacherList(){
//        System.out.println(WebCookie.getCookie());
        return chanXueYanService.getTeacherList();
    }

}

package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.ChanXueYanUO;
import com.example.graduate_sever.common.UO.CompetitionUO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.CompetitionEntity;
import com.example.graduate_sever.service.XueKeJingSaiService;
import com.example.graduate_sever.service.ZhuZuoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class XueKeJingSai {
    @Autowired
    private XueKeJingSaiService service;

    @GetMapping("/getAllJingSai")
    public ResVO getAllJingSai(DTO dTO){
        return service.getAllJingSai(dTO);
    }

    @GetMapping("/getSearchJingSai")
    public ResVO getSearchJingSai(DTO dTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return service.getSearchJingSai(dTO);}

    @PostMapping("/deleteJingSai")
    public JsonBean deleteJingSai(int[] ids){return  service.deleteJingSai(ids);}

    @PostMapping("/deleteOneJingSai")
    public  JsonBean deleteOneJingSai(Integer id){return service.deleteOneJingSai(id);}

    @GetMapping("/getJingSaiDetail")
    public JsonBean getJingSaiDetail(Integer id){
        System.out.println(id);

        return service.getJingSaiDetial(id);}

    @RequestMapping(value = "/updateCompetition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean updateCompetition(@RequestBody CompetitionUO uo){
        Integer[] people=uo.getPeople();
        CompetitionEntity element=new CompetitionEntity();
        element.setFinishtime(uo.getFinishtime());
        element.setGrade(uo.getGrade());
        element.setLevel(uo.getLevel());
        element.setName(uo.getName());
        element.setStudent(uo.getStudent());
        if(uo.getRole() ==3){
            element.setStatus(1);
        }
        element.setBadge(uo.getShenbao());
        return service.insertJingSai(element,people);
    }

}

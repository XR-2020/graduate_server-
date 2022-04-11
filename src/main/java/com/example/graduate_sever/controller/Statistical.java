package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.StatisticalDTO;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.StatisticalRes;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.model.ZheXian;
import com.example.graduate_sever.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Statistical {
    @Autowired
    private StatisticalService service;

    @GetMapping("/SearchAll")
    public StatisticalRes SearchAll(StatisticalDTO dto){
        String date1= dto.getDate1();
        String date2=dto.getDate2();
        StatisticalRes allData=new StatisticalRes(service.selectAll(dto),
                service.selectChanXueYan(date1,date2),
                service.hengxiangkeyan(date1,date2),
                service.jiaoyanlunwen(date1,date2),
                service.jiaoyanxiangmu(date1,date2),
                service.jiaoyuguihua(date1,date2),
                service.keyanlunwen(date1,date2),
                service.keyanxiangmujiexiang(date1,date2),
                service.pingguzhongxin(date1,date2),
                service.rongyuchenghao(date1,date2),
                service.ruanjianzhuzuo(date1,date2),
                service.xuekejingsai(date1,date2),
                service.zhuanli(date1,date2),
                service.zhuzuo(date1,date2),
                service.zongxiangkeyan(date1,date2));
        return allData;
    }

    @GetMapping("/SearchByDetail")
    public List<TableData> SearchByDetail(StatisticalDTO dto){
        String date1= dto.getDate1();
        String date2=dto.getDate2();
        String value=dto.getValue();
        List<TableData> tableData=null;
        switch (value){
            case "1":{
                tableData=service.selectChanXueYan(date1,date2);
                break;
            }
            case "2":{
                tableData=service.jiaoyanxiangmu(date1,date2);
                break;
            }
            case "3":{
                tableData=service.jiaoyanlunwen(date1,date2);
                break;
            }
            case "4":{
                tableData=service.jiaoyuguihua(date1,date2);
                break;
            }
            case "5":{
                tableData=service.zongxiangkeyan(date1,date2);
                break;
            }
            case "6":{
                tableData=service.pingguzhongxin(date1,date2);
                break;
            }
            case "7":{
                tableData=service.zhuanli(date1,date2);
                break;
            }
            case "8":{
                tableData=service.hengxiangkeyan(date1,date2);
                break;
            }
            case "9":{
                tableData=service.zhuzuo(date1,date2);
                break;
            }
            case "10":{
                tableData=service.keyanlunwen(date1,date2);
                break;
            }
            case "11":{
                tableData=service.ruanjianzhuzuo(date1,date2);
                break;
            }
            case "12":{
                tableData=service.keyanxiangmujiexiang(date1,date2);
                break;
            }
            case "13":{
                tableData=service.xuekejingsai(date1,date2);
                break;
            }
            case "14":{
                tableData=service.rongyuchenghao(date1,date2);
                break;
            }
        }
        return tableData;
    }


}

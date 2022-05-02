package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.StatisticalDTO;
import com.example.graduate_sever.common.NewStatisticalRes;
import com.example.graduate_sever.common.StatisticalRes;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.service.NewStatisticalService;
import com.example.graduate_sever.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewStatistical {
    @Autowired
    private NewStatisticalService service;

    @GetMapping("/NewSearchAll")
    public NewStatisticalRes NewSearchAll(StatisticalDTO dto){
        String date1= dto.getDate1();
        String date2=dto.getDate2();

        NewStatisticalRes allData=new NewStatisticalRes(
                service.selectxiaowaishijianjidi(date1,date2),
                service.selectshijianlixiang(date1,date2),
                service.selectshijianjiexiang(date1,date2),
                service.selectjiaocaiyejidian(date1,date2),
                service.selectjiaoyanyeji(date1,date2),
                service.selectjiaoyanlunwen(date1,date2),
                service.selectyouxiubishe(date1,date2),
                service.selectzongxiangjieti(date1,date2),
                service.selectkeyanhuojiang(date1,date2)
        );
        return allData;
    }

    @GetMapping("/NewSearchByDetail")
    public List<TableData> NewSearchByDetail(StatisticalDTO dto){
        String date1= dto.getDate1();
        String date2=dto.getDate2();
        String value=dto.getValue();
        List<TableData> tableData=null;
        switch (value){
            case "教务处-实践科_校外实践基地":{
                tableData=service.selectxiaowaishijianjidi(date1,date2);
                break;
            }
            case "教务处-实践科_立项":{
                tableData=service.selectshijianlixiang(date1,date2);
                break;
            }
            case "教务处-实践科_结项":{
                tableData=service.selectshijianjiexiang(date1,date2);
                break;
            }
            case "教务处-教材科_教材业绩点":{
                tableData=service.selectjiaocaiyejidian(date1,date2);
                break;
            }
            case "教务处-教研科_教研业绩":{
                tableData=service.selectjiaoyanyeji(date1,date2);
                break;
            }
            case "教务处-教研科_教研论文":{
                tableData=service.selectjiaoyanlunwen(date1,date2);
                break;
            }
            case "教务处-评估中心_2020届本科优秀毕业设计（论文）指导教师":{
                tableData=service.selectyouxiubishe(date1,date2);
                break;
            }
            case "社科处_3.纵向结题":{
                tableData=service.selectzongxiangjieti(date1,date2);
                break;
            }
            case "社科处_7.科研获奖":{
                tableData=service.selectkeyanhuojiang(date1,date2);
                break;
            }
        }
        return tableData;
    }


}

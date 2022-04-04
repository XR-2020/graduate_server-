package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.StatisticalMapper;
import com.example.graduate_sever.common.DTO.StatisticalDTO;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.ZheXianEntity;
import com.example.graduate_sever.model.ZheXian;
import com.example.graduate_sever.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("StatisticalService")
public class Statisticalimpl implements StatisticalService {
    @Autowired
    private StatisticalMapper mapper;

    @Override
    public List<ZheXian> selectAll(StatisticalDTO dto) {
        ZheXianEntity zheXian=null;
        ZheXian element=null;
        List<ZheXian> data=new ArrayList<>();
        String date1= dto.getDate1();
        String date2=dto.getDate2();
        long value;
        HashMap<String,String> arr=new HashMap<String,String>();
        arr.put("chanxueyan","产学研");
        arr.put("competition","学科竞赛");
        arr.put("hengxiangkeyanxiangmu","横向科研项目");
        arr.put("honor","荣誉称号");
        arr.put("jiaoyanlunwen","教研论文");
        arr.put("jiaoyanxiangmu","教研项目");
        arr.put("jiaoyuguihuaxiangmu","教育规划项目");
        arr.put("keyanlunwen","科研论文");
        arr.put("keyanxiangmujiexiang","科研项目结项");
        arr.put("pingguzhongxinxiangguan","评估中心相关");
        arr.put("ruanjianzhuzuoquan","软件著作权");
        arr.put("zhuanli","专利");
        arr.put("zhuzuo","著作");
        arr.put("zongxiangkeyanxiangmu","纵向科研项目");
        for (Map.Entry<String,String> str:arr.entrySet()) {
            zheXian=new ZheXianEntity(str.getKey(), date1,date2);
            value=mapper.SearchAll(zheXian);
            element=new ZheXian(str.getValue(),value);
            data.add(element);
        }
        return data;
    }

    @Override
    public ResVO SearchDetail(StatisticalDTO dto) {
        return null;
    }
}

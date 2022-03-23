package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.HengXiangKeYanMapper;
import com.example.graduate_sever.Dao.ZhuanLiMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.HengXiangKeYanService;
import com.example.graduate_sever.service.ZhuanLiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("HengXiangKeYanService")
public class HengXiangKeYanimpl implements HengXiangKeYanService {
    @Autowired
    private HengXiangKeYanMapper mapper;
    @Override
    public ResVO getAllHengXiangKeYan(DTO dTO) {
        List<List<Object>>data=mapper.getAllHengXiangKeYan(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchHengXiangKeYan(DTO dTO) {
        List<List<Object>>data=mapper.getSearchHengXiangKeYan(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteHengXiangKeYan(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneHengXiangKeYan(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneHengXiangKeYan(Integer id) {
        mapper.deleteOneHengXiangKeYan(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getHengXiangKeYanDetial(Integer id) {
        return new JsonBean(200,"",mapper.getHengXiangKeYanDetail(id));
    }
}

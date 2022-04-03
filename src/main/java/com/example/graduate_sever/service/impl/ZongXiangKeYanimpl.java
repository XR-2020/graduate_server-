package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.HengXiangKeYanMapper;
import com.example.graduate_sever.Dao.ZongXiangKeYanMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.ZongXiangKeYanXiangMuEntity;
import com.example.graduate_sever.service.HengXiangKeYanService;
import com.example.graduate_sever.service.ZongXiangKeYanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ZongXiangKeYanService")
public class ZongXiangKeYanimpl implements ZongXiangKeYanService {
    @Autowired
    private ZongXiangKeYanMapper mapper;
    @Override
    public ResVO getAllZongXiangKeYan(DTO dTO) {
        List<List<Object>>data=mapper.getAllZongXiangKeYan(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchZongXiangKeYan(DTO dTO) {
        List<List<Object>>data=mapper.getSearchZongXiangKeYan(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteZongXiangKeYan(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneZongXiangKeYan(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneZongXiangKeYan(Integer id) {
        mapper.deleteOneZongXiangKeYan(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getZongXiangKeYanDetial(Integer id) {
        return new JsonBean(200,"",mapper.getZongXiangKeYanDetail(id));
    }

    @Override
    public JsonBean insertZongXiangKeYan(ZongXiangKeYanXiangMuEntity entity) {
        return new JsonBean(200,"",mapper.insertZongXiangKeYan(entity));
    }
}

package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.KeYanLunWenMapper;
import com.example.graduate_sever.Dao.ZhuZuoMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.service.KeYanLunWenService;
import com.example.graduate_sever.service.ZhuZuoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("KeYanLunWenService")
public class KeYanLunWenimpl implements KeYanLunWenService {
    @Autowired
    private KeYanLunWenMapper mapper;
    @Override
    public ResVO getAllKeYanLunWen(DTO dTO) {
        List<List<Object>>data=mapper.getAllKeYanLunWen(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchKeYanLunWen(DTO dTO) {
        List<List<Object>>data=mapper.getSearchKeYanLunWen(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteKeYanLunWen(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneKeYanLunWen(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneKeYanLunWen(Integer id) {
        mapper.deleteOneKeYanLunWen(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getKeYanLunWenDetial(Integer id) {
        return new JsonBean(200,"",mapper.getKeYanLunWenDetail(id));
    }

    @Override
    public JsonBean insertKeYanLunWen(HeBingEntity entity) {
        return new JsonBean(200,"",mapper.insertKeYanLunWen(entity));
    }
}

package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.KeYanXiangMuJieXiangMapper;
import com.example.graduate_sever.Dao.ZhuZuoMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.service.KeYanXiangMuJieXiangService;
import com.example.graduate_sever.service.ZhuZuoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("KeYanXiangMuJieXiangService")
public class KeYanXiangMuJieXiangimpl implements KeYanXiangMuJieXiangService {
    @Autowired
    private KeYanXiangMuJieXiangMapper mapper;
    @Override
    public ResVO getAllKeYanXiangMuJieXiang(DTO dTO) {
        List<List<Object>>data=mapper.getAllKeYanXiangMuJieXiang(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchKeYanXiangMuJieXiang(DTO dTO) {
        List<List<Object>>data=mapper.getSearchKeYanXiangMuJieXiang(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteKeYanXiangMuJieXiang(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneKeYanXiangMuJieXiang(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneKeYanXiangMuJieXiang(Integer id) {
        mapper.deleteOneKeYanXiangMuJieXiang(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getKeYanXiangMuJieXiangDetial(Integer id) {
        return new JsonBean(200,"",mapper.getKeYanXiangMuJieXiangDetail(id));
    }

    @Override
    public JsonBean insertKeYanXiangMuJieXiang(HeBingEntity entity,Integer[] people) {
        mapper.insertKeYanXiangMuJieXiang(entity);
        for (Integer ach_id:people) {
            mapper.insertKeYanXiangMuJieXiangParticipation(new ParticipationEntity(ach_id,entity.getId(),12));
        }
        return new JsonBean(200,"","");
    }
}

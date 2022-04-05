package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.JiaoYanXiangMuMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.JiaoYanXiangMuEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.service.JiaoYanXiangMuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("JiaoYanXiangMuService")
public class JiaoYanXiangMuimpl implements JiaoYanXiangMuService {
    @Autowired
    private JiaoYanXiangMuMapper mapper;
    @Override
    public ResVO getAllJiaoYan(DTO dTO) {
        List<List<Object>>data=mapper.getAllJiaoYan(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchJiaoYan(DTO dTO) {
        List<List<Object>>data=mapper.getSearchJiaoYan(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteJiaoYan(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneJiaoYan(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneJiaoYan(Integer id) {
        mapper.deleteOneJiaoYan(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getJiaoYanDetial(Integer id) {
        return new JsonBean(200,"",mapper.getJiaoYanDetail(id));
    }

    @Override
    public JsonBean insertJiaoYan(JiaoYanXiangMuEntity entity,Integer[] people) {
        mapper.insertJiaoYanXiangMu(entity);
        for (Integer ach_id:people) {
            mapper.insertJiaoYanXiangMuParticipation(new ParticipationEntity(ach_id,entity.getId(),2));
        }
        return new JsonBean(200,"","");
    }
}

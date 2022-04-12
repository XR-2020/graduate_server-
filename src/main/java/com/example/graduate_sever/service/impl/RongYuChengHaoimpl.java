package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.RongYuChengHaoMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.HonorEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.Honor;
import com.example.graduate_sever.service.RongYuChengHaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("RongYuChengHaoService")
public class RongYuChengHaoimpl implements RongYuChengHaoService {
    @Autowired
    private RongYuChengHaoMapper mapper;
    @Override
    public ResVO getAllRongYu(DTO dTO) {
        List<List<Object>>data=mapper.getAllRongYu(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchRongYu(DTO dTO) {
        List<List<Object>>data=mapper.getSearchRongYu(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteRongYu(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneRongYu(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneRongYu(Integer id) {
        mapper.deleteOneRongYu(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean insertRongYuChengHao(HonorEntity entity,Integer[] people) {
        int ref=mapper.insertRongYuChengHao(entity);
        if(ref!=0){
            for (Integer ach_id:people) {
                mapper.insertRongYuChengHaoParticipation(new ParticipationEntity(ach_id,entity.getId(),14));
            }
        }
        return new JsonBean(200,"",ref);
    }

    @Override
    public JsonBean shenbaoRongYuChengHao(HonorEntity entity, Integer[] people) {
        int ref=mapper.shenBaoRongYuChengHao(entity);
        if(ref!=0){
            for (Integer ach_id:people) {
                mapper.insertRongYuChengHaoParticipation(new ParticipationEntity(ach_id,entity.getId(),14));
            }
        }
        return new JsonBean(200,"",ref);
    }

    @Override
    public Metails getRongYuChengHaoMetails(Integer id) {
        return mapper.RongYuChengHaoMetails(id);
    }

    @Override
    public List<TableData> waitingrongyuchenghao(DTO dTO) {
        List<Honor> list=mapper.waitingrongyuchenghao(dTO);
        List<TableData> tableData=new ArrayList<>();
        for (Honor c:list) {
            tableData.add(new TableData(c,mapper.getRongYuDetail(c.getId())));
        }
        return tableData;
    }

//    @Override
//    public JsonBean getRongYuDetial(Integer id) {
//        return new JsonBean(200,"",mapper.getZhuZuoDetail(id));
//    }
}

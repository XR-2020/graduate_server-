package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.ChanXueYanMapper;
import com.example.graduate_sever.Dao.RongYuChengHaoMapper;
import com.example.graduate_sever.common.*;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.entity.HonorEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.Honor;
import com.example.graduate_sever.model.MyShenBaoModel;
import com.example.graduate_sever.service.RongYuChengHaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("RongYuChengHaoService")
public class RongYuChengHaoimpl implements RongYuChengHaoService {
    @Autowired
    private RongYuChengHaoMapper mapper;
    @Autowired
    private ChanXueYanMapper chanXueYanMapper;
    @Override
    public ResVO getAllRongYu(DTO dTO) {
        List<Honor> list=mapper.selectAllRongYuChengHao(dTO);
        List<TableData> tableData=new ArrayList<>();
        for (Honor c:list) {
            tableData.add(new TableData(c,mapper.getRongYuDetail(c.getId())));
        }
        return new ResVO(tableData, mapper.selectAllRongYuChengHaoPageTotal());
    }

    @Override
    public ResVO getSearchRongYu(DTO dTO) {
        List<Honor> list=mapper.getSearchRongYu(dTO);
        List<TableData> tableData=new ArrayList<>();
        for (Honor c:list) {
            tableData.add(new TableData(c,mapper.getRongYuDetail(c.getId())));
        }
        return new ResVO(tableData, mapper.getSearchRongYuPageTotal());
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
    public JsonBean shenbaoRongYuChengHao(HonorEntity entity) {
        int ref=mapper.shenBaoRongYuChengHao(entity);
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

    @Override
    public int passRongYuChengHao(Integer id, Integer ispass) {
        return mapper.passRongYuChengHao(id,ispass);
    }

    @Override
    public List<TableData> getDisData(MyShenBaoDTO dto) {
        List<MyShenBaoModel> list=mapper.getRongYuChengHaoDisData(dto);
        for (MyShenBaoModel b:list) {
            System.out.println(b.getId());
        }
        List<TableData> tableData=new ArrayList<>();
        for (MyShenBaoModel c:list) {
            tableData.add(new TableData(c,mapper.getRongYuDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<Integer> getHonorBadge(Integer id) {
        List<People> people=mapper.getRongYuDetail(id);
        List<Integer> badges=new ArrayList<>();
        for (People p:people) {
            badges.add(p.getBadge());
        }
        return badges;
    }

    @Override
    public int editHonor(Integer id, String name, String partment, String finishtime, String teacher,String level) {
        int ref=mapper.editHonor(id,name,finishtime,partment,teacher,level);
//        if(ref==1){
//            chanXueYanMapper.deletePeople(id,14);
//            for (Integer p:people) {
//                chanXueYanMapper.editPeople(id,p,14);
//            }
//        }
        return ref;
    }

//    @Override
//    public JsonBean getRongYuDetial(Integer id) {
//        return new JsonBean(200,"",mapper.getZhuZuoDetail(id));
//    }
}

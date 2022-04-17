package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.ChanXueYanMapper;
import com.example.graduate_sever.Dao.XueKeJingSaiMapper;
import com.example.graduate_sever.common.*;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.entity.CompetitionEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.Competition;
import com.example.graduate_sever.model.Honor;
import com.example.graduate_sever.model.MyShenBaoModel;
import com.example.graduate_sever.service.XueKeJingSaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("XueKeJingSaiService")
public class XueKeJingSaiimpl implements XueKeJingSaiService {
    @Autowired
    private XueKeJingSaiMapper mapper;
    @Autowired
    private ChanXueYanMapper chanXueYanMapper;
    @Override
    public ResVO getAllJingSai(DTO dTO) {
        List<Competition> list=mapper.getAllJingSai(dTO);
        List<TableData> tableData=new ArrayList<>();
        for (Competition c:list) {
            tableData.add(new TableData(c,mapper.getJingSaiDetail(c.getId())));
        }
        return new ResVO(tableData, mapper.getAllJingSaiPageTotal());
    }

    @Override
    public ResVO getSearchJingSai(DTO dTO) {
        List<Competition> list=mapper.getSearchJingSai(dTO);
        List<TableData> tableData=new ArrayList<>();
        for (Competition c:list) {
            tableData.add(new TableData(c,mapper.getJingSaiDetail(c.getId())));
        }
        return new ResVO(tableData, mapper.getSearchJingSaiPageTotal());
    }

    @Override
    public JsonBean deleteJingSai(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneJingSai(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneJingSai(Integer id) {
        mapper.deleteOneJingSai(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getJingSaiDetial(Integer id) {
        return new JsonBean(200,"",mapper.getJingSaiDetail(id));
    }

    @Override
    public JsonBean insertJingSai(CompetitionEntity entity) {
        return new JsonBean(200,"",  mapper.insertJingSai(entity));
    }

    @Override
    public JsonBean shenBaoJingSai(CompetitionEntity entity, Integer[] people) {
        int ref=mapper.shenBaoJingSai(entity);
        if(ref!=0){
            for (Integer ach_id:people) {
                mapper.insertJingSaiParticipation(new ParticipationEntity(ach_id,entity.getId(),13));
            }
        }
        return new JsonBean(200,"",ref);
    }

    @Override
    public Metails getXueKeJingSaiMetails(Integer id) {
        return mapper.XueKeJingSaiMetails(id);
    }

    @Override
    public List<TableData> waitingxuekejingsai(DTO dTO) {
        List<Competition> list=mapper.waitingxuekejingsai(dTO);
        List<TableData> tableData=new ArrayList<>();
        for (Competition c:list) {
            tableData.add(new TableData(c,mapper.getJingSaiDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public int passXueKeJingSai(Integer id, Integer ispass) {
        return mapper.passXueKeJingSai(id,ispass);
    }

    @Override
    public List<TableData> getDisData(MyShenBaoDTO dto) {
        List<MyShenBaoModel> list=mapper.getXueKeJingSaiDisData(dto);
        for (MyShenBaoModel b:list) {
            System.out.println(b.getId());
        }
        List<TableData> tableData=new ArrayList<>();
        for (MyShenBaoModel c:list) {
            tableData.add(new TableData(c,mapper.getJingSaiDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<Integer> getJingSaiDetailBadge(Integer id) {
        List<People> people=mapper.getJingSaiDetail(id);
        List<Integer> badges=new ArrayList<>();
        for (People p:people) {
            badges.add(p.getBadge());
        }
        return badges;
    }

    @Override
    public List<Integer> getComputitionBadge(Integer id) {
        List<People> people=mapper.getJingSaiDetail(id);
        List<Integer> badges=new ArrayList<>();
        for (People p:people) {
            badges.add(p.getBadge());
        }
        return badges;
    }

    @Override
    public int editCompetition(Integer id, String name, String partment, String finishtime, String teacher, String grade, String student, String level) {
        int ref=mapper.editCompetition(id,name,finishtime,grade,student,level,teacher,partment);
//        if(ref==1){
//            chanXueYanMapper.deletePeople(id,13);
//            for (Integer p:people) {
//                chanXueYanMapper.editPeople(id,p,13);
//            }
//        }
        return ref;
    }
}

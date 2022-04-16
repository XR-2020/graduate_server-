package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.controller.XueKeJingSai;
import com.example.graduate_sever.entity.CompetitionEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.Competition;
import com.example.graduate_sever.model.MyShenBaoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface XueKeJingSaiMapper {

    List<Competition> getAllJingSai(DTO dTO);
    long getAllJingSaiPageTotal();

    List<Competition> getSearchJingSai(DTO dTO);

    void deleteOneJingSai(int id);

    List<People> getJingSaiDetail(Integer id);

    int insertJingSai(CompetitionEntity entity);
    int shenBaoJingSai(CompetitionEntity entity);
    int insertJingSaiParticipation(ParticipationEntity participationEntity);
    Metails XueKeJingSaiMetails(Integer id);
    List<Competition> selectXueKeJingSai(String date1, String date2);

    List<Competition> waitingxuekejingsai(DTO dTO);

    int passXueKeJingSai(Integer id, Integer ispass);

    List<MyShenBaoModel> getXueKeJingSaiDisData(MyShenBaoDTO dto);

    int editCompetition(Integer id, String name, String finishtime, String grade, String student, String level, String partment);

    long getSearchJingSaiPageTotal();

    Competition selectOneCompetition(Integer id);
}

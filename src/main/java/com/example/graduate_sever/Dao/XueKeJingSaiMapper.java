package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.entity.CompetitionEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface XueKeJingSaiMapper {

    List<List<Object>> getAllJingSai(DTO dTO);

    List<List<Object>> getSearchJingSai(DTO dTO);

    void deleteOneJingSai(int id);

    List<People> getJingSaiDetail(Integer id);

    int insertJingSai(CompetitionEntity entity);
    int shenBaoJingSai(CompetitionEntity entity);
    int insertJingSaiParticipation(ParticipationEntity participationEntity);
    Metails XueKeJingSaiMetails();
}

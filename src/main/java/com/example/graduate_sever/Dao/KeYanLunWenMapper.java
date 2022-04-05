package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface KeYanLunWenMapper {

    List<List<Object>> getAllKeYanLunWen(DTO dTO);

    List<List<Object>> getSearchKeYanLunWen(DTO dTO);

    void deleteOneKeYanLunWen(int id);

    List<People> getKeYanLunWenDetail(Integer id);

    int insertKeYanLunWen(HeBingEntity entity);

    int insertKeYanLunWenParticipation(ParticipationEntity participationEntity);
}

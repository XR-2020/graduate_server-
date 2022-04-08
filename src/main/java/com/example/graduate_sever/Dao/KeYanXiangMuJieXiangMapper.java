package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface KeYanXiangMuJieXiangMapper {

    List<List<Object>> getAllKeYanXiangMuJieXiang(DTO dTO);

    List<List<Object>> getSearchKeYanXiangMuJieXiang(DTO dTO);

    void deleteOneKeYanXiangMuJieXiang(int id);

    List<People> getKeYanXiangMuJieXiangDetail(Integer id);
    int insertKeYanXiangMuJieXiang(HeBingEntity entity);
    int shenBaoKeYanXiangMuJieXiang(HeBingEntity entity);
    int insertKeYanXiangMuJieXiangParticipation(ParticipationEntity participationEntity);
}

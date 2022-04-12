package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.ChanXueYan;
import com.example.graduate_sever.model.HengXiangKeYanXiangMu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HengXiangKeYanMapper {

    List<List<Object>> getAllHengXiangKeYan(DTO dTO);

    List<List<Object>> getSearchHengXiangKeYan(DTO dTO);

    void deleteOneHengXiangKeYan(int id);

    List<People> getHengXiangKeYanDetail(Integer id);

    int insertHengXiangKeYan(HeBingEntity entity);
    int shenBaoHengXiangKeYan(HeBingEntity entity);

    int insertHengXiangKeYanParticipation(ParticipationEntity participationEntity);
    Metails  HengXiangKeYanMetails(Integer id);
    List<HengXiangKeYanXiangMu> selectHengXiangKeYan(String date1, String date2);

    List<HengXiangKeYanXiangMu> waitinghengxiangkeyan(DTO dTO);
}

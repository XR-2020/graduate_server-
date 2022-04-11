package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.entity.ZongXiangKeYanXiangMuEntity;
import com.example.graduate_sever.model.ChanXueYan;
import com.example.graduate_sever.model.ZongXiangKeYanXiangMu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ZongXiangKeYanMapper {

    List<List<Object>> getAllZongXiangKeYan(DTO jiaoYanXiangMuDTO);

    List<List<Object>> getSearchZongXiangKeYan(DTO jiaoYanXiangMuDTO);

    void deleteOneZongXiangKeYan(int id);

    List<People> getZongXiangKeYanDetail(Integer id);

    int insertZongXiangKeYan(ZongXiangKeYanXiangMuEntity entity);
    int shenBaoZongXiangKeYan(ZongXiangKeYanXiangMuEntity entity);
    int insertZongXiangKeYanParticipation(ParticipationEntity participationEntity);
    Metails ZongXiangKeYanMetails();
    List<ZongXiangKeYanXiangMu> selectZongXiangKeYan(String date1, String date2);
}

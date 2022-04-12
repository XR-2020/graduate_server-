package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.ChanXueYan;
import com.example.graduate_sever.model.ZhuanLi;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ZhuanLiMapper {

    List<List<Object>> getAllZhuanLi(DTO jiaoYanXiangMuDTO);

    List<List<Object>> getSearchZhuanLi(DTO jiaoYanXiangMuDTO);

    void deleteOneZhuanLi(int id);

    List<People> getZhuanLiDetail(Integer id);

    int insertZhuanLi(HeBingEntity entity);
    int shenBaoZhuanLi(HeBingEntity entity);
    int insertZhuanLiParticipation(ParticipationEntity participationEntity);
    Metails ZhuanLiMetails(Integer id);
    List<ZhuanLi> selectZhuanLi(String date1, String date2);

    List<ZhuanLi> waitingzhuanli(DTO dTO);
}

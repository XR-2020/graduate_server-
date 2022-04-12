package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.ChanXueYan;
import com.example.graduate_sever.model.ZhuZuo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ZhuZuoMapper {

    List<List<Object>> getAllZhuZuo(DTO dTO);

    List<List<Object>> getSearchZhuZuo(DTO dTO);

    void deleteOneZhuZuo(int id);

    List<People> getZhuZuoDetail(Integer id);

    int insertZhuZuo(HeBingEntity entity);
    int shenBaoZhuZuo(HeBingEntity entity);
    int insertZhuZuoParticipation(ParticipationEntity participationEntity);
    Metails ZhuZuoMetails(Integer id);
    List<ZhuZuo> selectZhuZuo(String date1, String date2);

    List<ZhuZuo> waitingzhuzuo(DTO dTO);
}

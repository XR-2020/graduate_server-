package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.ChanXueYan;
import com.example.graduate_sever.model.RuanJianZhuZuoQuan;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RuanJianZhuZuoMapper {

    List<List<Object>> getAllRuanJianZhuZuo(DTO dTO);

    List<List<Object>> getSearchRuanJianZhuZuo(DTO dTO);

    void deleteOneRuanJianZhuZuo(int id);

    List<People> getRuanJianZhuZuoDetail(Integer id);

    int insertRuanJianZhuZuo(HeBingEntity entity);
    int shenBaoRuanJianZhuZuo(HeBingEntity entity);
    int insertRuanJianZhuZuoParticipation(ParticipationEntity participationEntity);
    Metails RuanJianZhuZuoMetails();
    List<RuanJianZhuZuoQuan> selectRuanJianZhuZuo(String date1, String date2);
}

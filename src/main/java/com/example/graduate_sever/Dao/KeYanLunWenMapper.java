package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.ChanXueYan;
import com.example.graduate_sever.model.KeYanLunWen;
import com.example.graduate_sever.model.MyShenBaoModel;
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
    int shenBaoKeYanLunWen(HeBingEntity entity);
    int insertKeYanLunWenParticipation(ParticipationEntity participationEntity);
    Metails KeYanLunWenMetails(Integer id);
    List<KeYanLunWen> selectKeYanLunWen(String date1, String date2);

    List<KeYanLunWen> waitingkeyanlunwen(DTO dTO);

    int passKeYanLunWen(Integer id, Integer ispass);

    List<MyShenBaoModel> getKeYanLunWenDisData(MyShenBaoDTO dto);

    KeYanLunWen selectOneKeYanLunWen(Integer id);
}

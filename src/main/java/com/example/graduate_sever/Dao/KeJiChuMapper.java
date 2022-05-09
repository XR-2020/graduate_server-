package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.EditUO.KeJiChuEditUO;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.entity.NewParticipationEntity;
import com.example.graduate_sever.entity.NewSystemEntity;
import com.example.graduate_sever.model.NewSyatemModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface KeJiChuMapper {
    String kejichuType(Integer id);
    List<List<Object>> getAllKeJiChu(DTO dTO);

    List<List<Object>> getSearchKeJiChu(DTO dTO);

    void deleteOneKeJiChu(int id);

    List<People> getKeJiChuDetail(Integer id, String type);

    int editKeJiChu(KeJiChuEditUO uo);

    Metails getKeJiChuMetails(Integer id);

    int passKeJiChu(Integer id, Integer pass);

    int insertKeJiChu(NewSystemEntity newSystemEntity);

    List<NewSyatemModel> waitingKeJiChu(Integer beginIndex, Integer pageSize, String type);

    long waitingKeJiChuPageToTal(String type);

    int shenBaoKeJiChu(NewSystemEntity element);
}

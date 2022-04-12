package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.entity.HonorEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.ChanXueYan;
import com.example.graduate_sever.model.Honor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RongYuChengHaoMapper {

    List<List<Object>> getAllRongYu(DTO dTO);

    List<List<Object>> getSearchRongYu(DTO dTO);

    void deleteOneRongYu(int id);

    List<People> getRongYuDetail(Integer id);

    int insertRongYuChengHao(HonorEntity entity);
    int shenBaoRongYuChengHao(HonorEntity entity);
    int insertRongYuChengHaoParticipation(ParticipationEntity participationEntity);
    Metails RongYuChengHaoMetails();
    List<Honor> selectRongYuChengHao(String date1, String date2);

    List<Honor> waitingrongyuchenghao(DTO dTO);
}

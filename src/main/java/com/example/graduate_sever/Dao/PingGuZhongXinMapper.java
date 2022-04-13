package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.entity.PingGuZhongXinXiangGuanEntity;
import com.example.graduate_sever.model.ChanXueYan;
import com.example.graduate_sever.model.MyShenBaoModel;
import com.example.graduate_sever.model.PingGuZhongXinXiangGuan;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PingGuZhongXinMapper {

    List<List<Object>> getAllPingGuZhongXin(DTO jiaoYanXiangMuDTO);

    List<List<Object>> getSearchPingGuZhongXin(DTO jiaoYanXiangMuDTO);

    void deleteOnePingGuZhongXin(int id);

    List<People> getPingGuZhongXinDetail(Integer id);

    int insertPingGuZhongXin(PingGuZhongXinXiangGuanEntity entity);
    int shenBaoPingGuZhongXin(PingGuZhongXinXiangGuanEntity entity);
    int insertPingGuZhongXinParticipation(ParticipationEntity participationEntity);
    Metails PingGuZhongXinMetails(Integer id);
    List<PingGuZhongXinXiangGuan> selectPingGuZhongXin(String date1, String date2);

    List<PingGuZhongXinXiangGuan> waitingpingguzhongxin(DTO dTO);

    int passPingGuZhongXin(Integer id, Integer ispass);

    List<MyShenBaoModel> getPingGuZhongXinDisData(MyShenBaoDTO dto);
}

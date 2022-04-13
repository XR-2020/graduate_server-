package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.JiaoYanXiangMuEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.ChanXueYan;
import com.example.graduate_sever.model.JiaoYanXiangMu;
import com.example.graduate_sever.model.MyShenBaoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JiaoYanXiangMuMapper {

    List<List<Object>> getAllJiaoYan(DTO jiaoYanXiangMuDTO);

    List<List<Object>> getSearchJiaoYan(DTO jiaoYanXiangMuDTO);

    void deleteOneJiaoYan(int id);

    List<People> getJiaoYanDetail(Integer id);

    int insertJiaoYanXiangMu(JiaoYanXiangMuEntity entity);
    int shenBaoJiaoYanXiangMu(JiaoYanXiangMuEntity entity);
    int insertJiaoYanXiangMuParticipation(ParticipationEntity participationEntity);
    Metails JiaoYanXiangMetails(Integer id);
    List<JiaoYanXiangMu> selectJiaoYanXiangMu(String date1, String date2);

    List<JiaoYanXiangMu> waitingjiaoyanxiangmu(DTO dTO);

    int passJiaoYan(Integer id, Integer ispass);

    List<MyShenBaoModel> getJiaoYanXiangDisData(MyShenBaoDTO dto);
}

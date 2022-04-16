package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.JiaoYanLunWenEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.ChanXueYan;
import com.example.graduate_sever.model.JiaoYanLunWen;
import com.example.graduate_sever.model.MyShenBaoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JiaoYanLunWenMapper {

    List<List<Object>> getAllJiaoYanLunWen(DTO jiaoYanXiangMuDTO);

    List<List<Object>> getSearchJiaoYanLunWen(DTO jiaoYanXiangMuDTO);

    void deleteOneJiaoYanLunWen(int id);

    List<People> getJiaoYanLunWenDetail(Integer id);

    int insertJiaoYanLunWen(JiaoYanLunWenEntity entity);
    int shenBaoJiaoYanLunWen(JiaoYanLunWenEntity entity);
    int insertJiaoYanLunWenParticipation(ParticipationEntity participationEntity);
    Metails JiaoYanLunWenMetails(Integer id);
    List<JiaoYanLunWen> selectJiaoYanLunWen(String date1, String date2);

    List<JiaoYanLunWen> waitingjiaoyanlunwen(DTO dTO);

    int passJiaoYanLunWen(Integer id, Integer ispass);

    List<MyShenBaoModel> getJiaoYanLunWenDisData(MyShenBaoDTO dto);

    int editJiaoYanLunWen(Integer id, String name, String finishtime, String partment);
}

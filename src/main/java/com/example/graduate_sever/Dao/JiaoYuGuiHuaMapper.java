package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.JiaoYuGuiHuaXiangMuEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.ChanXueYan;
import com.example.graduate_sever.model.JiaoYuGuiHuaXiangMu;
import com.example.graduate_sever.model.MyShenBaoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JiaoYuGuiHuaMapper {

    List<List<Object>> getAllJiaoYuGuiHua(DTO jiaoYanXiangMuDTO);

    List<List<Object>> getSearchJiaoYuGuiHua(DTO jiaoYanXiangMuDTO);

    void deleteOneJiaoYuGuiHua(int id);

    List<People> getJiaoYuGuiHuaDetail(Integer id);

    int insertJiaoYuGuiHua(JiaoYuGuiHuaXiangMuEntity entity);
    int shenBaoJiaoYuGuiHua(JiaoYuGuiHuaXiangMuEntity entity);
    int insertJiaoYuGuiHuaParticipation(ParticipationEntity participationEntity);
    Metails JiaoYuGuiHuaMetails(Integer id);
    List<JiaoYuGuiHuaXiangMu> selectJiaoYuGuiHua(String date1, String date2);

    List<JiaoYuGuiHuaXiangMu> waitingjiaoyuguihua(DTO dTO);

    int passJiaoYuGuiHua(Integer id, Integer ispass);

    List<MyShenBaoModel> getJiaoYuGuiHuaDisData(MyShenBaoDTO dto);

    int editJiaoYuGuiHua(Integer id, String name, String finishtime, String partment, String danwei, String grade, String level);

    JiaoYuGuiHuaXiangMu selectOneJiaoYuGuiHua(Integer id);
}

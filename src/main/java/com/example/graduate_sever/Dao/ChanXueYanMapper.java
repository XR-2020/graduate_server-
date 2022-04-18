package com.example.graduate_sever.Dao;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.ChanXueYan;
import com.example.graduate_sever.model.MyShenBaoModel;
import com.example.graduate_sever.model.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ChanXueYanMapper {

    List<List<Object>> getAll(DTO chanXueYanDTO);

    List<List<Object>> getSearchChanXueYan(DTO chanXueYanDTO);

    void deleteOneChanXueYan(int id);

    List<People> getChanXueYanDetail(Integer id);
    int insertChanXueYan(ChanXueYanEntity entity);
    int shenBaoChanXueYan(ChanXueYanEntity entity);
    int insertChanXueYanParticipation(ParticipationEntity participationEntity);

    List<Object> getTeacherList();
    Metails ChanXueYanmetails(Integer id);
    List<ChanXueYan> selectChanXueYan(String date1,String date2);

    List<ChanXueYan> waitingChanXueYan(DTO dTO);
    int passChanXueYan(Integer id, Integer ispass);
    long waitingPageTotal(String type);
    List<People> getDetail(Integer id,Integer type);
    List<Integer> getBadge(Integer id,Integer type);
    List<MyShenBaoModel> getDisData(String tablename,Integer badge,Integer type,Integer pageIndex,Integer pageSize);
    long getPageTotal(Integer badge,String tablename,Integer type);
    List<MyShenBaoModel> getDaiShenHeData(String tablename,Integer badge,Integer type,Integer pageIndex,Integer pageSize);
    long getDaiShenHePageTotal(Integer badge,String tablename,Integer type);
    List<MyShenBaoModel> getHadPassData(String tablename,Integer badge,Integer type,Integer pageIndex,Integer pageSize);
    long getHadPassPageTotal(Integer badge,String tablename,Integer type);

    int deleteMyShenBao(Integer id, String tablename);

    int deleteDaiShenHePartipation(Integer id, Integer type);
    void deletePeople(Integer id, Integer type);

    int editChanXueYan(Integer id, String name, String finishtime, String partment, String lianghua, String wenhao);

    int editPeople(Integer ach_id, Integer badge,Integer type);

    int editHeBing(Integer id, String tablename, String name, String finishtime, String partment);

    ChanXueYan selectOneChanXueYan(Integer id);

    Teacher login(Integer username, String password);
}

package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.NewParticipationEntity;
import com.example.graduate_sever.entity.NewSystemEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.NewSyatemModel;
import com.example.graduate_sever.model.SheKeChuModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NewSystemMapper {
    int insertNewSystem(NewSystemEntity entity);
    int shenBaoNewSystem(NewSystemEntity entity);
    int insertNewSystemParticipation(NewParticipationEntity Entity);

    List<List<Object>> getAllNewSystem(DTO dto);

    List<List<Object>> getSearchNewSystem(DTO dto);

    List<People> getNewSystemDetail(Integer id, String type);

    int editNewSystem(Integer id, String name, String finishtime, String partment);

    void deletePeople(Integer id,String type);

    void editNewSystemPeople(Integer ach_id, Integer badge,String type);

    void deleteOneNewSystem(int id);

    int shenBaoJiaoWuChu(NewSystemEntity element);


}

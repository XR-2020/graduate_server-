package com.example.graduate_sever.Dao;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.entity.ChanXueYanEntity;
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

}

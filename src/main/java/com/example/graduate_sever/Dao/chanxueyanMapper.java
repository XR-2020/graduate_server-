package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.ChanXueYanDTO;
import com.example.graduate_sever.common.People;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface chanxueyanMapper {

    List<List<Object>> getAll(ChanXueYanDTO chanXueYanDTO);

    List<List<Object>> getSearchChanXueYan(ChanXueYanDTO chanXueYanDTO);

    void deleteOneChanXueYan(int id);

    List<People> getChanXueYanDetail(Integer id);

}

package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.People;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HengXiangKeYanMapper {

    List<List<Object>> getAllHengXiangKeYan(DTO dTO);

    List<List<Object>> getSearchHengXiangKeYan(DTO dTO);

    void deleteOneHengXiangKeYan(int id);

    List<People> getHengXiangKeYanDetail(Integer id);

}

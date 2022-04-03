package com.example.graduate_sever.Dao;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.StatisticalDTO;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StatisticalMapper {

    List<List<Object>> SearchDetail(StatisticalDTO dto);
    List<List<Object>> SearchAll(StatisticalDTO dto);



}

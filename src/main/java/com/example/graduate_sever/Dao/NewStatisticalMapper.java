package com.example.graduate_sever.Dao;
import com.example.graduate_sever.common.DTO.StatisticalDTO;
import com.example.graduate_sever.entity.ZheXianEntity;
import com.example.graduate_sever.model.NewSyatemModel;
import com.example.graduate_sever.model.SheKeChuModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NewStatisticalMapper {


    List<NewSyatemModel> selectOne(String date1, String date2, String type);

    List<SheKeChuModel> selectOneSheKeChu(String date1, String date2, String type);
}

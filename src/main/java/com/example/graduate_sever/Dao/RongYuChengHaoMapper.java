package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.People;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RongYuChengHaoMapper {

    List<List<Object>> getAllRongYu(DTO dTO);

    List<List<Object>> getSearchRongYu(DTO dTO);

    void deleteOneRongYu(int id);

//    List<People> getRongYuDetail(Integer id);

}

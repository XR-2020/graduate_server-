package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.People;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ZhuZuoMapper {

    List<List<Object>> getAllZhuZuo(DTO dTO);

    List<List<Object>> getSearchZhuZuo(DTO dTO);

    void deleteOneZhuZuo(int id);

    List<People> getZhuZuoDetail(Integer id);

}

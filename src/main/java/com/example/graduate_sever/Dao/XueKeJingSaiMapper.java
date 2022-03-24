package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.People;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface XueKeJingSaiMapper {

    List<List<Object>> getAllJingSai(DTO dTO);

    List<List<Object>> getSearchJingSai(DTO dTO);

    void deleteOneJingSai(int id);

    List<People> getJingSaiDetail(Integer id);

}

package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.People;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface KeYanXiangMuJieXiangMapper {

    List<List<Object>> getAllKeYanXiangMuJieXiang(DTO dTO);

    List<List<Object>> getSearchKeYanXiangMuJieXiang(DTO dTO);

    void deleteOneKeYanXiangMuJieXiang(int id);

    List<People> getKeYanXiangMuJieXiangDetail(Integer id);

}

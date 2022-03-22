package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.People;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ZhuanLiMapper {

    List<List<Object>> getAllZhuanLi(DTO jiaoYanXiangMuDTO);

    List<List<Object>> getSearchZhuanLi(DTO jiaoYanXiangMuDTO);

    void deleteOneZhuanLi(int id);

    List<People> getZhuanLiDetail(Integer id);

}

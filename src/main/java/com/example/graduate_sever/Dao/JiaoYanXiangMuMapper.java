package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.entity.JiaoYanXiangMuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JiaoYanXiangMuMapper {

    List<List<Object>> getAllJiaoYan(DTO jiaoYanXiangMuDTO);

    List<List<Object>> getSearchJiaoYan(DTO jiaoYanXiangMuDTO);

    void deleteOneJiaoYan(int id);

    List<People> getJiaoYanDetail(Integer id);

    int insertJiaoYanXiangMu(JiaoYanXiangMuEntity entity);

}

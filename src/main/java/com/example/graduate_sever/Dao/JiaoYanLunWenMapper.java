package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.JiaoYanLunWenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JiaoYanLunWenMapper {

    List<List<Object>> getAllJiaoYanLunWen(DTO jiaoYanXiangMuDTO);

    List<List<Object>> getSearchJiaoYanLunWen(DTO jiaoYanXiangMuDTO);

    void deleteOneJiaoYanLunWen(int id);

    List<People> getJiaoYanLunWenDetail(Integer id);

    int insertJiaoYanLunWen(JiaoYanLunWenEntity entity);
}

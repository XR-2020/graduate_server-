package com.example.graduate_sever.Dao;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.People;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PingGuZhongXinMapper {

    List<List<Object>> getAllPingGuZhongXin(DTO jiaoYanXiangMuDTO);

    List<List<Object>> getSearchPingGuZhongXin(DTO jiaoYanXiangMuDTO);

    void deleteOnePingGuZhongXin(int id);

    List<People> getPingGuZhongXinDetail(Integer id);

}

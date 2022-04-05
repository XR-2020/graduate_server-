package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.model.Teacher;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.List;

public interface  ChanXueYanService {
     ResVO selectAll(DTO chanXueYanDTO);
     ResVO getSearchChanXueYan(DTO chanXueYanDTO);
     JsonBean deleteChanXueYan(int[] ids);
     JsonBean deleteOneChanXueYan(Integer id);
     JsonBean getChanXueYanDetail(Integer id);
     JsonBean insertChanXueYan(ChanXueYanEntity entity,Integer[] people);
     List<Object> getTeacherList();
     void crawlerWebSite(String td, CloseableHttpClient httpClient,String cookie);
}

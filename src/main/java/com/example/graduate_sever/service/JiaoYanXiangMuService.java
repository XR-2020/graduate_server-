package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.JiaoYanXiangMuEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

public interface JiaoYanXiangMuService {
     ResVO getAllJiaoYan(DTO dTO);
     ResVO getSearchJiaoYan(DTO dTO);
     JsonBean deleteJiaoYan(int[] ids);
     JsonBean deleteOneJiaoYan(Integer id);
     JsonBean getJiaoYanDetial(Integer id);
     JsonBean insertJiaoYan(JiaoYanXiangMuEntity entity,Integer[] people);
     void JiaoYanXiangMuCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view);
}

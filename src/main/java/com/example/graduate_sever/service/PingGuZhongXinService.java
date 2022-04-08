package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.PingGuZhongXinXiangGuanEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

public interface PingGuZhongXinService {
     ResVO getAllPingGuZhongXin(DTO jiaoYanXiangMuDTO);
     ResVO getSearchPingGuZhongXin(DTO jiaoYanXiangMuDTO);
     JsonBean deletePingGuZhongXin(int[] ids);
     JsonBean deleteOnePingGuZhongXin(Integer id);
     JsonBean getPingGuZhongXinDetial(Integer id);
     JsonBean insertPingGuZhongXin(PingGuZhongXinXiangGuanEntity entity,Integer[] people);
     JsonBean shenBaoPingGuZhongXin(PingGuZhongXinXiangGuanEntity entity,Integer[] people);
     void PingGuZhongXinCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view);
}

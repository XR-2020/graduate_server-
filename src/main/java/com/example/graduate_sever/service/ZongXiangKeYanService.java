package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.ZongXiangKeYanXiangMuEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

public interface ZongXiangKeYanService {
     ResVO getAllZongXiangKeYan(DTO jiaoYanXiangMuDTO);
     ResVO getSearchZongXiangKeYan(DTO jiaoYanXiangMuDTO);
     JsonBean deleteZongXiangKeYan(int[] ids);
     JsonBean deleteOneZongXiangKeYan(Integer id);
     JsonBean getZongXiangKeYanDetial(Integer id);
     JsonBean insertZongXiangKeYan(ZongXiangKeYanXiangMuEntity entity,Integer[] people);
     JsonBean shenBaoZongXiangKeYan(ZongXiangKeYanXiangMuEntity entity,Integer[] people);
     void ZongXiangKeYanCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view);
}

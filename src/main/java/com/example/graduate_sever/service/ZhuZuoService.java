package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.HeBingEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

public interface ZhuZuoService {
     ResVO getAllZhuZuo(DTO dTO);
     ResVO getSearchZhuZuo(DTO dTO);
     JsonBean deleteZhuZuo(int[] ids);
     JsonBean deleteOneZhuZuo(Integer id);
     JsonBean getZhuZuoDetial(Integer id);
     JsonBean insertZhuZuo(HeBingEntity entity,Integer[] people);
     JsonBean shenBaoZhuZuo(HeBingEntity entity,Integer[] people);
     void ZhuZuoCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view);
}

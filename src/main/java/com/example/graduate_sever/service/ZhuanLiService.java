package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.HeBingEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.List;

public interface ZhuanLiService {
     ResVO getAllZhuanLi(DTO jiaoYanXiangMuDTO);
     ResVO getSearchZhuanLi(DTO jiaoYanXiangMuDTO);
     JsonBean deleteZhuanLi(int[] ids);
     JsonBean deleteOneZhuanLi(Integer id);
     JsonBean getZhuanLiDetial(Integer id);
     JsonBean insertZhuanLi(HeBingEntity entity,Integer[] people);
     JsonBean shenBaoZhuanLi(HeBingEntity entity,Integer[] people);
     void ZhuanLiCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view);

     List<TableData>  waitingzhuanli(DTO dTO);
}

package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.JiaoYanLunWenEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.List;

public interface JiaoYanLunWenMuService {
     ResVO getAllJiaoYanLunWen(DTO jiaoYanXiangMuDTO);
     ResVO getSearchJiaoYanLunWen(DTO jiaoYanXiangMuDTO);
     JsonBean deleteJiaoYanLunWen(int[] ids);
     JsonBean deleteOneJiaoYanLunWen(Integer id);
     JsonBean getJiaoYanLunWenDetial(Integer id);
     JsonBean insertJiaoYanLunWen(JiaoYanLunWenEntity entity,Integer[] people);
     JsonBean shenBaoJiaoYanLunWen(JiaoYanLunWenEntity entity,Integer[] people);
     void JiaoYanLunWenCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view);

     List<TableData>  waitingjiaoyanlunwen(DTO dTO);
     Metails getJiaoYanLunWenMetails(Integer id);

    int passJiaoYanLunWen(Integer id, Integer ispass);
}

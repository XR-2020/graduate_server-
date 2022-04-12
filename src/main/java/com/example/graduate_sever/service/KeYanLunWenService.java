package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.HeBingEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.List;

public interface KeYanLunWenService {
     ResVO getAllKeYanLunWen(DTO dTO);
     ResVO getSearchKeYanLunWen(DTO dTO);
     JsonBean deleteKeYanLunWen(int[] ids);
     JsonBean deleteOneKeYanLunWen(Integer id);
     JsonBean getKeYanLunWenDetial(Integer id);
     JsonBean insertKeYanLunWen(HeBingEntity entity,Integer[] people);
     JsonBean shenBaoKeYanLunWen(HeBingEntity entity,Integer[] people);
     void KeYanLunWenCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view);
     Metails getKeYanLunWenMetails(Integer id);
     List<TableData>  waitingkeyanlunwen(DTO dTO);

    int passKeYanLunWen(Integer id, Integer ispass);
}

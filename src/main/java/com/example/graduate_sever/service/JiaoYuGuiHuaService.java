package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.JiaoYuGuiHuaXiangMuEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

public interface JiaoYuGuiHuaService {
     ResVO getAllJiaoYuGuiHua(DTO dTO);
     ResVO getSearchJiaoYuGuiHua(DTO dTO);
     JsonBean deleteJiaoYuGuiHua(int[] ids);
     JsonBean deleteOneJiaoYuGuiHua(Integer id);
     JsonBean getJiaoYuGuiHuaDetial(Integer id);
     JsonBean insertJiaoYuGuiHua(JiaoYuGuiHuaXiangMuEntity entity,Integer[] people);
     void JiaoYuGuiHuaCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view);
}

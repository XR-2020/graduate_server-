package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.JiaoYuGuiHuaXiangMuEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.List;

public interface JiaoYuGuiHuaService {
     ResVO getAllJiaoYuGuiHua(DTO dTO);
     ResVO getSearchJiaoYuGuiHua(DTO dTO);
     JsonBean deleteJiaoYuGuiHua(int[] ids);
     JsonBean deleteOneJiaoYuGuiHua(Integer id);
     JsonBean getJiaoYuGuiHuaDetial(Integer id);
     JsonBean insertJiaoYuGuiHua(JiaoYuGuiHuaXiangMuEntity entity,Integer[] people);
     JsonBean shenBaoJiaoYuGuiHua(JiaoYuGuiHuaXiangMuEntity entity,Integer[] people);
     void JiaoYuGuiHuaCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view);
     Metails getJiaoYuGuiHuaMetails(Integer id);
     List<TableData>  waitingjiaoyuguihua(DTO dTO);

    int passJiaoYuGuiHua(Integer id, Integer ispass);

     List<TableData> getDisData(MyShenBaoDTO dto);
}

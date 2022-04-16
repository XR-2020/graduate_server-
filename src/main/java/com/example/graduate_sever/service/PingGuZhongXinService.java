package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.PingGuZhongXinXiangGuanEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.List;

public interface PingGuZhongXinService {
     ResVO getAllPingGuZhongXin(DTO jiaoYanXiangMuDTO);
     ResVO getSearchPingGuZhongXin(DTO jiaoYanXiangMuDTO);
     JsonBean deletePingGuZhongXin(int[] ids);
     JsonBean deleteOnePingGuZhongXin(Integer id);
     JsonBean getPingGuZhongXinDetial(Integer id);
     JsonBean insertPingGuZhongXin(PingGuZhongXinXiangGuanEntity entity,Integer[] people);
     JsonBean shenBaoPingGuZhongXin(PingGuZhongXinXiangGuanEntity entity,Integer[] people);
     void PingGuZhongXinCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view);
     Metails getPingGuZhongXinMetails(Integer id);
     List<TableData>  waitingpingguzhongxin(DTO dTO);

    int passPingGuZhongXin(Integer id, Integer ispass);

     List<TableData> getDisData(MyShenBaoDTO dto);

    List<Integer> getPingGuZhongXinDetailBadge(Integer id);

    int editPingGuZhongXin(Integer id, String name, String finishtime, String partment, Integer[] people, String grade);
}

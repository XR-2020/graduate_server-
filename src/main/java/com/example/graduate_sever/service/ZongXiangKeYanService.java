package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.ZongXiangKeYanXiangMuEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.List;

public interface ZongXiangKeYanService {
     ResVO getAllZongXiangKeYan(DTO jiaoYanXiangMuDTO);
     ResVO getSearchZongXiangKeYan(DTO jiaoYanXiangMuDTO);
     JsonBean deleteZongXiangKeYan(List<Integer> ids);
     JsonBean deleteOneZongXiangKeYan(Integer id);
     JsonBean getZongXiangKeYanDetial(Integer id);
     JsonBean insertZongXiangKeYan(ZongXiangKeYanXiangMuEntity entity,Integer[] people);
     JsonBean shenBaoZongXiangKeYan(ZongXiangKeYanXiangMuEntity entity,Integer[] people);
     void ZongXiangKeYanCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view);
     Metails getZongXiangKeYanMetails(Integer id);
     List<TableData>  waitingzongxiangkeyan(DTO dTO);

    int passZongXiangKeYan(Integer id, Integer ispass);

     List<TableData> getDisData(MyShenBaoDTO dto);

    List<Integer> getZongXiangKeYanDetailBadge(Integer id);

    int editZongXiangKeYan(Integer id, String name, String finishtime, String partment, String level, Integer[] people);
}

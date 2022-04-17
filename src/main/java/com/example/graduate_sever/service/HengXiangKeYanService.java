package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.HeBingEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.List;

public interface HengXiangKeYanService {
     ResVO getAllHengXiangKeYan(DTO dTO);
     ResVO getSearchHengXiangKeYan(DTO dTO);
     JsonBean deleteHengXiangKeYan(List<Integer> ids);
     JsonBean deleteOneHengXiangKeYan(Integer id);
     JsonBean getHengXiangKeYanDetial(Integer id);
     JsonBean insertHengXiangKeYan(HeBingEntity entity,Integer[] people);
     JsonBean shenBaoHengXiangKeYan(HeBingEntity entity,Integer[] people);
     void HengXiangKeYanCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view);

     List<TableData>  waitinghengxiangkeyan(DTO dTO);
     Metails getHengXiangKeYanMetails(Integer id);

    int passHengXiangKeYan(Integer id, Integer ispass);

     List<TableData> getDisData(MyShenBaoDTO dto);

    List<Integer> getHengXiangKeYanDetailBadge(Integer id);
}

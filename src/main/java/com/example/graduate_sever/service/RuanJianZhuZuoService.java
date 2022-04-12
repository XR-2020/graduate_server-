package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.HeBingEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.List;

public interface RuanJianZhuZuoService {
     ResVO getAllRuanJianZhuZuo(DTO jiaoYanXiangMuDTO);
     ResVO getSearchRuanJianZhuZuo(DTO jiaoYanXiangMuDTO);
     JsonBean deleteRuanJianZhuZuo(int[] ids);
     JsonBean deleteOneRuanJianZhuZuo(Integer id);
     JsonBean getRuanJianZhuZuoDetial(Integer id);
     JsonBean insertRuanJianZhuZuo(HeBingEntity entity,Integer[] people);
     JsonBean shenBaoRuanJianZhuZuo(HeBingEntity entity,Integer[] people);
     void RuanJianZhuZuoCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view);

     List<TableData>  waitingruanjianzhuzuo(DTO dTO);
}

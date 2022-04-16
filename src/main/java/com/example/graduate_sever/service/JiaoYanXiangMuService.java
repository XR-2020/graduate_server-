package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.JiaoYanXiangMuEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.List;

public interface JiaoYanXiangMuService {
     ResVO getAllJiaoYan(DTO dTO);
     ResVO getSearchJiaoYan(DTO dTO);
     JsonBean deleteJiaoYan(int[] ids);
     JsonBean deleteOneJiaoYan(Integer id);
     JsonBean getJiaoYanDetial(Integer id);
     JsonBean insertJiaoYan(JiaoYanXiangMuEntity entity,Integer[] people);
     JsonBean shenBaoJiaoYan(JiaoYanXiangMuEntity entity,Integer[] people);
     void JiaoYanXiangMuCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view);

     List<TableData>  waitingjiaoyanxiangmu(DTO dTO);

     Metails getJiaoYanXiangMuMetails(Integer id);

    int passJiaoYan(Integer id, Integer ispass);

     List<TableData> getDisData(MyShenBaoDTO dto);

    List<Integer> getJiaoYanDetialBadge(Integer id);

    int editJiaoYan(Integer id, String name, String finishtime, String partment, String lianghua, Integer[] people, String wenhao);
}

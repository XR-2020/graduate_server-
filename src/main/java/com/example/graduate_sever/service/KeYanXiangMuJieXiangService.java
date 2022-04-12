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

public interface KeYanXiangMuJieXiangService {
     ResVO getAllKeYanXiangMuJieXiang(DTO dTO);
     ResVO getSearchKeYanXiangMuJieXiang(DTO dTO);
     JsonBean deleteKeYanXiangMuJieXiang(int[] ids);
     JsonBean deleteOneKeYanXiangMuJieXiang(Integer id);
     JsonBean getKeYanXiangMuJieXiangDetial(Integer id);
     JsonBean insertKeYanXiangMuJieXiang(HeBingEntity entity,Integer[] people);
     JsonBean shenBaoKeYanXiangMuJieXiang(HeBingEntity entity,Integer[] people);
     void KeYanXiangMuJieXiangCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view);
     Metails getKeYanXiangMuJieXiangMetails(Integer id);
     List<TableData>  waitingkeyanxiangmujiexiang(DTO dTO);

    int passKeYanXiangMuJieXiang(Integer id, Integer ispass);
}

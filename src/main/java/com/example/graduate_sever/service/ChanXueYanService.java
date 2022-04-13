package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.List;

public interface  ChanXueYanService {
     ResVO selectAll(DTO chanXueYanDTO);
     ResVO getSearchChanXueYan(DTO chanXueYanDTO);
     JsonBean deleteChanXueYan(int[] ids);
     JsonBean deleteOneChanXueYan(Integer id);
     JsonBean getChanXueYanDetail(Integer id);
     JsonBean insertChanXueYan(ChanXueYanEntity entity,Integer[] people);
     JsonBean shenBaoChanXueYan(ChanXueYanEntity entity,Integer[] people);
     List<Object> getTeacherList();
     void ChanXueYancrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view);

    List<TableData> waitingChanXueYan(DTO dTO);

    long waitingPageTotal(Integer type);


    Metails getchanxueyanMetails(Integer id);

    int passChanXueYan(Integer id, Integer ispass);

    List<TableData> getDisData(MyShenBaoDTO dto);

    long getPageTotal();
}

package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.JiaoWuChuUO;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.util.List;

public interface NewSystemCrawlerService {
    List<String> typeList(CloseableHttpClient httpClient) throws IOException;
    void newSystemCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view);

    ResVO getAllNewSystem(DTO chanXueYanDTO);

    ResVO getSearchNewSystem(DTO chanXueYanDTO);

    JsonBean getNewSystemDetail(Integer id, String type);

    List<Integer> getNewSystemBadge(Integer id,String type);

    int editNewSystem(Integer id, String name, String finishtime, String partment, Integer[] people,String type);

    JsonBean deleteNewSystem(List<Integer> ids);

    JsonBean deleteOneNewSystem(Integer id);

    JsonBean shenBaoSheKeChu(JiaoWuChuUO uo) throws IOException;
}

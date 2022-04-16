package com.example.graduate_sever.service;

import com.example.graduate_sever.common.*;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
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

    List<DaiShenHeTableData>getDisData(MyShenBaoDTO dto);
    List<DaiShenHeTableData>getHadPassData(MyShenBaoDTO dto);
    List<DaiShenHeTableData>getDaiShenHeData(MyShenBaoDTO dto);

    long getPageTotal(Integer badge);
    long getHadPassPageTotal(Integer badge);
    long getDaiShenHePageTotal(Integer badge);

    int deleteMyShenBao(Integer id, String tablename);

    List<Integer> getChanXueYanDetailBadge(Integer id);

    int editChanXueYan(Integer id, String name, String finishtime, String partment, String lianghua, Integer[] people, String wenhao);

    int editHeBing(Integer id, String tablename, String name, String finishtime, String partment, Integer[] people, Integer type);
}

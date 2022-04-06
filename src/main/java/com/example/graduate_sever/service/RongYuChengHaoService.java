package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.HonorEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

public interface RongYuChengHaoService {
     ResVO getAllRongYu(DTO jiaoYanXiangMuDTO);
     ResVO getSearchRongYu(DTO jiaoYanXiangMuDTO);
     JsonBean deleteRongYu(int[] ids);
     JsonBean deleteOneRongYu(Integer id);
//     JsonBean getRongYuDetial(Integer id);

     JsonBean insertRongYuChengHao(HonorEntity entity,Integer[] people);
}

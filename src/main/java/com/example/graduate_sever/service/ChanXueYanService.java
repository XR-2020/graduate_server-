package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.ChanXueYanEntity;

public interface  ChanXueYanService {
     ResVO selectAll(DTO chanXueYanDTO);
     ResVO getSearchChanXueYan(DTO chanXueYanDTO);
     JsonBean deleteChanXueYan(int[] ids);
     JsonBean deleteOneChanXueYan(Integer id);
     JsonBean getChanXueYanDetail(Integer id);
     JsonBean insertChanXueYan(ChanXueYanEntity entity);
}

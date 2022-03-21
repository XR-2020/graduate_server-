package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.ChanXueYanDTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;

public interface  ChanXueYanService {
     ResVO selectAll(ChanXueYanDTO chanXueYanDTO);
     ResVO getSearchChanXueYan(ChanXueYanDTO chanXueYanDTO);
     JsonBean deleteChanXueYan(int[] ids);
     JsonBean deleteOneChanXueYan(Integer id);
     JsonBean getChanXueYanDetail(Integer id);
}

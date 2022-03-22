package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;

public interface JiaoYuGuiHuaService {
     ResVO getAllJiaoYuGuiHua(DTO dTO);
     ResVO getSearchJiaoYuGuiHua(DTO dTO);
     JsonBean deleteJiaoYuGuiHua(int[] ids);
     JsonBean deleteOneJiaoYuGuiHua(Integer id);
     JsonBean getJiaoYuGuiHuaDetial(Integer id);
}

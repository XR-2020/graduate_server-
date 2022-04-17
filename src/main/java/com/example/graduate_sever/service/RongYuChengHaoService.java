package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.HonorEntity;

import java.util.List;

public interface RongYuChengHaoService {
     ResVO getAllRongYu(DTO jiaoYanXiangMuDTO);
     ResVO getSearchRongYu(DTO jiaoYanXiangMuDTO);
     JsonBean deleteRongYu(int[] ids);
     JsonBean deleteOneRongYu(Integer id);
//     JsonBean getRongYuDetial(Integer id);

     JsonBean insertRongYuChengHao(HonorEntity entity,Integer[] people);
     JsonBean shenbaoRongYuChengHao(HonorEntity entity);
     Metails getRongYuChengHaoMetails(Integer id);
     List<TableData>  waitingrongyuchenghao(DTO dTO);

    int passRongYuChengHao(Integer id, Integer ispass);

    List<TableData> getDisData(MyShenBaoDTO dto);

    List<Integer> getHonorBadge(Integer id);

    int editHonor(Integer id, String name, String partment, String finishtime, String teacher, String level);
}

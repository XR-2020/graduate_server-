package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.RongYuChengHaoMapper;
import com.example.graduate_sever.Dao.ZhuZuoMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.RongYuChengHaoService;
import com.example.graduate_sever.service.ZhuZuoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RongYuChengHaoService")
public class RongYuChengHaoimpl implements RongYuChengHaoService {
    @Autowired
    private RongYuChengHaoMapper mapper;
    @Override
    public ResVO getAllRongYu(DTO dTO) {
        List<List<Object>>data=mapper.getAllRongYu(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchRongYu(DTO dTO) {
        List<List<Object>>data=mapper.getSearchRongYu(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteRongYu(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneRongYu(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneRongYu(Integer id) {
        mapper.deleteOneRongYu(id);
        return new JsonBean(200,"","");
    }

//    @Override
//    public JsonBean getRongYuDetial(Integer id) {
//        return new JsonBean(200,"",mapper.getZhuZuoDetail(id));
//    }
}

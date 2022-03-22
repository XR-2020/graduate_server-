package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.PingGuZhongXinMapper;
import com.example.graduate_sever.Dao.ZhuanLiMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.PingGuZhongXinService;
import com.example.graduate_sever.service.ZhuanLiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ZhuanLiService")
public class ZhuanLiimpl implements ZhuanLiService {
    @Autowired
    private ZhuanLiMapper mapper;
    @Override
    public ResVO getAllZhuanLi(DTO dTO) {
        List<List<Object>>data=mapper.getAllZhuanLi(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchZhuanLi(DTO dTO) {
        List<List<Object>>data=mapper.getSearchZhuanLi(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteZhuanLi(int[] ids) {
        for (int id:ids) {
            mapper.deleteOneZhuanLi(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneZhuanLi(Integer id) {
        mapper.deleteOneZhuanLi(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getZhuanLiDetial(Integer id) {
        return new JsonBean(200,"",mapper.getZhuanLiDetail(id));
    }
}

package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.JiaoYanLunWenMapper;
import com.example.graduate_sever.Dao.JiaoYanXiangMuMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.JiaoYanLunWenMuService;
import com.example.graduate_sever.service.JiaoYanXiangMuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("JiaoYanXiangMuService")
public class JiaoYanLunWenimpl implements JiaoYanLunWenMuService {
    @Autowired
    private JiaoYanLunWenMapper jiaoYanLunWenMapper;
    @Override
    public ResVO getAllJiaoYanLunWen(DTO jiaoYanXiangMuDTO) {
        List<List<Object>>data=jiaoYanLunWenMapper.getAllJiaoYanLunWen(jiaoYanXiangMuDTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchJiaoYanLunWen(DTO jiaoYanXiangMuDTO) {
        List<List<Object>>data=jiaoYanLunWenMapper.getSearchJiaoYanLunWen(jiaoYanXiangMuDTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteJiaoYanLunWen(int[] ids) {
        for (int id:ids) {
            jiaoYanLunWenMapper.deleteOneJiaoYanLunWen(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneJiaoYanLunWen(Integer id) {
        jiaoYanLunWenMapper.deleteOneJiaoYanLunWen(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getJiaoYanLunWenDetial(Integer id) {
        return new JsonBean(200,"",jiaoYanLunWenMapper.getJiaoYanLunWenDetail(id));
    }
}

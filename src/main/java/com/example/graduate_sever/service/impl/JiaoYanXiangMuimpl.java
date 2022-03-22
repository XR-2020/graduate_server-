package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.JiaoYanXiangMuMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.JiaoYanXiangMuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("JiaoYanXiangMuService")
public class JiaoYanXiangMuimpl implements JiaoYanXiangMuService {
    @Autowired
    private JiaoYanXiangMuMapper jiaoYanXiangMuMapper;
    @Override
    public ResVO getAllJiaoYan(DTO jiaoYanXiangMuDTO) {
        List<List<Object>>data=jiaoYanXiangMuMapper.getAllJiaoYan(jiaoYanXiangMuDTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchJiaoYan(DTO jiaoYanXiangMuDTO) {
        List<List<Object>>data=jiaoYanXiangMuMapper.getSearchJiaoYan(jiaoYanXiangMuDTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteJiaoYan(int[] ids) {
        for (int id:ids) {
            jiaoYanXiangMuMapper.deleteOneJiaoYan(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneJiaoYan(Integer id) {
        jiaoYanXiangMuMapper.deleteOneJiaoYan(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getJiaoYanDetial(Integer id) {
        return new JsonBean(200,"",jiaoYanXiangMuMapper.getJiaoYanDetail(id));
    }
}

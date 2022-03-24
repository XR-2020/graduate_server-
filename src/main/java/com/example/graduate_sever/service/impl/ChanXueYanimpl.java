package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.ChanXueYanMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.service.ChanXueYanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ChanXueYanService")
public class ChanXueYanimpl implements ChanXueYanService {
    @Autowired
    private ChanXueYanMapper chanxueyanMapper;
    @Override
    public ResVO selectAll(DTO chanXueYanDTO) {
        List<List<Object>>data=chanxueyanMapper.getAll(chanXueYanDTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchChanXueYan(DTO chanXueYanDTO) {
        List<List<Object>>data=chanxueyanMapper.getSearchChanXueYan(chanXueYanDTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteChanXueYan(int[] ids) {
        for (int id:ids) {
            chanxueyanMapper.deleteOneChanXueYan(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneChanXueYan(Integer id) {
        chanxueyanMapper.deleteOneChanXueYan(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getChanXueYanDetail(Integer id) {
        return new JsonBean(200,"",chanxueyanMapper.getChanXueYanDetail(id));
    }

    @Override
    public JsonBean insertChanXueYan(ChanXueYanEntity entity) {
        return new JsonBean(200,"",chanxueyanMapper.insertChanXueYan(entity));
    }
}

package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.chanxueyanMapper;
import com.example.graduate_sever.common.DTO.ChanXueYanDTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.entity.ChanXueYan;
import com.example.graduate_sever.service.ChanXueYanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ChanXueYanService")
public class ChanXueYanimpl implements ChanXueYanService {
    @Autowired
    private chanxueyanMapper chanxueyanMapper;
    @Override
    public ResVO selectAll(ChanXueYanDTO chanXueYanDTO) {
        List<Object> list=chanxueyanMapper.getAll(chanXueYanDTO).get(0);
        long total= (long)chanxueyanMapper.getAll(chanXueYanDTO).get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchChanXueYan(ChanXueYanDTO chanXueYanDTO) {
        List<Object> list=chanxueyanMapper.getSearchChanXueYan(chanXueYanDTO).get(0);
        long total= (long)chanxueyanMapper.getSearchChanXueYan(chanXueYanDTO).get(1).get(0);
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
}

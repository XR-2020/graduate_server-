package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.JiaoYuGuiHuaMapper;
import com.example.graduate_sever.Dao.PingGuZhongXinMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.service.JiaoYuGuiHuaService;
import com.example.graduate_sever.service.PingGuZhongXinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("JiaoYuGuiHuaService")
public class JiaoYuGuiHuaimpl implements JiaoYuGuiHuaService {
    @Autowired
    private JiaoYuGuiHuaMapper mapper;
    @Override
    public ResVO getAllJiaoYuGuiHua(DTO dTO) {
        List<List<Object>>data=mapper.getAllPingGuZhongXin(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchJiaoYuGuiHua(DTO dTO) {
        List<List<Object>>data=mapper.getSearchPingGuZhongXin(dTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        System.out.println(total);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean deleteJiaoYuGuiHua(int[] ids) {
        for (int id:ids) {
            mapper.deleteOnePingGuZhongXin(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneJiaoYuGuiHua(Integer id) {
        mapper.deleteOnePingGuZhongXin(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getJiaoYuGuiHuaDetial(Integer id) {
        return new JsonBean(200,"",mapper.getPingGuZhongXinDetail(id));
    }
}

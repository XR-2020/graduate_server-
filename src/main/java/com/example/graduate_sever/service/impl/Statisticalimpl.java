package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.*;
import com.example.graduate_sever.common.DTO.StatisticalDTO;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.controller.RuanJianZhuZuo;
import com.example.graduate_sever.entity.ZheXianEntity;
import com.example.graduate_sever.model.*;
import com.example.graduate_sever.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("StatisticalService")
public class Statisticalimpl implements StatisticalService {
    @Autowired
    private StatisticalMapper mapper;
    @Autowired
    private ChanXueYanMapper chanXueYanMapper;
    @Autowired
    private HengXiangKeYanMapper hengXiangKeYanMapper;
    @Autowired
    private JiaoYanLunWenMapper jiaoYanLunWenMapper;
    @Autowired
    private JiaoYanXiangMuMapper jiaoYanXiangMuMapper;
    @Autowired
    private JiaoYuGuiHuaMapper jiaoYuGuiHuaMapper;
    @Autowired
    private KeYanLunWenMapper keYanLunWenMapper;
    @Autowired
    private KeYanXiangMuJieXiangMapper keYanXiangMuJieXiangMapper;
    @Autowired
    private PingGuZhongXinMapper pingGuZhongXinMapper;
    @Autowired
    private RongYuChengHaoMapper rongYuChengHaoMapper;
    @Autowired
    private RuanJianZhuZuoMapper ruanJianZhuZuoMapper;
    @Autowired
    private XueKeJingSaiMapper xueKeJingSaiMapper;
    @Autowired
    private ZhuanLiMapper zhuanLiMapper;
    @Autowired
    private ZhuZuoMapper zhuZuoMapper;
    @Autowired
    private ZongXiangKeYanMapper zongXiangKeYanMapper;


    @Override
    public List<ZheXian> selectAll(StatisticalDTO dto) {
        ZheXianEntity zheXian=null;
        ZheXian element=null;
        List<ZheXian> data=new ArrayList<>();
        String date1= dto.getDate1();
        String date2=dto.getDate2();
        long value;
        HashMap<String,String> arr=new HashMap<String,String>();
        arr.put("chanxueyan","产学研");
        arr.put("competition","学科竞赛");
        arr.put("hengxiangkeyanxiangmu","横向科研项目");
        arr.put("honor","荣誉称号");
        arr.put("jiaoyanlunwen","教研论文");
        arr.put("jiaoyanxiangmu","教研项目");
        arr.put("jiaoyuguihuaxiangmu","教育规划项目");
        arr.put("keyanlunwen","科研论文");
        arr.put("keyanxiangmujiexiang","科研项目结项");
        arr.put("pingguzhongxinxiangguan","评估中心相关");
        arr.put("ruanjianzhuzuoquan","软件著作权");
        arr.put("zhuanli","专利");
        arr.put("zhuzuo","著作");
        arr.put("zongxiangkeyanxiangmu","纵向科研项目");
        for (Map.Entry<String,String> str:arr.entrySet()) {
            zheXian=new ZheXianEntity(str.getKey(), date1,date2);
            value=mapper.SearchAll(zheXian);
            element=new ZheXian(str.getValue(),value);
            data.add(element);
        }
        return data;
    }

    @Override
    public ResVO SearchDetail(StatisticalDTO dto) {
        return null;
    }

    @Override
    public List<TableData> selectChanXueYan(String date1, String date2) {
        List<ChanXueYan> list=chanXueYanMapper.selectChanXueYan(date1,date2);
        for (ChanXueYan b:list) {
            System.out.println(b.getId());
        }
        List<TableData> tableData=new ArrayList<>();
        for (ChanXueYan c:list) {
            System.out.println(c.getId()+"______________");
            tableData.add(new TableData(c,chanXueYanMapper.getChanXueYanDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<TableData> hengxiangkeyan(String date1, String date2) {
        List<HengXiangKeYanXiangMu> list=hengXiangKeYanMapper.selectHengXiangKeYan(date1,date2);
        List<TableData> tableData=new ArrayList<>();
        for (HengXiangKeYanXiangMu c:list) {
            tableData.add(new TableData(c,hengXiangKeYanMapper.getHengXiangKeYanDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<TableData> jiaoyanlunwen(String date1, String date2) {
        List<JiaoYanLunWen> list=jiaoYanLunWenMapper.selectJiaoYanLunWen(date1,date2);
        List<TableData> tableData=new ArrayList<>();
        for (JiaoYanLunWen c:list) {
            tableData.add(new TableData(c,jiaoYanLunWenMapper.getJiaoYanLunWenDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<TableData> jiaoyanxiangmu(String date1, String date2) {
        List<JiaoYanXiangMu> list=jiaoYanXiangMuMapper.selectJiaoYanXiangMu(date1,date2);
        List<TableData> tableData=new ArrayList<>();
        for (JiaoYanXiangMu c:list) {
            tableData.add(new TableData(c,jiaoYanXiangMuMapper.getJiaoYanDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<TableData> jiaoyuguihua(String date1, String date2) {
        List<JiaoYuGuiHuaXiangMu> list=jiaoYuGuiHuaMapper.selectJiaoYuGuiHua(date1,date2);
        List<TableData> tableData=new ArrayList<>();
        for (JiaoYuGuiHuaXiangMu c:list) {
            tableData.add(new TableData(c,jiaoYuGuiHuaMapper.getJiaoYuGuiHuaDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<TableData> keyanlunwen(String date1, String date2) {
        List<KeYanLunWen> list=keYanLunWenMapper.selectKeYanLunWen(date1,date2);
        List<TableData> tableData=new ArrayList<>();
        for (KeYanLunWen c:list) {
            tableData.add(new TableData(c,keYanLunWenMapper.getKeYanLunWenDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<TableData> keyanxiangmujiexiang(String date1, String date2) {
        List<KeYanXiangMuJieXiang> list=keYanXiangMuJieXiangMapper.selectKeYanXiangMuJieXiang(date1,date2);
        List<TableData> tableData=new ArrayList<>();
        for (KeYanXiangMuJieXiang c:list) {
            tableData.add(new TableData(c,keYanXiangMuJieXiangMapper.getKeYanXiangMuJieXiangDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<TableData> pingguzhongxin(String date1, String date2) {
        List<PingGuZhongXinXiangGuan> list=pingGuZhongXinMapper.selectPingGuZhongXin(date1,date2);
        List<TableData> tableData=new ArrayList<>();
        for (PingGuZhongXinXiangGuan c:list) {
            tableData.add(new TableData(c,pingGuZhongXinMapper.getPingGuZhongXinDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<TableData> rongyuchenghao(String date1, String date2) {
        List<Honor> list=rongYuChengHaoMapper.selectRongYuChengHao(date1,date2);
        List<TableData> tableData=new ArrayList<>();
        for (Honor c:list) {
            tableData.add(new TableData(c,rongYuChengHaoMapper.getRongYuDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<TableData> ruanjianzhuzuo(String date1, String date2) {
        List<RuanJianZhuZuoQuan> list=ruanJianZhuZuoMapper.selectRuanJianZhuZuo(date1,date2);
        List<TableData> tableData=new ArrayList<>();
        for (RuanJianZhuZuoQuan c:list) {
            tableData.add(new TableData(c,ruanJianZhuZuoMapper.getRuanJianZhuZuoDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<TableData> xuekejingsai(String date1, String date2) {
        List<Competition> list=xueKeJingSaiMapper.selectXueKeJingSai(date1,date2);
        List<TableData> tableData=new ArrayList<>();
        for (Competition c:list) {
            tableData.add(new TableData(c,xueKeJingSaiMapper.getJingSaiDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<TableData> zhuanli(String date1, String date2) {
        List<ZhuanLi> list=zhuanLiMapper.selectZhuanLi(date1,date2);
        List<TableData> tableData=new ArrayList<>();
        for (ZhuanLi c:list) {
            tableData.add(new TableData(c,zhuanLiMapper.getZhuanLiDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<TableData> zhuzuo(String date1, String date2) {
        List<ZhuZuo> list=zhuZuoMapper.selectZhuZuo(date1,date2);
        List<TableData> tableData=new ArrayList<>();
        for (ZhuZuo c:list) {
            tableData.add(new TableData(c,zhuZuoMapper.getZhuZuoDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<TableData> zongxiangkeyan(String date1, String date2) {
        List<ZongXiangKeYanXiangMu> list=zongXiangKeYanMapper.selectZongXiangKeYan(date1,date2);
        List<TableData> tableData=new ArrayList<>();
        for (ZongXiangKeYanXiangMu c:list) {
            tableData.add(new TableData(c,zongXiangKeYanMapper.getZongXiangKeYanDetail(c.getId())));
        }
        return tableData;
    }
}

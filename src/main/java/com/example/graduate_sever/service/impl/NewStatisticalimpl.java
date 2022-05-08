package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.*;
import com.example.graduate_sever.common.DTO.StatisticalDTO;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.ZheXianEntity;
import com.example.graduate_sever.model.*;
import com.example.graduate_sever.service.NewStatisticalService;
import com.example.graduate_sever.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("NewStatisticalService")
public class NewStatisticalimpl implements NewStatisticalService {
    @Autowired
    private NewStatisticalMapper mapper;
    @Autowired
    private NewSystemMapper newSystemMapper;
    @Autowired
    private RongYuChengHaoMapper rongYuChengHaoMapper;
    @Autowired
    private XueKeJingSaiMapper xueKeJingSaiMapper;

    @Override
    public List<TableData> selectxiaowaishijianjidi(String date1, String date2,Integer badge) {
        List<NewSyatemModel> list=mapper.selectOne(date1,date2,badge,"教务处-实践科_校外实践基地");
        List<TableData> tableData=new ArrayList<>();
        for (NewSyatemModel c:list) {
            System.out.println(c.getId()+"______________");
            tableData.add(new TableData(c,newSystemMapper.getNewSystemDetail(c.getId(),"教务处-实践科_校外实践基地")));
        }
        return tableData;
    }

    @Override
    public List<TableData> selectshijianlixiang(String date1, String date2,Integer badge) {
        List<NewSyatemModel> list=mapper.selectOne(date1,date2,badge,"教务处-实践科_立项");
        List<TableData> tableData=new ArrayList<>();
        for (NewSyatemModel c:list) {
            System.out.println(c.getId()+"______________");
            tableData.add(new TableData(c,newSystemMapper.getNewSystemDetail(c.getId(),"教务处-实践科_立项")));
        }
        return tableData;
    }

    @Override
    public List<TableData> selectshijianjiexiang(String date1, String date2,Integer badge) {
        List<NewSyatemModel> list=mapper.selectOne(date1,date2,badge,"教务处-实践科_结项");
        List<TableData> tableData=new ArrayList<>();
        for (NewSyatemModel c:list) {
            System.out.println(c.getId()+"______________");
            tableData.add(new TableData(c,newSystemMapper.getNewSystemDetail(c.getId(),"教务处-实践科_结项")));
        }
        return tableData;
    }

    @Override
    public List<TableData> selectjiaocaiyejidian(String date1, String date2,Integer badge) {
        List<NewSyatemModel> list=mapper.selectOne(date1,date2,badge,"教务处-教材科_教材业绩点");
        List<TableData> tableData=new ArrayList<>();
        for (NewSyatemModel c:list) {
            System.out.println(c.getId()+"______________");
            tableData.add(new TableData(c,newSystemMapper.getNewSystemDetail(c.getId(),"教务处-教材科_教材业绩点")));
        }
        return tableData;
    }

    @Override
    public List<TableData> selectjiaoyanyeji(String date1, String date2,Integer badge) {
        List<NewSyatemModel> list=mapper.selectOne(date1,date2,badge,"教务处-教研科_教研业绩");
        List<TableData> tableData=new ArrayList<>();
        for (NewSyatemModel c:list) {
            System.out.println(c.getId()+"______________");
            tableData.add(new TableData(c,newSystemMapper.getNewSystemDetail(c.getId(),"教务处-教研科_教研业绩")));
        }
        return tableData;
    }

    @Override
    public List<TableData> selectjiaoyanlunwen(String date1, String date2,Integer badge) {
        List<NewSyatemModel> list=mapper.selectOne(date1,date2,badge,"教务处-教研科_教研论文");
        List<TableData> tableData=new ArrayList<>();
        for (NewSyatemModel c:list) {
            System.out.println(c.getId()+"______________");
            tableData.add(new TableData(c,newSystemMapper.getNewSystemDetail(c.getId(),"教务处-教研科_教研论文")));
        }
        return tableData;
    }

    @Override
    public List<TableData> selectyouxiubishe(String date1, String date2,Integer badge) {
        List<NewSyatemModel> list=mapper.selectOne(date1,date2,badge,"教务处-评估中心_2020届本科优秀毕业设计（论文）指导教师");
        List<TableData> tableData=new ArrayList<>();
        for (NewSyatemModel c:list) {
            System.out.println(c.getId()+"______________");
            tableData.add(new TableData(c,newSystemMapper.getNewSystemDetail(c.getId(),"教务处-评估中心_2020届本科优秀毕业设计（论文）指导教师")));
        }
        return tableData;
    }

    @Override
    public List<TableData> selectzongxiangjieti(String date1, String date2,Integer badge) {
        List<SheKeChuModel> list=mapper.selectOneSheKeChu(date1,date2,badge,"社科处_3.纵向结题");
        List<TableData> tableData=new ArrayList<>();
        for (SheKeChuModel c:list) {
            System.out.println(c.getId()+"______________");
            tableData.add(new TableData(c,newSystemMapper.getNewSystemDetail(c.getId(),"社科处_3.纵向结题")));
        }
        return tableData;
    }

    @Override
    public List<TableData> selectkeyanhuojiang(String date1, String date2,Integer badge) {
        List<SheKeChuModel> list=mapper.selectOneSheKeChu(date1,date2,badge,"社科处_7.科研获奖");
        List<TableData> tableData=new ArrayList<>();
        for (SheKeChuModel c:list) {
            tableData.add(new TableData(c,newSystemMapper.getNewSystemDetail(c.getId(),"社科处_7.科研获奖")));
        }
        return tableData;
    }

    @Override
    public List<TableData> selectzhuanli(String date1, String date2, Integer badge) {
        List<NewSyatemModel> list=mapper.selectOneKeJiChu(date1,date2,badge,"科技处_专利");
        List<TableData> tableData=new ArrayList<>();
        for (NewSyatemModel c:list) {
            System.out.println(c.getId()+"______________");
            tableData.add(new TableData(c,newSystemMapper.getNewSystemDetail(c.getId(),"科技处_专利")));
        }
        return tableData;
    }

    @Override
    public List<TableData> selecthengxiangkeyan(String date1, String date2, Integer badge) {
        List<NewSyatemModel> list=mapper.selectOneKeJiChu(date1,date2,badge,"科技处_横向项目");
        List<TableData> tableData=new ArrayList<>();
        for (NewSyatemModel c:list) {
            System.out.println(c.getId()+"______________");
            tableData.add(new TableData(c,newSystemMapper.getNewSystemDetail(c.getId(),"科技处_横向项目")));
        }
        return tableData;
    }

    @Override
    public List<TableData> selectzongxiangkeyan(String date1, String date2, Integer badge) {
        List<NewSyatemModel> list=mapper.selectOneKeJiChu(date1,date2,badge,"科技处_纵向立项");
        List<TableData> tableData=new ArrayList<>();
        for (NewSyatemModel c:list) {
            System.out.println(c.getId()+"______________");
            tableData.add(new TableData(c,newSystemMapper.getNewSystemDetail(c.getId(),"科技处_纵向立项")));
        }
        return tableData;
    }

    @Override
    public List<TableData> selectzhuzuo(String date1, String date2, Integer badge) {
        List<NewSyatemModel> list=mapper.selectOneKeJiChu(date1,date2,badge,"科技处_著作");
        List<TableData> tableData=new ArrayList<>();
        for (NewSyatemModel c:list) {
            System.out.println(c.getId()+"______________");
            tableData.add(new TableData(c,newSystemMapper.getNewSystemDetail(c.getId(),"科技处_著作")));
        }
        return tableData;
    }

    @Override
    public List<TableData> selectkeyanlunwen(String date1, String date2, Integer badge) {
        List<NewSyatemModel> list=mapper.selectOneKeJiChu(date1,date2,badge,"科技处_论文");
        List<TableData> tableData=new ArrayList<>();
        for (NewSyatemModel c:list) {
            System.out.println(c.getId()+"______________");
            tableData.add(new TableData(c,newSystemMapper.getNewSystemDetail(c.getId(),"科技处_论文")));
        }
        return tableData;
    }

    @Override
    public List<TableData> selectruanjianzhuzuoquan(String date1, String date2, Integer badge) {
        List<NewSyatemModel> list=mapper.selectOneKeJiChu(date1,date2,badge,"科技处_软件著作权");
        List<TableData> tableData=new ArrayList<>();
        for (NewSyatemModel c:list) {
            System.out.println(c.getId()+"______________");
            tableData.add(new TableData(c,newSystemMapper.getNewSystemDetail(c.getId(),"科技处_软件著作权")));
        }
        return tableData;
    }

    @Override
    public List<TableData> selectkeyanxiangmujiexiang(String date1, String date2, Integer badge) {
        List<NewSyatemModel> list=mapper.selectOneKeJiChu(date1,date2,badge,"科技处_项目结项");
        List<TableData> tableData=new ArrayList<>();
        for (NewSyatemModel c:list) {
            System.out.println(c.getId()+"______________");
            tableData.add(new TableData(c,newSystemMapper.getNewSystemDetail(c.getId(),"科技处_项目结项")));
        }
        return tableData;
    }

    @Override
    public List<TableData> selectxuekejingsai(String date1, String date2, Integer badge) {
       String name=mapper.selectTeacherName(badge);
        List<Competition> list=xueKeJingSaiMapper.selectXueKeJingSai(date1,date2,name);
        List<TableData> tableData=new ArrayList<>();
        for (Competition c:list) {
            tableData.add(new TableData(c,null));
        }
        return tableData;
    }

    @Override
    public List<TableData> selectrongyuchenghao(String date1, String date2, Integer badge) {
        String name=mapper.selectTeacherName(badge);
        List<Honor> list=rongYuChengHaoMapper.selectRongYuChengHao(date1,date2,name);
        List<TableData> tableData=new ArrayList<>();
        for (Honor c:list) {
            tableData.add(new TableData(c,null));
        }
        return tableData;
    }


}

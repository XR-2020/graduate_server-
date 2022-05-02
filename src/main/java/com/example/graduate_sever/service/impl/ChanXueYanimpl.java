package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.*;
import com.example.graduate_sever.common.*;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.*;
import com.example.graduate_sever.service.ChanXueYanService;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.http.NameValuePair;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("ChanXueYanService")
public class ChanXueYanimpl implements ChanXueYanService {
    @Autowired
    private ChanXueYanMapper chanxueyanMapper;
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
    @Autowired
    private NewSystemMapper newSystemMapper;
    @Autowired
    private NewStatisticalMapper newStatisticalMapper;

    @Override
    public ResVO selectAll(DTO chanXueYanDTO) {
        List<List<Object>>data=chanxueyanMapper.getAll(chanXueYanDTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
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
    public JsonBean deleteChanXueYan(List<Integer> ids) {
        for (int id:ids) {
            chanxueyanMapper.deleteOneChanXueYan(id);
            chanxueyanMapper.deletePeople(id,1);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneChanXueYan(Integer id) {
        chanxueyanMapper.deleteOneChanXueYan(id);
        chanxueyanMapper.deletePeople(id,1);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getChanXueYanDetail(Integer id) {
        return new JsonBean(200,"",chanxueyanMapper.getChanXueYanDetail(id));
    }

    @Override
    public JsonBean insertChanXueYan(ChanXueYanEntity entity, Integer[] people) {
        chanxueyanMapper.insertChanXueYan(entity);
        for (Integer badge:people) {
            chanxueyanMapper.insertChanXueYanParticipation(new ParticipationEntity(badge,entity.getId(),1));
        }
        return new JsonBean(200,"","");
    }

    @Override
    public int editChanXueYan(Integer id, String name, String finishtime, String partment, String lianghua, Integer[] people, String wenhao) {
        int ref=chanxueyanMapper.editChanXueYan(id,name,finishtime,partment,lianghua,wenhao);
       if(ref==1){
           chanxueyanMapper.deletePeople(id,1);
           for (Integer p:people) {
               chanxueyanMapper.editPeople(id,p,1);
           }
       }
        return ref;
    }

    @Override
    public int editHeBing(Integer id, String tablename, String name, String finishtime, String partment, Integer[] people, Integer type) {
        int ref=chanxueyanMapper.editHeBing(id,tablename,name,finishtime,partment);
        if(ref==1){
//            chanxueyanMapper.deletePeople(id,type);
            for (Integer p:people) {
                chanxueyanMapper.editPeople(id,p,type);
            }
        }
        return ref;
    }

    @Override
    public DaiShenHeEditTableData selectProject(Integer id, String tablename) {
        Integer type=null;
        DaiShenHeEditTableData tableData=null;
        switch (tablename){
            case "honor":{
                type=14;
                tableData=new DaiShenHeEditTableData(rongYuChengHaoMapper.selectHonor(id),chanxueyanMapper.getBadge(id,type));
                break;
            }
            case "jiaoyanlunwen":{
                type=3;
                tableData=new DaiShenHeEditTableData(jiaoYanLunWenMapper.selectOneJiaoYanLunWen(id),chanxueyanMapper.getBadge(id,type));
                break;
            }
            case "jiaoyanxiangmu":{
                type=2;
                tableData=new DaiShenHeEditTableData(jiaoYanXiangMuMapper.selectOneJiaoYanXiangMu(id),chanxueyanMapper.getBadge(id,type));
                break;
            }
            case "jiaoyuguihuaxiangmu":{
                type=5;
                tableData=new DaiShenHeEditTableData(jiaoYuGuiHuaMapper.selectOneJiaoYuGuiHua(id),chanxueyanMapper.getBadge(id,type));
                break;
            }
            case "keyanlunwen":{
                type=10;
                tableData=new DaiShenHeEditTableData(keYanLunWenMapper.selectOneKeYanLunWen(id),chanxueyanMapper.getBadge(id,type));
                break;
            }
            case "keyanxiangmujiexiang":{
                type=12;
                tableData=new DaiShenHeEditTableData(keYanXiangMuJieXiangMapper.selectOneKeYanXiangMu(id),chanxueyanMapper.getBadge(id,type));
                break;
            }
            case "pingguzhongxinxiangguan":{
                type=4;
                tableData=new DaiShenHeEditTableData(pingGuZhongXinMapper.selectOnePingGuZhongXin(id),chanxueyanMapper.getBadge(id,type));
                break;
            }
            case "ruanjianzhuzuoquan":{
                type=11;
                tableData=new DaiShenHeEditTableData(ruanJianZhuZuoMapper.selectOneRuanJianZhuZuoQuan(id),chanxueyanMapper.getBadge(id,type));
                break;
            }
            case "zhuanli":{
                type=6;
                tableData=new DaiShenHeEditTableData(zhuanLiMapper.selectOneZhuanLi(id),chanxueyanMapper.getBadge(id,type));
                break;
            }
            case "zhuzuo":{
                type=9;
                tableData=new DaiShenHeEditTableData(zhuZuoMapper.selectOneZhuZuo(id),chanxueyanMapper.getBadge(id,type));
                break;
            }
            case "zongxiangkeyanxiangmu":{
                type=8;
                tableData=new DaiShenHeEditTableData(zongXiangKeYanMapper.selectOneZongXiangKeYan(id),chanxueyanMapper.getBadge(id,type));
                break;
            }
            case "hengxiangkeyanxiangmu":{
                type=7;
                tableData=new DaiShenHeEditTableData(hengXiangKeYanMapper.selectOneHengXiangKeYan(id),chanxueyanMapper.getBadge(id,type));
                break;
            }
            case "competition":{
                type=13;
                tableData=new DaiShenHeEditTableData(xueKeJingSaiMapper.selectOneCompetition(id),chanxueyanMapper.getBadge(id,type));
                break;
            }
            case "chanxueyan":{
                type=1;
                tableData=new DaiShenHeEditTableData(chanxueyanMapper.selectOneChanXueYan(id),chanxueyanMapper.getBadge(id,type));
                break;
            }
            case "newsystem":{
                NewSyatemModel newSyatemModel=newStatisticalMapper.selectOneNewSystem(id,tablename);
                tableData=new DaiShenHeEditTableData(newSyatemModel,newStatisticalMapper.getNewBadge(id,newSyatemModel.getType()));
            }
            default:{
                SheKeChuModel sheKeChuModel=newStatisticalMapper.selectDaiShenHeSheKeChu(id,tablename);
                tableData=new DaiShenHeEditTableData(sheKeChuModel,newStatisticalMapper.getNewBadge(id,sheKeChuModel.getType()));
            }
        }
        return tableData;
    }

    @Override
    public Teacher login(Integer username, String password) {
        return chanxueyanMapper.login(username,password);
    }

    @Override
    public JsonBean shenBaoChanXueYan(ChanXueYanEntity entity, Integer[] people) {
        int ref=chanxueyanMapper.shenBaoChanXueYan(entity);
        if(ref!=0){
            for (Integer badge:people) {
                chanxueyanMapper.insertChanXueYanParticipation(new ParticipationEntity(badge,entity.getId(),1));
            }
        }
        return new JsonBean(200,"",ref);
    }

    @Override
    public List<Object> getTeacherList() {
        return  chanxueyanMapper.getTeacherList();
    }

    @Override
    public void ChanXueYancrawlerWebSite(String td, CloseableHttpClient httpClient,HttpPost list,HttpPost view){
        List<NameValuePair> listparams= new ArrayList<NameValuePair>();
        String date=WebCookie.getDate();
        //设置请求地址的参数
        listparams.add(new BasicNameValuePair("tb",td));
        listparams.add(new BasicNameValuePair("pageNum","0"));
        listparams.add(new BasicNameValuePair("keyword",""));
        UrlEncodedFormEntity formEntity= null;
        try {
            formEntity = new UrlEncodedFormEntity(listparams,"utf-8");
            list.setEntity(formEntity);
            Document doc=Jsoup.parse(EntityUtils.toString(httpClient.execute(list).getEntity()));
            String[] ids=doc.getElementsByAttributeValue("fd","序号").text().split("\\s+");
            Elements name=doc.getElementsByAttributeValue("fd","项目名称");;
            String[] partment=doc.getElementsByAttributeValue("fd","所在部门").text().split("\\s+");
            String[] firstpeople=doc.getElementsByAttributeValue("fd","工号").text().split("\\s+");
            String[] lianghua=doc.getElementsByAttributeValue("fd","量化依据").text().split("\\s+");
            String[] wenhao=doc.getElementsByAttributeValue("fd","立项文号").text().split("\\s+");
            //设置除参与人外其他信息
            for(int i=0;i<ids.length;i++){
                ChanXueYanEntity chanXueYanEntity=new ChanXueYanEntity(1,date,lianghua[i],wenhao[i],name.get(i).text(),partment[i],Integer.parseInt(firstpeople[i]));
                int ref=chanxueyanMapper.insertChanXueYan(chanXueYanEntity);
                if(ref!=0){
                    //设置小眼睛参数
                    List<NameValuePair> viewparams= new ArrayList<NameValuePair>();
                    viewparams.add(new BasicNameValuePair("tb",td));
                    viewparams.add(new BasicNameValuePair("id",ids[i]));
                    UrlEncodedFormEntity viewformEntity = new UrlEncodedFormEntity(viewparams,"utf-8");
                    view.setEntity(viewformEntity);
                    //获取小眼睛内容
                    String[] people=Jsoup.parse(EntityUtils.toString(httpClient.execute(view).getEntity())).getElementById("memTab").text().split("\\s+");
                    for(int j=5;j<people.length;j+=4){
                        chanxueyanMapper.insertChanXueYanParticipation(new ParticipationEntity(Integer.parseInt(people[j]),chanXueYanEntity.getId(),1));
                    }
                    //添加第一完成人
                    chanxueyanMapper.insertChanXueYanParticipation(new ParticipationEntity(Integer.parseInt(firstpeople[i]),chanXueYanEntity.getId(),1));
                }else{
                    continue;
                }
               }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<TableData> waitingChanXueYan(DTO dTO) {
        List<ChanXueYan> list=chanxueyanMapper.waitingChanXueYan(dTO);
        for (ChanXueYan b:list) {
            System.out.println(b.getId());
        }
        List<TableData> tableData=new ArrayList<>();
        for (ChanXueYan c:list) {
            tableData.add(new TableData(c,chanxueyanMapper.getChanXueYanDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public long waitingPageTotal(Integer type) {
        long pageTotal=-1;
        switch (type){
            case 1:{
                pageTotal=chanxueyanMapper.waitingPageTotal("chanxueyan");
                break;
            }
            case 2:{
                pageTotal=chanxueyanMapper.waitingPageTotal("jiaoyanxiangmu");
                break;
            }
            case 3:{
                pageTotal=chanxueyanMapper.waitingPageTotal("jiaoyanlunwen");
                break;
            }
            case 4:{
                pageTotal=chanxueyanMapper.waitingPageTotal("pingguzhongxinxiangguan");
                break;
            }
            case 5:{
                pageTotal=chanxueyanMapper.waitingPageTotal("jiaoyuguihuaxiangmu");
                break;
            }
            case 6:{
                pageTotal=chanxueyanMapper.waitingPageTotal("zhuanli");
                break;
            }
            case 7:{
                pageTotal=chanxueyanMapper.waitingPageTotal("hengxiangkeyanxiangmu");
                break;
            }
            case 8:{
                pageTotal=chanxueyanMapper.waitingPageTotal("zongxiangkeyanxiangmu");
                break;
            }
            case 9:{
                pageTotal=chanxueyanMapper.waitingPageTotal("zhuzuo");
                break;
            }
            case 10:{
                pageTotal=chanxueyanMapper.waitingPageTotal("keyanlunwen");
                break;
            }
            case 11:{
                pageTotal=chanxueyanMapper.waitingPageTotal("ruanjianzhuzuoquan");
                break;
            }
            case 12:{
                pageTotal=chanxueyanMapper.waitingPageTotal("keyanxiangmujiexiang");
                break;
            }
            case 13:{
                pageTotal=chanxueyanMapper.waitingPageTotal("competition");
                break;
            }
            case 14:{
                pageTotal=chanxueyanMapper.waitingPageTotal("honor");
                break;
            }
        }
        return pageTotal;
    }

    @Override
    public Metails getchanxueyanMetails(Integer id) {
        return chanxueyanMapper.ChanXueYanmetails(id);
    }

    @Override
    public int passChanXueYan(Integer id, Integer ispass) {
        return chanxueyanMapper.passChanXueYan(id,ispass);
    }

    @Override
    public List<DaiShenHeTableData> getDisData(MyShenBaoDTO dto) {
        Integer badge=dto.getBadge();
        Integer pageIndex=dto.getPageIndex();
        Integer pageSize=dto.getPageSize();
        List<GetPageTotal> paramslist=new ArrayList<>();
        paramslist.add(new GetPageTotal("chanxueyan",1));
        paramslist.add(new GetPageTotal("jiaoyanxiangmu",2));
        paramslist.add(new GetPageTotal("jiaoyanlunwen",3));
        paramslist.add(new GetPageTotal("pingguzhongxinxiangguan",4));
        paramslist.add(new GetPageTotal("jiaoyuguihuaxiangmu",5));
        paramslist.add(new GetPageTotal("zhuanli",6));
        paramslist.add(new GetPageTotal("hengxiangkeyanxiangmu",7));
        paramslist.add(new GetPageTotal("zongxiangkeyanxiangmu",8));
        paramslist.add(new GetPageTotal("zhuzuo",9));
        paramslist.add(new GetPageTotal("keyanlunwen",10));
        paramslist.add(new GetPageTotal("ruanjianzhuzuoquan",11));
        paramslist.add(new GetPageTotal("keyanxiangmujiexiang",12));
        paramslist.add(new GetPageTotal("competition",13));
        paramslist.add(new GetPageTotal("honor",14));
        List<List<MyShenBaoModel>> list=new ArrayList<>();
        List<MyShenBaoModel> myShenBaoModelList=new ArrayList<>();
        List<DaiShenHeTableData> tableData=new ArrayList<>();
        List<NewGetPageTotal> paramslist2=new ArrayList<>();
        paramslist2.add(new NewGetPageTotal("newsystem",""));
        paramslist2.add(new NewGetPageTotal("shekechu",""));
        for (NewGetPageTotal g:paramslist2) {
            myShenBaoModelList=chanxueyanMapper.getDisData(g.getTablename(),badge);
            for (MyShenBaoModel m:myShenBaoModelList) {
                tableData.add(new DaiShenHeTableData(m,null,g.getTablename()));
            }
        }
        for (GetPageTotal g:paramslist) {
            myShenBaoModelList=chanxueyanMapper.getDisData(g.getTablename(),badge);
            for (MyShenBaoModel m:myShenBaoModelList) {
                tableData.add(new DaiShenHeTableData(m,chanxueyanMapper.getDetail(m.getId(),g.getType()),g.getTablename()));
            }
        }
        return tableData;
    }

    @Override
    public long getPageTotal(Integer badge) {
        int pageTotal=0;
        List<GetPageTotal> list=new ArrayList<>();
        list.add(new GetPageTotal("chanxueyan",1));
        list.add(new GetPageTotal("jiaoyanxiangmu",2));
        list.add(new GetPageTotal("jiaoyanlunwen",3));
        list.add(new GetPageTotal("pingguzhongxinxiangguan",4));
        list.add(new GetPageTotal("jiaoyuguihuaxiangmu",5));
        list.add(new GetPageTotal("zhuanli",6));
        list.add(new GetPageTotal("hengxiangkeyanxiangmu",7));
        list.add(new GetPageTotal("zongxiangkeyanxiangmu",8));
        list.add(new GetPageTotal("zhuzuo",9));
        list.add(new GetPageTotal("keyanlunwen",10));
        list.add(new GetPageTotal("ruanjianzhuzuoquan",11));
        list.add(new GetPageTotal("keyanxiangmujiexiang",12));
        list.add(new GetPageTotal("competition",13));
        list.add(new GetPageTotal("honor",14));
        List<NewGetPageTotal> paramslist2=new ArrayList<>();
        paramslist2.add(new NewGetPageTotal("newsystem",""));
        paramslist2.add(new NewGetPageTotal("shekechu",""));
        for (GetPageTotal g:list) {
            pageTotal+=chanxueyanMapper.getPageTotal(badge,g.getTablename());
        }
        for (NewGetPageTotal g:paramslist2) {
            pageTotal+=chanxueyanMapper.getPageTotal(badge,g.getTablename());
        }
        return pageTotal;
    }

    @Override
    public List<DaiShenHeTableData> getDaiShenHeData(MyShenBaoDTO dto) {
        Integer badge=dto.getBadge();
        Integer pageIndex=dto.getPageIndex();
        Integer pageSize=dto.getPageSize();
        List<GetPageTotal> paramslist=new ArrayList<>();
        paramslist.add(new GetPageTotal("chanxueyan",1));
        paramslist.add(new GetPageTotal("jiaoyanxiangmu",2));
        paramslist.add(new GetPageTotal("jiaoyanlunwen",3));
        paramslist.add(new GetPageTotal("pingguzhongxinxiangguan",4));
        paramslist.add(new GetPageTotal("jiaoyuguihuaxiangmu",5));
        paramslist.add(new GetPageTotal("zhuanli",6));
        paramslist.add(new GetPageTotal("hengxiangkeyanxiangmu",7));
        paramslist.add(new GetPageTotal("zongxiangkeyanxiangmu",8));
        paramslist.add(new GetPageTotal("zhuzuo",9));
        paramslist.add(new GetPageTotal("keyanlunwen",10));
        paramslist.add(new GetPageTotal("ruanjianzhuzuoquan",11));
        paramslist.add(new GetPageTotal("keyanxiangmujiexiang",12));
        paramslist.add(new GetPageTotal("competition",13));
        paramslist.add(new GetPageTotal("honor",14));
        List<NewGetPageTotal> paramslist2=new ArrayList<>();
        paramslist2.add(new NewGetPageTotal("newsystem",""));
        paramslist2.add(new NewGetPageTotal("shekechu",""));
        List<MyShenBaoModel> myShenBaoModelList;
        List<DaiShenHeTableData> tableData=new ArrayList<>();
        for (GetPageTotal g:paramslist) {
            myShenBaoModelList=chanxueyanMapper.getDaiShenHeData(g.getTablename(),badge);
            for (MyShenBaoModel m:myShenBaoModelList) {
                tableData.add(new DaiShenHeTableData(m,null,g.getTablename()));
            }
        }
        for (NewGetPageTotal g:paramslist2) {
            myShenBaoModelList=chanxueyanMapper.getDaiShenHeData(g.getTablename(),badge);
            for (MyShenBaoModel m:myShenBaoModelList) {
                tableData.add(new DaiShenHeTableData(m,newSystemMapper.getNewSystemDetail(m.getId(),g.getType()),g.getTablename()));
            }
        }
        return tableData;
    }

    @Override
    public long getDaiShenHePageTotal(Integer badge) {
        int pageTotal=0;
        List<GetPageTotal> list=new ArrayList<>();
        list.add(new GetPageTotal("chanxueyan",1));
        list.add(new GetPageTotal("jiaoyanxiangmu",2));
        list.add(new GetPageTotal("jiaoyanlunwen",3));
        list.add(new GetPageTotal("pingguzhongxinxiangguan",4));
        list.add(new GetPageTotal("jiaoyuguihuaxiangmu",5));
        list.add(new GetPageTotal("zhuanli",6));
        list.add(new GetPageTotal("hengxiangkeyanxiangmu",7));
        list.add(new GetPageTotal("zongxiangkeyanxiangmu",8));
        list.add(new GetPageTotal("zhuzuo",9));
        list.add(new GetPageTotal("keyanlunwen",10));
        list.add(new GetPageTotal("ruanjianzhuzuoquan",11));
        list.add(new GetPageTotal("keyanxiangmujiexiang",12));
        list.add(new GetPageTotal("competition",13));
        list.add(new GetPageTotal("honor",14));
        List<NewGetPageTotal> paramslist2=new ArrayList<>();
        paramslist2.add(new NewGetPageTotal("newsystem",""));
        paramslist2.add(new NewGetPageTotal("shekechu",""));
        for (NewGetPageTotal g:paramslist2) {
            pageTotal+=chanxueyanMapper.getDaiShenHePageTotal(badge,g.getTablename());
        }
        for (GetPageTotal g:list) {
            pageTotal+=chanxueyanMapper.getDaiShenHePageTotal(badge,g.getTablename());
        }

        return pageTotal;
    }

    @Override
    public int deleteMyShenBao(Integer id, String tablename) {
        Integer type=0;
        String newtype = null;
        switch (tablename){
            case "chanxueyan":{
                type=1;
                break;
            }
            case "jiaoyanxiangmu":{
                type=2;
                break;
            }
            case "jiaoyanlunwen":{
                type=3;
                break;
            }
            case "pingguzhongxinxiangguan":{
                type=4;
                break;
            }
            case "jiaoyuguihuaxiangmu":{
                type=5;
                break;
            }
            case "zhuanli":{
                type=6;
                break;
            }
            case "hengxiangkeyanxiangmu":{
                type=7;
                break;
            }
            case "zongxiangkeyanxiangmu":{
                type=8;
                break;
            }
            case "zhuzuo":{
                type=9;
                break;
            }
            case "keyanlunwen":{
                type=10;
                break;
            }
            case "ruanjianzhuzuoquan":{
                type=11;
                break;
            }
            case "keyanxiangmujiexiang":{
                type=12;
                break;
            }
            case "competition":{
                type=13;
                break;
            }
            case "honor":{
                type=14;
                break;
            }
            case "newsystem":{
                newtype=newStatisticalMapper.selectOneNewSystem(id,tablename).getType();
                break;
            }
            case "shekechu": {
                newtype=newStatisticalMapper.selectDaiShenHeSheKeChu(id,tablename).getType();
                break;
            }
        }
        System.out.println(newtype+"***********");
        int ref=chanxueyanMapper.deleteMyShenBao(id,tablename);
        if(ref==1){
               if(tablename.equals("newsystem")||tablename.equals("shekechu")){
                   newSystemMapper.deletePeople(id,newtype);
               }else{
                   chanxueyanMapper.deleteDaiShenHePartipation(id,type);
               }
        }
        return ref;
    }

    @Override
    public List<Integer> getChanXueYanDetailBadge(Integer id) {
        List<People> people=chanxueyanMapper.getChanXueYanDetail(id);
        List<Integer> badges=new ArrayList<>();
        for (People p:people) {
            badges.add(p.getBadge());
        }
        return badges;
    }

    @Override
    public List<DaiShenHeTableData> getHadPassData(MyShenBaoDTO dto) {
        Integer badge=dto.getBadge();
        Integer pageIndex=dto.getPageIndex();
        Integer pageSize=dto.getPageSize();
        List<GetPageTotal> paramslist=new ArrayList<>();
        paramslist.add(new GetPageTotal("chanxueyan",1));
        paramslist.add(new GetPageTotal("jiaoyanxiangmu",2));
        paramslist.add(new GetPageTotal("jiaoyanlunwen",3));
        paramslist.add(new GetPageTotal("pingguzhongxinxiangguan",4));
        paramslist.add(new GetPageTotal("jiaoyuguihuaxiangmu",5));
        paramslist.add(new GetPageTotal("zhuanli",6));
        paramslist.add(new GetPageTotal("hengxiangkeyanxiangmu",7));
        paramslist.add(new GetPageTotal("zongxiangkeyanxiangmu",8));
        paramslist.add(new GetPageTotal("zhuzuo",9));
        paramslist.add(new GetPageTotal("keyanlunwen",10));
        paramslist.add(new GetPageTotal("ruanjianzhuzuoquan",11));
        paramslist.add(new GetPageTotal("keyanxiangmujiexiang",12));
        paramslist.add(new GetPageTotal("competition",13));
        paramslist.add(new GetPageTotal("honor",14));
        List<MyShenBaoModel> myShenBaoModelList=new ArrayList<>();
        List<DaiShenHeTableData> tableData=new ArrayList<>();
        List<NewGetPageTotal> paramslist2=new ArrayList<>();
        paramslist2.add(new NewGetPageTotal("newsystem",""));
        paramslist2.add(new NewGetPageTotal("shekechu",""));
        for (NewGetPageTotal g:paramslist2) {
            myShenBaoModelList=chanxueyanMapper.getHadPassData(g.getTablename(),badge);
            for (MyShenBaoModel m:myShenBaoModelList) {
                tableData.add(new DaiShenHeTableData(m,null,g.getTablename()));
            }
        }
        for (GetPageTotal g:paramslist) {
            myShenBaoModelList=chanxueyanMapper.getHadPassData(g.getTablename(),badge);
            for (MyShenBaoModel m:myShenBaoModelList) {
                tableData.add(new DaiShenHeTableData(m,chanxueyanMapper.getDetail(m.getId(),g.getType()),g.getTablename()));
            }
        }
        return tableData;
    }

    @Override
    public long getHadPassPageTotal(Integer badge) {
        int pageTotal=0;
        List<GetPageTotal> list=new ArrayList<>();
        list.add(new GetPageTotal("chanxueyan",1));
        list.add(new GetPageTotal("jiaoyanxiangmu",2));
        list.add(new GetPageTotal("jiaoyanlunwen",3));
        list.add(new GetPageTotal("pingguzhongxinxiangguan",4));
        list.add(new GetPageTotal("jiaoyuguihuaxiangmu",5));
        list.add(new GetPageTotal("zhuanli",6));
        list.add(new GetPageTotal("hengxiangkeyanxiangmu",7));
        list.add(new GetPageTotal("zongxiangkeyanxiangmu",8));
        list.add(new GetPageTotal("zhuzuo",9));
        list.add(new GetPageTotal("keyanlunwen",10));
        list.add(new GetPageTotal("ruanjianzhuzuoquan",11));
        list.add(new GetPageTotal("keyanxiangmujiexiang",12));
        list.add(new GetPageTotal("competition",13));
        list.add(new GetPageTotal("honor",14));
        List<NewGetPageTotal> paramslist2=new ArrayList<>();
        paramslist2.add(new NewGetPageTotal("newsystem",""));
        paramslist2.add(new NewGetPageTotal("shekechu",""));
        for (NewGetPageTotal g:paramslist2) {
            pageTotal+=chanxueyanMapper.getHadPassPageTotal(badge,g.getTablename());
        }
        for (GetPageTotal g:list) {
            pageTotal+=chanxueyanMapper.getHadPassPageTotal(badge,g.getTablename());
        }
        return pageTotal;
    }
}

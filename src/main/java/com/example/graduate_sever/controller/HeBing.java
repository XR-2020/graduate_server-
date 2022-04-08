package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.ChanXueYanUO;
import com.example.graduate_sever.common.UO.HeBingUO;
import com.example.graduate_sever.common.WebCookie;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.service.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HeBing {
    @Autowired
    private ZongXiangKeYanService zongXiangKeYanService;
    @Autowired
    private PingGuZhongXinService pingGuZhongXinService;
    @Autowired
    private JiaoYanXiangMuService jiaoYanXiangMuService;
    @Autowired
    private JiaoYanLunWenMuService jiaoYanLunWenMuService;
    @Autowired
    private KeYanLunWenService keYanLunWenService;
    @Autowired
    private JiaoYuGuiHuaService jiaoYuGuiHuaService;
    @Autowired
    private ChanXueYanService chanXueYanService;
    @Autowired
    private ZhuanLiService zhuanLiService;
    @Autowired
    private HengXiangKeYanService hengXiangKeYanService;
    @Autowired
    private ZhuZuoService zhuZuoService;
    @Autowired
    private RuanJianZhuZuoService ruanJianZhuZuoService;
    @Autowired
    private KeYanXiangMuJieXiangService keYanXiangMuJieXiangService;
    @RequestMapping(value = "/updateHeBing", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean updateHeBing(@RequestBody HeBingUO uo){
        Integer role=uo.getRole();
        HeBingEntity element=null;
        JsonBean jsonBean=null;
        Integer[] people=uo.getPeople();
        if (role!=3&&role!=4) {
            element = new HeBingEntity(0, uo.getFinishtime(), uo.getPartment(), uo.getName(), uo.getShenbao());
        }else {
            element = new HeBingEntity(1, uo.getFinishtime(), uo.getPartment(), uo.getName(),uo.getShenbao());
        }
        switch (uo.getType()){
           //专利
           case 2:{
               jsonBean=zhuanLiService.shenBaoZhuanLi(element,people);
               break;
           }
           //横向科研项目
           case 3:{
               jsonBean=hengXiangKeYanService.shenBaoHengXiangKeYan(element,people);
               break;
           }
           //著作
           case 4:{
               jsonBean=zhuZuoService.shenBaoZhuZuo(element,people);
               break;
           }
           //科研论文
           case 5:{
               jsonBean=keYanLunWenService.shenBaoKeYanLunWen(element,people);
               break;
           }
           //软件著作权
           case 6:{
               jsonBean=ruanJianZhuZuoService.shenBaoRuanJianZhuZuo(element,people);
               break;
           }
           //科研项目结项
           case 7:{
               jsonBean=keYanXiangMuJieXiangService.shenBaoKeYanXiangMuJieXiang(element,people);
               break;
           }
       }
        return jsonBean;
    }
    @GetMapping("/crawlerWebSite")
    public String crawlerWebSite(String crawlertd){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String cookie= WebCookie.getCookie();
        //获取对应项目列表
        HttpPost list=new HttpPost("http://jx.zut.edu.cn/list.jsp");
        list.setHeader("Cookie",cookie);
        //获取小眼睛
        HttpPost view=new HttpPost("http://jx.zut.edu.cn/view.jsp");
        view.setHeader("Cookie",cookie);
        switch (crawlertd){
            case "教务处-实践科_产学研立项":{
                chanXueYanService.ChanXueYancrawlerWebSite(crawlertd,httpClient,list,view);
                break;
            }
            case "科技处_著作":{
                zhuZuoService.ZhuZuoCrawlerWebSite(crawlertd,httpClient,list,view);
                break;
            }
            case "社科处_教育规划项目纵向结题":{
                jiaoYuGuiHuaService.JiaoYuGuiHuaCrawlerWebSite(crawlertd,httpClient,list,view);
                break;
            }
            case "科技处_横向科研项目业绩表":{
                hengXiangKeYanService.HengXiangKeYanCrawlerWebSite(crawlertd,httpClient,list,view);
                break;
            }
            case "科技处_纵向业绩量化汇总":{
                zongXiangKeYanService.ZongXiangKeYanCrawlerWebSite(crawlertd,httpClient,list,view);
                break;
            }
            case "科技处_论文":{
                keYanLunWenService.KeYanLunWenCrawlerWebSite(crawlertd,httpClient,list,view);
                break;
            }
            case "科技处_专利":{
                zhuanLiService.ZhuanLiCrawlerWebSite(crawlertd,httpClient,list,view);
                break;
            }
            case "科技处_项目结项":{
                keYanXiangMuJieXiangService.KeYanXiangMuJieXiangCrawlerWebSite(crawlertd,httpClient,list,view);
                break;
            }
            case "科技处_软件著作权":{
                ruanJianZhuZuoService.RuanJianZhuZuoCrawlerWebSite(crawlertd,httpClient,list,view);
                break;
            }
            case "教务处-教研科_教研论文":{
                jiaoYanLunWenMuService.JiaoYanLunWenCrawlerWebSite(crawlertd,httpClient,list,view);
                break;
            }
            case "教务处-教研科_教研":{
                jiaoYanXiangMuService.JiaoYanXiangMuCrawlerWebSite(crawlertd,httpClient,list,view);
                break;
            }
            case "教务处-评估中心_模板":{
                pingGuZhongXinService.PingGuZhongXinCrawlerWebSite(crawlertd,httpClient,list,view);
                break;
            }
        }
        return "OK";
    }

}

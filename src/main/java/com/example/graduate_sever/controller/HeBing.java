package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.EditUO.EditHeBingUO;
import com.example.graduate_sever.common.MyDaiShenBaoRes;
import com.example.graduate_sever.common.*;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.common.UO.ChanXueYanUO;
import com.example.graduate_sever.common.UO.HeBingUO;
import com.example.graduate_sever.entity.HeBingEntity;
import com.example.graduate_sever.service.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    private RongYuChengHaoService rongYuChengHaoService;
    @Autowired
    private XueKeJingSaiService xueKeJingSaiService;
    @RequestMapping(value = "/updateHeBing", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean updateHeBing(@RequestBody HeBingUO uo) throws IOException {
        Integer role=uo.getRole();
        HeBingEntity element=null;
        JsonBean jsonBean=null;
        Integer[] people=uo.getPeople();
        //读取证明材料
        File file=new File(uo.getPath());
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
        BufferedInputStream bin = null;
        try {
            bin = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            while (bin.read(buffer) > 0) {
                bos.write(buffer);
            }
        } finally {
            bin.close();
            bos.close();
        }
        if (role!=3&&role!=4) {
            element = new HeBingEntity(0, uo.getFinishtime(), uo.getPartment(), uo.getName(), uo.getShenbao(),bos.toByteArray());
        }else {
            element = new HeBingEntity(1, uo.getFinishtime(), uo.getPartment(), uo.getName(),uo.getShenbao(),bos.toByteArray());
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
    @RequestMapping(value = "/HeBingMetials")
    public String HeBingMetials(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file.getBytes().length);
        // 将文件保存在服务器目录中
        // 新生成的文件名称
        String uuid = UUID.randomUUID().toString();
        // 得到上传文件后缀
        String originalName = file.getOriginalFilename();
        String ext = "." + FilenameUtils.getExtension(originalName);
        // 新生成的文件名称
        String fileName = uuid + ext;
        String filepath="E:\\graduate_sever\\metails\\"+fileName;
        // 得到新的文件File对象
        File targetFile = new File(filepath);
        // 开始复制文件
        FileUtils.writeByteArrayToFile(targetFile, file.getBytes());
        return filepath;
    }
    @GetMapping("/getAllWaiting")
    public WaitingRes getAllWaiting(DTO dTO){

        WaitingRes allData=new WaitingRes(
                chanXueYanService.waitingChanXueYan(dTO),chanXueYanService.waitingPageTotal(1),
                hengXiangKeYanService.waitinghengxiangkeyan(dTO),chanXueYanService.waitingPageTotal(7),
                jiaoYanLunWenMuService.waitingjiaoyanlunwen(dTO),chanXueYanService.waitingPageTotal(3),
                jiaoYanXiangMuService.waitingjiaoyanxiangmu(dTO),chanXueYanService.waitingPageTotal(2),
                jiaoYuGuiHuaService.waitingjiaoyuguihua(dTO),chanXueYanService.waitingPageTotal(5),
                keYanLunWenService.waitingkeyanlunwen(dTO),chanXueYanService.waitingPageTotal(10),
                keYanXiangMuJieXiangService.waitingkeyanxiangmujiexiang(dTO),chanXueYanService.waitingPageTotal(12),
                pingGuZhongXinService.waitingpingguzhongxin(dTO),chanXueYanService.waitingPageTotal(4),
                rongYuChengHaoService.waitingrongyuchenghao(dTO),chanXueYanService.waitingPageTotal(14),
                ruanJianZhuZuoService.waitingruanjianzhuzuo(dTO),chanXueYanService.waitingPageTotal(11),
                xueKeJingSaiService.waitingxuekejingsai(dTO),chanXueYanService.waitingPageTotal(13),
                zhuanLiService.waitingzhuanli(dTO),chanXueYanService.waitingPageTotal(6),
                zhuZuoService.waitingzhuzuo(dTO),chanXueYanService.waitingPageTotal(9),
                zongXiangKeYanService.waitingzongxiangkeyan(dTO),chanXueYanService.waitingPageTotal(8));
        return allData;
    }

    @GetMapping("/getDisData")
    public MyDaiShenBaoRes getDisData(MyShenBaoDTO dto){
        Integer pageIndex=dto.getPageIndex()-1;
        Integer pageEnd=pageIndex+dto.getPageSize();
        List<DaiShenHeTableData> list=chanXueYanService.getDisData(dto);
        long pageTotal=chanXueYanService.getPageTotal(dto.getBadge());
        return new MyDaiShenBaoRes(list.subList(pageIndex,pageEnd),pageTotal);
    }

    @GetMapping("/getDaiShenHeData")
    public MyDaiShenBaoRes getDaiShenHeData(MyShenBaoDTO dto){
        Integer pageIndex=dto.getPageIndex()-1;
        Integer pageEnd=pageIndex+dto.getPageSize();
        List<DaiShenHeTableData> list=chanXueYanService.getDaiShenHeData(dto);
        long pageTotal=chanXueYanService.getDaiShenHePageTotal(dto.getBadge());
        return new MyDaiShenBaoRes(list.subList(pageIndex,pageEnd),pageTotal);
    }
    @GetMapping("/getHadPassData")
    public MyDaiShenBaoRes getHadPassData(MyShenBaoDTO dto){
        Integer pageIndex=dto.getPageIndex()-1;
        Integer pageEnd=pageIndex+dto.getPageSize();
        List<DaiShenHeTableData> list=chanXueYanService.getHadPassData(dto);
        long pageTotal=chanXueYanService.getHadPassPageTotal(dto.getBadge());
        return new MyDaiShenBaoRes(list.subList(pageIndex,pageEnd),pageTotal);
    }
    @RequestMapping(value = "/deleteMyShenBao", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int deleteMyShenBao(Integer id,String tablename){
        return chanXueYanService.deleteMyShenBao(id,tablename);
    }

    @RequestMapping(value = "/editHeBing", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean editHeBing(@RequestBody EditHeBingUO uo) throws Exception {
        String tablename = null;
        switch (uo.getType()){
            //专利
            case 2:{
                tablename="zhuanli";
                break;
            }
            //横向科研项目
            case 3:{
                tablename="hengxiangkeyanxiangmu";
                break;
            }
            //著作
            case 4:{
                tablename="zhuzuo";
                break;
            }
            //科研论文
            case 5:{
                tablename="keyanlunwen";
                break;
            }
            //软件著作权
            case 6:{
                tablename="ruanjianzhuzuoquan";
                break;
            }
            //科研项目结项
            case 7:{
                tablename="keyanxiangmujiexiang";
                break;
            }
        }
        return  new JsonBean(200,"",chanXueYanService.editHeBing(uo.getId(),tablename,uo.getName(),uo.getFinishtime(),uo.getPartment(),uo.getPeople(),uo.getType()));
    }
}

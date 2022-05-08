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
    private NewSystemCrawlerService newSystemCrawlerService;
    @Autowired
    private SheKeChuService sheKeChuService;
    @Autowired
    private KeJiChuService keJiChuService;
    @Autowired
    private XueKeJingSaiService xueKeJingSaiService;
    @Autowired
    private RongYuChengHaoService rongYuChengHaoService;
    @Autowired
    private ChanXueYanService chanXueYanService;

    @GetMapping("/getAllWaiting")
    public WaitingRes getAllWaiting(DTO dTO){
        WaitingRes allData=new WaitingRes(
                keJiChuService.waitingKeJiChu(dTO,"科技处_横向项目"),keJiChuService.waitingKeJiChuPageToTal("科技处_横向项目"),
                keJiChuService.waitingKeJiChu(dTO,"科技处_论文"),keJiChuService.waitingKeJiChuPageToTal("科技处_论文"),
                keJiChuService.waitingKeJiChu(dTO,"科技处_项目结项"),keJiChuService.waitingKeJiChuPageToTal("科技处_项目结项"),
                rongYuChengHaoService.waitingrongyuchenghao(dTO),rongYuChengHaoService.waitingrongyuchenghaoPageToTal(),
                keJiChuService.waitingKeJiChu(dTO,"科技处_软件著作权"),keJiChuService.waitingKeJiChuPageToTal("科技处_软件著作权"),
                xueKeJingSaiService.waitingxuekejingsai(dTO),xueKeJingSaiService.waitingxuekejingsaiPageToTal(),
                keJiChuService.waitingKeJiChu(dTO,"科技处_专利"),keJiChuService.waitingKeJiChuPageToTal("科技处_专利"),
                keJiChuService.waitingKeJiChu(dTO,"科技处_著作"),keJiChuService.waitingKeJiChuPageToTal("科技处_著作"),
                keJiChuService.waitingKeJiChu(dTO,"科技处_纵向立项"),keJiChuService.waitingKeJiChuPageToTal("科技处_纵向立项"),
                newSystemCrawlerService.waitingnewSystem(dTO,"教务处-实践科_校外实践基地"),newSystemCrawlerService.waitingnewSystemPageToTal("教务处-实践科_校外实践基地"),
                newSystemCrawlerService.waitingnewSystem(dTO,"教务处-实践科_立项"),newSystemCrawlerService.waitingnewSystemPageToTal("教务处-实践科_立项"),
                newSystemCrawlerService.waitingnewSystem(dTO,"教务处-实践科_结项"),newSystemCrawlerService.waitingnewSystemPageToTal("教务处-实践科_结项"),
                newSystemCrawlerService.waitingnewSystem(dTO,"教务处-教材科_教材业绩点"),newSystemCrawlerService.waitingnewSystemPageToTal("教务处-教材科_教材业绩点"),
                newSystemCrawlerService.waitingnewSystem(dTO,"教务处-教研科_教研业绩"),newSystemCrawlerService.waitingnewSystemPageToTal("教务处-教研科_教研业绩"),
                newSystemCrawlerService.waitingnewSystem(dTO,"教务处-教研科_教研论文"),newSystemCrawlerService.waitingnewSystemPageToTal("教务处-教研科_教研论文"),
                newSystemCrawlerService.waitingnewSystem(dTO,"教务处-评估中心_2020届本科优秀毕业设计（论文）指导教师"),newSystemCrawlerService.waitingnewSystemPageToTal("教务处-评估中心_2020届本科优秀毕业设计（论文）指导教师"),
                sheKeChuService.waitingSheKeChu(dTO,"社科处_3.纵向结题"),sheKeChuService.waitingSheKeChuPageToTal("社科处_3.纵向结题"),
                sheKeChuService.waitingSheKeChu(dTO,"社科处_7.科研获奖"),sheKeChuService.waitingSheKeChuPageToTal("社科处_7.科研获奖"));
        return allData;
    }

    @GetMapping("/getDisData")
    public MyDaiShenBaoRes getDisData(MyShenBaoDTO dto){
        Integer pageIndex=(dto.getPageIndex()-1)*dto.getPageSize();
        int pageEnd=pageIndex+dto.getPageSize();
        List<DaiShenHeTableData> list=chanXueYanService.getDisData(dto);
        long pageTotal=chanXueYanService.getPageTotal(dto.getBadge());
        if(pageTotal<pageEnd){
            pageEnd=(int)pageTotal;
        }
        System.out.println(pageIndex+"_pageIndex___________"+pageEnd+"************************pageEnd*******************"+pageTotal);
        return new MyDaiShenBaoRes(list.subList(pageIndex,pageEnd),pageTotal);
    }

    @GetMapping("/getDaiShenHeData")
    public MyDaiShenBaoRes getDaiShenHeData(MyShenBaoDTO dto){
        Integer pageIndex=(dto.getPageIndex()-1)*dto.getPageSize();
        int pageEnd=pageIndex+dto.getPageSize();
        List<DaiShenHeTableData> list=chanXueYanService.getDaiShenHeData(dto);
        long pageTotal=chanXueYanService.getDaiShenHePageTotal(dto.getBadge());
        if(pageTotal<pageEnd){
            pageEnd=(int)pageTotal;
        }
        return new MyDaiShenBaoRes(list.subList(pageIndex,pageEnd),pageTotal);
    }
    @GetMapping("/getHadPassData")
    public MyDaiShenBaoRes getHadPassData(MyShenBaoDTO dto){
        Integer pageIndex=(dto.getPageIndex()-1)*dto.getPageSize();
        int pageEnd=pageIndex+dto.getPageSize();
        List<DaiShenHeTableData> list=chanXueYanService.getHadPassData(dto);
        long pageTotal=chanXueYanService.getHadPassPageTotal(dto.getBadge());
        if(pageTotal<pageEnd){
            pageEnd=(int)pageTotal;
        }
        return new MyDaiShenBaoRes(list.subList(pageIndex,pageEnd),pageTotal);
    }
    @RequestMapping(value = "/deleteMyShenBao", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int deleteMyShenBao(Integer id,String tablename){
        return chanXueYanService.deleteMyShenBao(id,tablename);
    }

}

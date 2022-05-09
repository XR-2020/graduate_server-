package com.example.graduate_sever.controller;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.EditUO.EditNewSystemUO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.JiaoWuChuUO;
import com.example.graduate_sever.common.WebCookie;
import com.example.graduate_sever.service.NewSystemCrawlerService;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class JiaoWuChu {
    @Autowired
    private NewSystemCrawlerService newSystemCrawlerService;

    @GetMapping("/NewSystemCrawlerWebSite")
    public String crawlerWebSite(String crawlertd) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String cookie = WebCookie.getCookie();
        //获取对应项目列表
        HttpPost list = new HttpPost("http://jx.zut.edu.cn/list.jsp");
        list.setHeader("Cookie", cookie);
        //获取小眼睛
        HttpPost view = new HttpPost("http://jx.zut.edu.cn/view.jsp");
        view.setHeader("Cookie", cookie);
        newSystemCrawlerService.newSystemCrawlerWebSite(crawlertd,httpClient,list,view);
        return "爬取完成";
    }
    @GetMapping("/CrawlerTypeList")
    public List<String> CrawlerTypeList(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<String> list=null;
        try {
            list=newSystemCrawlerService.typeList(httpClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @GetMapping("/getAllNewSystem")
    public ResVO getAllNewSystem(DTO chanXueYanDTO){
        return newSystemCrawlerService.getAllNewSystem(chanXueYanDTO);
    }

    @GetMapping("/getSearchNewSystem")
    public ResVO getSearchNewSystem(DTO chanXueYanDTO){
        return newSystemCrawlerService.getSearchNewSystem(chanXueYanDTO);}

    @GetMapping("/getNewSystemDetail")
    public JsonBean getNewSystemDetail(Integer id,String type){
        return newSystemCrawlerService.getNewSystemDetail(id,type);}

    @GetMapping("/getNewSystemBadge")
    public JsonBean getNewSystemBadge(Integer id,String type){
        return new JsonBean(200,"",newSystemCrawlerService.getNewSystemBadge(id,type));}

    @RequestMapping(value = "/editNewSystem", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean editNewSystem(@RequestBody EditNewSystemUO uo) throws Exception {
        return  new JsonBean(200,"",newSystemCrawlerService.editNewSystem(uo.getId(),uo.getName(),uo.getFinishtime(),uo.getPartment(),uo.getPeople(),uo.getType()));
    }


    @RequestMapping(value = "/deleteNewSystem", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean deleteNewSystem(@RequestParam(value = "ids") List<Integer> ids){
        for (Integer i:ids) {
            System.out.println(i);
        }
        return  newSystemCrawlerService.deleteNewSystem(ids);}

    @PostMapping("/deleteOneNewSystem")
    public  JsonBean deleteOneNewSystem(Integer id){return newSystemCrawlerService.deleteOneNewSystem(id);}

    @RequestMapping(value = "/insertJiaoWuChu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean insertJiaoWuChu(@RequestBody JiaoWuChuUO uo) throws Exception {
        return  newSystemCrawlerService.shenBaoSheKeChu(uo);
    }

    @GetMapping("/getJiaoWuChuMetails")
    public byte[] getJiaoWuChuMetails(Integer id){
        return newSystemCrawlerService.getJiaoWuChuMetails(id).getMetails();
    }

    @RequestMapping(value = "/passJiaoWuChu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int passJiaoWuChu(Integer id,Integer ispass){
        return newSystemCrawlerService.passJiaoWuChu(id,ispass);
    }
}

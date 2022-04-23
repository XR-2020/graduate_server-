package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.NewSystemMapper;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.People;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.WebCookie;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.NewParticipationEntity;
import com.example.graduate_sever.entity.NewSystemEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.service.NewSystemCrawlerService;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service("NewSystemCrawlerService")
public class NewSystemClawerimpl implements NewSystemCrawlerService {
    @Autowired
    private NewSystemMapper newSystemMapper;

    @Override
    public List<String> typeList(CloseableHttpClient httpClient) throws IOException {
        Document doc=null;
        String content=null;
        Elements elements=null;
        String[] list;
        List<String> typelist=new ArrayList<>();
        //创建httpClient请求对象，设置url地址
        HttpGet httpGet = new HttpGet("http://jx.zut.edu.cn/index.jsp");
        httpGet.setHeader("Cookie", WebCookie.getCookie());
        CloseableHttpResponse response = null;
        response=httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
                try {
                    content = EntityUtils.toString(response.getEntity(), "utf8");
                    doc= Jsoup.parse(content);
                    elements=doc.getElementsByTag("li");
                    list=elements.text().split("\\s+");
                    for (String s:list) {
                        typelist.add(s);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
//        //列表滤重
//        Set set = new HashSet();
//        List newList = new ArrayList();
//        for (Iterator iter = typelist.iterator(); iter.hasNext();) {
//            Object element = iter.next();
//            if (set.add(element))
//                newList.add(element);
//        }
//        typelist.clear();
//        typelist.addAll(newList);
        return typelist;
    }

    @Override
    public void newSystemCrawlerWebSite(String td, CloseableHttpClient httpClient, HttpPost list, HttpPost view) {
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
            Elements name=doc.getElementsByAttributeValue("fd","名称");;
            Elements partment=doc.getElementsByAttributeValue("fd","部门");
            Elements firstpeople=doc.getElementsByAttributeValue("fd","工号");
            Elements finishtime=doc.getElementsByAttributeValue("fd","获奖/获准/按期验收时间");
            //设置除参与人外其他信息
            for(int i=0;i<ids.length;i++){
                NewSystemEntity newSystemEntity=new NewSystemEntity(1,finishtime.get(i).text(),partment.get(i).text(),name.get(i).text(),Integer.parseInt(firstpeople.get(i).text()),td);
                int ref=newSystemMapper.insertNewSystem(newSystemEntity);
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
                        newSystemMapper.insertNewSystemParticipation(new NewParticipationEntity(Integer.parseInt(people[j]),newSystemEntity.getId(),td));
                    }
                    //添加第一完成人
                    newSystemMapper.insertNewSystemParticipation(new NewParticipationEntity(Integer.parseInt(firstpeople.get(i).text()),newSystemEntity.getId(),td));
                }else{
                    continue;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ResVO getAllNewSystem(DTO chanXueYanDTO) {
        List<List<Object>>data=newSystemMapper.getAllNewSystem(chanXueYanDTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        return new ResVO(list,total);
    }

    @Override
    public ResVO getSearchNewSystem(DTO chanXueYanDTO) {
        List<List<Object>>data=newSystemMapper.getSearchNewSystem(chanXueYanDTO);
        List<Object> list=data.get(0);
        long total= (long)data.get(1).get(0);
        return new ResVO(list,total);
    }

    @Override
    public JsonBean getNewSystemDetail(Integer id, String type) {
        return new JsonBean(200,"",newSystemMapper.getNewSystemDetail(id,type));
    }

    @Override
    public List<Integer> getNewSystemBadge(Integer id,String type) {
        List<People> people=newSystemMapper.getNewSystemDetail(id,type);
        List<Integer> badges=new ArrayList<>();
        for (People p:people) {
            badges.add(p.getBadge());
        }
        return badges;
    }

    @Override
    public int editNewSystem(Integer id, String name, String finishtime, String partment, Integer[] people,String type) {
        int ref=newSystemMapper.editNewSystem(id,name,finishtime,partment);
        if(ref==1){
            newSystemMapper.deletePeople(id);
            for (Integer p:people) {
                newSystemMapper.editNewSystemPeople(id,p,type);
            }
        }
        return ref;
    }

    @Override
    public JsonBean deleteNewSystem(List<Integer> ids) {
        for (int id:ids) {
            newSystemMapper.deleteOneNewSystem(id);
            newSystemMapper.deletePeople(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneNewSystem(Integer id) {
        newSystemMapper.deleteOneNewSystem(id);
        newSystemMapper.deletePeople(id);
        return new JsonBean(200,"","");
    }
}

package com.example.graduate_sever.controller;


import com.example.graduate_sever.GraduateSeverApplication;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.EditUO.EditChanXueYanUO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.ChanXueYanUO;
import com.example.graduate_sever.common.WebCookie;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.model.Teacher;
import com.example.graduate_sever.service.ChanXueYanService;
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
public class ChanXueYan {
    @Autowired
    private ChanXueYanService chanXueYanService;

    @GetMapping("/getAllChanXueYan")
    public ResVO getAllChanXueYan(DTO chanXueYanDTO){
        return chanXueYanService.selectAll(chanXueYanDTO);
    }

    @GetMapping("/getSearchChanXueYan")
    public ResVO getSearchChanXueYan(DTO chanXueYanDTO){
        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return chanXueYanService.getSearchChanXueYan(chanXueYanDTO);}

    @RequestMapping(value = "/deleteChanXueYan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean deleteChanXueYan(@RequestParam(value = "ids") List<Integer> ids){
        for (Integer i:ids) {
            System.out.println(i);
        }
        return  chanXueYanService.deleteChanXueYan(ids);}

    @PostMapping("/deleteOneChanXueYan")
    public  JsonBean deleteOneChanXueYan(Integer id){return chanXueYanService.deleteOneChanXueYan(id);}

    @GetMapping("/getChanXueYanDetail")
    public JsonBean getChanXueYanDetail(Integer id){
        System.out.println(id);
        return chanXueYanService.getChanXueYanDetail(id);}

    @RequestMapping(value = "/insertChanXueYan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean insertChanXueYan(@RequestBody ChanXueYanUO uo) throws Exception {
        ChanXueYanEntity element=new ChanXueYanEntity();
        Integer[] people=uo.getPeople();
        Integer role=uo.getRole();
        element.setFinishtime(uo.getFinishtime());
        element.setName(uo.getName());
        element.setPartment(uo.getPartment());
        element.setLianghua(uo.getLianghua());
        element.setWenhao(uo.getWenhao());
        if (role==4||role==1){
            element.setStatus(1);
        }else {
            element.setStatus(0);
        }
        element.setBadge(uo.getShenbao());
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
        element.setMetails(bos.toByteArray());
        System.out.println("***********"+bos.toByteArray().length+"**************");
        return  chanXueYanService.shenBaoChanXueYan(element,people);
    }

    @GetMapping("/getTeacherList")
    public List<Object> getTeacherList(){
//        System.out.println(WebCookie.getCookie());
        return chanXueYanService.getTeacherList();
    }

    @RequestMapping(value = "/ChanXueYanMetials")
    public String ChanXueYanMetials(@RequestParam("file") MultipartFile file) throws IOException {
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

    @GetMapping("/getChanXueYanMetails")
    public byte[] getChanXueYanMetails(Integer id){
        return chanXueYanService.getchanxueyanMetails(id).getMetails();
    }

    @RequestMapping(value = "/passChanXueYan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int passChanXueYan(Integer id,Integer ispass){
        return chanXueYanService.passChanXueYan(id,ispass);
    }

    @GetMapping("/getChanXueYanDetailBadge")
    public JsonBean getChanXueYanDetailBadge(Integer id){
        return new JsonBean(200,"",chanXueYanService.getChanXueYanDetailBadge(id));}

    @RequestMapping(value = "/editChanXueYan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean editChanXueYan(@RequestBody EditChanXueYanUO uo) throws Exception {
        return  new JsonBean(200,"",chanXueYanService.editChanXueYan(uo.getId(),uo.getName(),uo.getFinishtime(),uo.getPartment(),uo.getLianghua(),uo.getPeople(),uo.getWenhao()));
    }

    @GetMapping("/selectProject")
    public JsonBean selectProject(Integer id,String tablename){
        return new JsonBean(200,"",chanXueYanService.selectProject(id,tablename));}

    @GetMapping("/login")
    public JsonBean login(String username,String password){
        return new JsonBean(200,"",chanXueYanService.login(Integer.parseInt(username),password));}
}

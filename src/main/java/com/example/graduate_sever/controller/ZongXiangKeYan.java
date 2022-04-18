package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.EditUO.EditZongXiangKeYanXiangMuUO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.ZongXiangKeYanXiangMuUO;
import com.example.graduate_sever.entity.ZongXiangKeYanXiangMuEntity;
import com.example.graduate_sever.service.HengXiangKeYanService;
import com.example.graduate_sever.service.ZongXiangKeYanService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

@RestController
public class ZongXiangKeYan {
    @Autowired
    private ZongXiangKeYanService service;

    @GetMapping("/getAllZongXiangKeYan")
    public ResVO getAllZongXiangKeYan(DTO dTO){
        return service.getAllZongXiangKeYan(dTO);
    }

    @GetMapping("/getSearchZongXiangKeYan")
    public ResVO getSearchZongXiangKeYan(DTO dTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return service.getSearchZongXiangKeYan(dTO);}

    @RequestMapping(value = "/deleteZongXiangKeYan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean deleteZongXiangKeYan(@RequestParam(value = "ids") List<Integer> ids){return  service.deleteZongXiangKeYan(ids);}

    @PostMapping("/deleteOneZongXiangKeYan")
    public  JsonBean deleteOneZongXiangKeYan(Integer id){return service.deleteOneZongXiangKeYan(id);}

    @GetMapping("/getZongXiangKeYanDetail")
    public JsonBean getZongXiangKeYanDetail(Integer id){
        System.out.println(id);
        return service.getZongXiangKeYanDetial(id);}

    @GetMapping("/getZongXiangKeYanDetailBadge")
    public JsonBean getZongXiangKeYanDetailBadge(Integer id){
        System.out.println(id);
        return new JsonBean(200,"",service.getZongXiangKeYanDetailBadge(id));}

    @RequestMapping(value = "/updateZongXiangKeYan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean updateZongXiangKeYan(@RequestBody ZongXiangKeYanXiangMuUO uo) throws IOException {
        Integer[] people=uo.getPeople();
        ZongXiangKeYanXiangMuEntity element=null;
        JsonBean jsonBean=null;
        int role=uo.getRole();
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
        if (role==4||role==2){
            element=new ZongXiangKeYanXiangMuEntity(1,uo.getFinishtime(),uo.getLevel(),uo.getType(),uo.getLixiang(),uo.getPartment(),uo.getName(), uo.getShenbao(),bos.toByteArray());
        }else {
            element=new ZongXiangKeYanXiangMuEntity(0,uo.getFinishtime(),uo.getLevel(),uo.getType(),uo.getLixiang(),uo.getPartment(),uo.getName(), uo.getShenbao(),bos.toByteArray());
        }

        return  service.shenBaoZongXiangKeYan(element,people);
    }

    @RequestMapping(value = "/ZongXiangKeYanMetials")
    public String ZongXiangKeYanMetials(@RequestParam("file") MultipartFile file) throws IOException {
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
    @GetMapping("/getZongXiangKeYanMetails")
    public byte[] getZongXiangKeYanMetails(Integer id){
        return service.getZongXiangKeYanMetails(id).getMetails();
    }

    @RequestMapping(value = "/passZongXiangKeYan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int passZongXiangKeYan(Integer id,Integer ispass){
        return service.passZongXiangKeYan(id,ispass);
    }

    @RequestMapping(value = "/editZongXiangKeYan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean editZongXiangKeYan(@RequestBody EditZongXiangKeYanXiangMuUO uo) throws Exception {
        return  new JsonBean(200,"",service.editZongXiangKeYan(uo.getId(),uo.getName(),uo.getFinishtime(),uo.getPartment(),uo.getLevel(),uo.getPeople()));
    }
}

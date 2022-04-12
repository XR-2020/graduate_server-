package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.PingGuZhongXinXiangGuanUO;
import com.example.graduate_sever.entity.PingGuZhongXinXiangGuanEntity;
import com.example.graduate_sever.service.JiaoYanLunWenMuService;
import com.example.graduate_sever.service.PingGuZhongXinService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@RestController
public class PingGuZhongXin {
    @Autowired
    private PingGuZhongXinService pingGuZhongXinService;

    @GetMapping("/getAllPingGuZhongXin")
    public ResVO getAllPingGuZhongXin(DTO jiaoYanXiangMuDTO){
        return pingGuZhongXinService.getAllPingGuZhongXin(jiaoYanXiangMuDTO);
    }

    @GetMapping("/getSearchPingGuZhongXin")
    public ResVO getSearchPingGuZhongXin(DTO jiaoYanXiangMuDTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return pingGuZhongXinService.getSearchPingGuZhongXin(jiaoYanXiangMuDTO);}

    @PostMapping("/deletePingGuZhongXin")
    public JsonBean deletePingGuZhongXin(int[] ids){return  pingGuZhongXinService.deletePingGuZhongXin(ids);}

    @PostMapping("/deleteOnePingGuZhongXin")
    public  JsonBean deleteOnePingGuZhongXin(Integer id){return pingGuZhongXinService.deleteOnePingGuZhongXin(id);}

    @GetMapping("/getPingGuZhongXinDetail")
    public JsonBean getPingGuZhongXinDetail(Integer id){
        System.out.println(id);

        return pingGuZhongXinService.getPingGuZhongXinDetial(id);}

    @RequestMapping(value = "/updatePingGuZhongXin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean updatePingGuZhongXin(@RequestBody PingGuZhongXinXiangGuanUO uo) throws IOException {
        Integer[] people=uo.getPeople();
        PingGuZhongXinXiangGuanEntity element=null;
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
        if (role!=4&&role!=1){
            element=new PingGuZhongXinXiangGuanEntity(0,uo.getFinishtime(),uo.getGrade(),uo.getPartment(),uo.getName(), uo.getFirstpeople(),uo.getShenbao(),bos.toByteArray());
        }else {
            element=new PingGuZhongXinXiangGuanEntity(1,uo.getFinishtime(),uo.getGrade(),uo.getPartment(),uo.getName(),uo.getFirstpeople(), uo.getShenbao(),bos.toByteArray());
        }

        return  pingGuZhongXinService.shenBaoPingGuZhongXin(element,people);
    }
    @RequestMapping(value = "/PingGuZhongXinMetials")
    public String PingGuZhongXinMetials(@RequestParam("file") MultipartFile file) throws IOException {
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
    @GetMapping("/getPingGuZhongXinMetails")
    public byte[] getPingGuZhongXinMetails(Integer id){
        return pingGuZhongXinService.getPingGuZhongXinMetails(id).getMetails();
    }

    @RequestMapping(value = "/passPingGuZhongXin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int passPingGuZhongXin(Integer id,Integer ispass){
        return pingGuZhongXinService.passPingGuZhongXin(id,ispass);
    }
}

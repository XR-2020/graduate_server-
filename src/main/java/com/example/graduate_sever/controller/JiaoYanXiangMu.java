package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.JiaoYanXiangMuUO;
import com.example.graduate_sever.entity.JiaoYanXiangMuEntity;
import com.example.graduate_sever.service.JiaoYanXiangMuService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@RestController
public class JiaoYanXiangMu {
    @Autowired
    private JiaoYanXiangMuService jiaoYanXiangMuService;

    @GetMapping("/getAllJiaoYan")
    public ResVO getAllJiaoYan(DTO jiaoYanXiangMuDTO){
        return jiaoYanXiangMuService.getAllJiaoYan(jiaoYanXiangMuDTO);
    }

    @GetMapping("/getSearchJiaoYan")
    public ResVO getSearchJiaoYan(DTO jiaoYanXiangMuDTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return jiaoYanXiangMuService.getSearchJiaoYan(jiaoYanXiangMuDTO);}

    @PostMapping("/deleteJiaoYan")
    public JsonBean deleteJiaoYan(int[] ids){return  jiaoYanXiangMuService.deleteJiaoYan(ids);}

    @PostMapping("/deleteOneJiaoYan")
    public  JsonBean deleteOneJiaoYan(Integer id){return jiaoYanXiangMuService.deleteOneJiaoYan(id);}

    @GetMapping("/getJiaoYanDetial")
    public JsonBean getJiaoYanDetial(Integer ids){
        System.out.println(ids);

        return jiaoYanXiangMuService.getJiaoYanDetial(ids);}

    @RequestMapping(value = "/updateJiaoYan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean updateJiaoYan(@RequestBody JiaoYanXiangMuUO uo) throws IOException {
        Integer[] people=uo.getPeople();
        JiaoYanXiangMuEntity element=null;
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
        if (role!=1&&role!=4){
            element=new JiaoYanXiangMuEntity(0,uo.getFinishtime(),uo.getLianghua(),uo.getWenhao(),uo.getPartment(),uo.getName(), uo.getShenbao(),bos.toByteArray());
        }else {
            element=new JiaoYanXiangMuEntity(1,uo.getFinishtime(),uo.getLianghua(),uo.getWenhao(),uo.getPartment(),uo.getName(), uo.getShenbao(),bos.toByteArray());}

        return  jiaoYanXiangMuService.shenBaoJiaoYan(element,people);
    }
    @RequestMapping(value = "/JiaoYanXiangMuMetials")
    public String JiaoYanXiangMuMetials(@RequestParam("file") MultipartFile file) throws IOException {
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
    @GetMapping("/getJiaoYanMetails")
    public byte[] getJiaoYanMetails(Integer id){
        return jiaoYanXiangMuService.getJiaoYanXiangMuMetails(id).getMetails();
    }

    @RequestMapping(value = "/passJiaoYan", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int passJiaoYan(Integer id,Integer ispass){
        return jiaoYanXiangMuService.passJiaoYan(id,ispass);
    }
}

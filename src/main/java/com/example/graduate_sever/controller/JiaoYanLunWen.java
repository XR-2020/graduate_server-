package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.HonorUO;
import com.example.graduate_sever.common.UO.JiaoYanLunWenUO;
import com.example.graduate_sever.entity.HonorEntity;
import com.example.graduate_sever.entity.JiaoYanLunWenEntity;
import com.example.graduate_sever.service.JiaoYanLunWenMuService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@RestController
public class JiaoYanLunWen {
    @Autowired
    private JiaoYanLunWenMuService jiaoYanLunWenMuService;

    @GetMapping("/getAllJiaoYanLunWen")
    public ResVO getAllJiaoYanLunWen(DTO jiaoYanXiangMuDTO) {
        return jiaoYanLunWenMuService.getAllJiaoYanLunWen(jiaoYanXiangMuDTO);
    }

    @GetMapping("/getSearchJiaoYanLunWen")
    public ResVO getSearchJiaoYanLunWen(DTO jiaoYanXiangMuDTO) {
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return jiaoYanLunWenMuService.getSearchJiaoYanLunWen(jiaoYanXiangMuDTO);
    }

    @PostMapping("/deleteJiaoYanLunWen")
    public JsonBean deleteJiaoYanLunWen(int[] ids) {
        return jiaoYanLunWenMuService.deleteJiaoYanLunWen(ids);
    }

    @PostMapping("/deleteOneJiaoYanLunWen")
    public JsonBean deleteOneJiaoYanLunWen(Integer id) {
        return jiaoYanLunWenMuService.deleteOneJiaoYanLunWen(id);
    }

    @GetMapping("/getJiaoYanLunWenDetail")
    public JsonBean getJiaoYanLunWenDetail(Integer id) {
        System.out.println(id);
        return jiaoYanLunWenMuService.getJiaoYanLunWenDetial(id);
    }

    @RequestMapping(value = "/updateJiaoYanLunWen", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean updateJiaoYanLunWen(@RequestBody JiaoYanLunWenUO uo) throws IOException {
        JiaoYanLunWenEntity element=null;
        Integer[] people=uo.getPeople();
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
        if (role!=2&&role!=4){
            element=new JiaoYanLunWenEntity(0,uo.getFinishtime(),uo.getPartment(),uo.getName(), uo.getShenbao(),bos.toByteArray());
        }else {
            element=new JiaoYanLunWenEntity(1,uo.getFinishtime(),uo.getPartment(),uo.getName(), uo.getShenbao(),bos.toByteArray());
        }

        return jiaoYanLunWenMuService.shenBaoJiaoYanLunWen(element,people);
    }
    @RequestMapping(value = "/JiaoYanLunWenMetials")
    public String JiaoYanLunWenMetials(@RequestParam("file") MultipartFile file) throws IOException {
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

}
package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.ChanXueYanUO;
import com.example.graduate_sever.common.UO.CompetitionUO;
import com.example.graduate_sever.entity.ChanXueYanEntity;
import com.example.graduate_sever.entity.CompetitionEntity;
import com.example.graduate_sever.service.XueKeJingSaiService;
import com.example.graduate_sever.service.ZhuZuoService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@RestController
public class XueKeJingSai {
    @Autowired
    private XueKeJingSaiService service;

    @GetMapping("/getAllJingSai")
    public ResVO getAllJingSai(DTO dTO){
        return service.getAllJingSai(dTO);
    }

    @GetMapping("/getSearchJingSai")
    public ResVO getSearchJingSai(DTO dTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return service.getSearchJingSai(dTO);}

    @PostMapping("/deleteJingSai")
    public JsonBean deleteJingSai(int[] ids){return  service.deleteJingSai(ids);}

    @PostMapping("/deleteOneJingSai")
    public  JsonBean deleteOneJingSai(Integer id){return service.deleteOneJingSai(id);}

    @GetMapping("/getJingSaiDetail")
    public JsonBean getJingSaiDetail(Integer id){
        System.out.println(id);

        return service.getJingSaiDetial(id);}

    @RequestMapping(value = "/updateCompetition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean updateCompetition(@RequestBody CompetitionUO uo) throws IOException {
        Integer[] people=uo.getPeople();
        CompetitionEntity element=new CompetitionEntity();
        element.setFinishtime(uo.getFinishtime());
        element.setGrade(uo.getGrade());
        element.setLevel(uo.getLevel());
        element.setName(uo.getName());
        element.setStudent(uo.getStudent());
        if(uo.getRole() ==3){
            element.setStatus(1);
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
        return service.insertJingSai(element,people);
    }

    @RequestMapping(value = "/XueKeJingSaiMetials")
    public String XueKeJingSaiMetials(@RequestParam("file") MultipartFile file) throws IOException {
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

    @GetMapping("/getXueKeJingSaiMetails")
    public byte[] getXueKeJingSaiMetails(Integer id){
        return service.getXueKeJingSaiMetails(id).getMetails();
    }
}

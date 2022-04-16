package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.EditUO.EditJiaoYuGuiHuaXiangMuUO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.JiaoYuGuiHuaXiangMuUO;
import com.example.graduate_sever.entity.JiaoYuGuiHuaXiangMuEntity;
import com.example.graduate_sever.service.JiaoYuGuiHuaService;
import com.example.graduate_sever.service.PingGuZhongXinService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@RestController
public class JiaoYuGuiHua {
    @Autowired
    private JiaoYuGuiHuaService jiaoYuGuiHuaService;

    @GetMapping("/getAllJiaoYuGuiHua")
    public ResVO getAllJiaoYuGuiHua(DTO dTO){
        return jiaoYuGuiHuaService.getAllJiaoYuGuiHua(dTO);
    }

    @GetMapping("/getSearchJiaoYuGuiHua")
    public ResVO getSearchJiaoYuGuiHua(DTO dTO){
//        System.out.println(chanXueYanDTO.getKey()+"______"+chanXueYanDTO.getPageIndex()+"_______"+chanXueYanDTO.getPageSize());
        return jiaoYuGuiHuaService.getSearchJiaoYuGuiHua(dTO);}

    @PostMapping("/deleteJiaoYuGuiHua")
    public JsonBean deleteJiaoYuGuiHua(int[] ids){return  jiaoYuGuiHuaService.deleteJiaoYuGuiHua(ids);}

    @PostMapping("/deleteOneJiaoYuGuiHua")
    public  JsonBean deleteOnePingGuZhongXin(Integer id){return jiaoYuGuiHuaService.deleteOneJiaoYuGuiHua(id);}

    @GetMapping("/getJiaoYuGuiHuaDetail")
    public JsonBean getJiaoYuGuiHuaDetail(Integer id){
        System.out.println(id);

        return jiaoYuGuiHuaService.getJiaoYuGuiHuaDetial(id);}

    @GetMapping("/getJiaoYuGuiHuaDetailBadge")
    public JsonBean getJiaoYuGuiHuaDetailBadge(Integer id){
        System.out.println(id);

        return new JsonBean(200,"",jiaoYuGuiHuaService.getJiaoYuGuiHuaDetailBadge(id));}

    @RequestMapping(value = "/updateJiaoYuGuiHua", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean updateJiaoYuGuiHua(@RequestBody JiaoYuGuiHuaXiangMuUO uo) throws IOException {
        Integer[] people=uo.getPeople();
        JiaoYuGuiHuaXiangMuEntity element=null;
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
        if (role!=4){
            element=new JiaoYuGuiHuaXiangMuEntity(0,uo.getFinishtime(),uo.getPartment(),uo.getName(),uo.getGrade(),uo.getLevel(),uo.getDanwei(), uo.getShenbao(),bos.toByteArray());
        }else {
            element=new JiaoYuGuiHuaXiangMuEntity(1,uo.getFinishtime(),uo.getPartment(),uo.getName(),uo.getGrade(),uo.getLevel(),uo.getDanwei(), uo.getShenbao(),bos.toByteArray());
        }

        return  jiaoYuGuiHuaService.shenBaoJiaoYuGuiHua(element,people);
    }
    @RequestMapping(value = "/JiaoYuGuiHuaMetials")
    public String JiaoYuGuiHuaMetials(@RequestParam("file") MultipartFile file) throws IOException {
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
    @GetMapping("/getJiaoYuGuiHuaMetails")
    public byte[] getJiaoYuGuiHuaMetails(Integer id){
        return jiaoYuGuiHuaService.getJiaoYuGuiHuaMetails(id).getMetails();
    }

    @RequestMapping(value = "/passJiaoYuGuiHua", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int passJiaoYuGuiHua(Integer id,Integer ispass){
        return jiaoYuGuiHuaService.passJiaoYuGuiHua(id,ispass);
    }

    @RequestMapping(value = "/editJiaoYuGuiHua", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean editJiaoYuGuiHua(@RequestBody EditJiaoYuGuiHuaXiangMuUO uo) throws Exception {
        return  new JsonBean(200,"",jiaoYuGuiHuaService.editJiaoYuGuiHua(uo.getId(),uo.getName(),uo.getFinishtime(),uo.getPartment(),uo.getDanwei(),uo.getGrade(),uo.getLevel(),uo.getPeople()));
    }
}

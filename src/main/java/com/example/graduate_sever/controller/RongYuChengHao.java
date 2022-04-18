package com.example.graduate_sever.controller;


import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.EditUO.EditChanXueYanUO;
import com.example.graduate_sever.common.EditUO.EditCompetitionUO;
import com.example.graduate_sever.common.EditUO.EditHonorUO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.UO.HonorUO;
import com.example.graduate_sever.entity.HonorEntity;
import com.example.graduate_sever.service.RongYuChengHaoService;
import com.example.graduate_sever.service.ZhuanLiService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

@RestController
public class RongYuChengHao {
    @Autowired
    private RongYuChengHaoService service;

    @GetMapping("/getAllRongYu")
    public ResVO getAllRongYu(DTO dTO){
        return service.getAllRongYu(dTO);
    }

    @GetMapping("/getSearchRongYu")
    public ResVO getSearchRongYu(DTO dTO){
     return service.getSearchRongYu(dTO);}

    @RequestMapping(value = "/deleteRongYu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean deleteRongYu(@RequestParam(value = "ids") List<Integer> ids){return  service.deleteRongYu(ids);}

    @PostMapping("/deleteOneRongYu")
    public  JsonBean deleteOneRongYu(Integer id){return service.deleteOneRongYu(id);}

    @RequestMapping(value = "/updateHonor", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean updateHonor(@RequestBody HonorUO uo) throws IOException {
        StringBuffer str=new StringBuffer();
        for (String s:uo.getPeople()) {
            str.append(s+" ");
        }
        String people=str.toString();
        HonorEntity element=null;
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
        if (role==4){
            element=new HonorEntity(1,uo.getName(),uo.getLevel(),uo.getFinishtime(),uo.getPartment(), people,bos.toByteArray(),uo.getShenbao());
        }else {
            element=new HonorEntity(0,uo.getName(),uo.getLevel(),uo.getFinishtime(),uo.getPartment(),people,bos.toByteArray(),uo.getShenbao());
        }

        return  service.shenbaoRongYuChengHao(element);
    }

    @RequestMapping(value = "/RongYuChengHaoMetials")
    public String RongYuChengHaoMetials(@RequestParam("file") MultipartFile file) throws IOException {
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
    @GetMapping("/getRongYuChengHaoMetails")
    public byte[] getRongYuChengHaoMetails(Integer id){
        return service.getRongYuChengHaoMetails(id).getMetails();
    }

    @RequestMapping(value = "/passRongYuChengHao", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int passRongYuChengHao(Integer id,Integer ispass){
        return service.passRongYuChengHao(id,ispass);
    }


    @GetMapping("/getHonorBadge")
    public JsonBean getHonorBadge(Integer id){
        return new JsonBean(200,"",service.getHonorBadge(id));}

    @RequestMapping(value = "/editHonor", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JsonBean editHonor(@RequestBody EditHonorUO uo) throws Exception {
        return  new JsonBean(200,"",service.editHonor(uo.getId(),uo.getName(),uo.getPartment(),uo.getFinishtime(),uo.getTeacher(),uo.getLevel()));
    }

    @RequestMapping(value = "/honorImport")
    public JsonBean honorImport(@RequestParam("file") MultipartFile file) throws IOException{
        return service.honorImport(file,file.getName());
    }
}

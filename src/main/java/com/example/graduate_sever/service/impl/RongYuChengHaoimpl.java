package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.ChanXueYanMapper;
import com.example.graduate_sever.Dao.RongYuChengHaoMapper;
import com.example.graduate_sever.common.*;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.entity.CompetitionEntity;
import com.example.graduate_sever.entity.HonorEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.Honor;
import com.example.graduate_sever.model.MyShenBaoModel;
import com.example.graduate_sever.service.RongYuChengHaoService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service("RongYuChengHaoService")
public class RongYuChengHaoimpl implements RongYuChengHaoService {
    @Autowired
    private RongYuChengHaoMapper mapper;
    @Autowired
    private ChanXueYanMapper chanXueYanMapper;
    @Override
    public ResVO getAllRongYu(DTO dTO) {
        List<Honor> list=mapper.selectAllRongYuChengHao(dTO);
        List<TableData> tableData=new ArrayList<>();
        for (Honor c:list) {
            tableData.add(new TableData(c,mapper.getRongYuDetail(c.getId())));
        }
        return new ResVO(tableData, mapper.selectAllRongYuChengHaoPageTotal());
    }

    @Override
    public ResVO getSearchRongYu(DTO dTO) {
        List<Honor> list=mapper.getSearchRongYu(dTO);
        List<TableData> tableData=new ArrayList<>();
        for (Honor c:list) {
            tableData.add(new TableData(c,mapper.getRongYuDetail(c.getId())));
        }
        return new ResVO(tableData, mapper.getSearchRongYuPageTotal());
    }

    @Override
    public JsonBean deleteRongYu(List<Integer> ids) {
        for (Integer id:ids) {
            mapper.deleteOneRongYu(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneRongYu(Integer id) {
        mapper.deleteOneRongYu(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean insertRongYuChengHao(HonorEntity entity,Integer[] people) {
        int ref=mapper.insertRongYuChengHao(entity);
        if(ref!=0){
            for (Integer ach_id:people) {
                mapper.insertRongYuChengHaoParticipation(new ParticipationEntity(ach_id,entity.getId(),14));
            }
        }
        return new JsonBean(200,"",ref);
    }

    @Override
    public JsonBean shenbaoRongYuChengHao(HonorEntity entity) {
        int ref=mapper.shenBaoRongYuChengHao(entity);
        return new JsonBean(200,"",ref);
    }

    @Override
    public Metails getRongYuChengHaoMetails(Integer id) {
        return mapper.RongYuChengHaoMetails(id);
    }

    @Override
    public List<TableData> waitingrongyuchenghao(DTO dTO) {
        List<Honor> list=mapper.waitingrongyuchenghao(dTO);
        List<TableData> tableData=new ArrayList<>();
        for (Honor c:list) {
            tableData.add(new TableData(c,mapper.getRongYuDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public int passRongYuChengHao(Integer id, Integer ispass) {
        return mapper.passRongYuChengHao(id,ispass);
    }

    @Override
    public List<TableData> getDisData(MyShenBaoDTO dto) {
        List<MyShenBaoModel> list=mapper.getRongYuChengHaoDisData(dto);
        for (MyShenBaoModel b:list) {
            System.out.println(b.getId());
        }
        List<TableData> tableData=new ArrayList<>();
        for (MyShenBaoModel c:list) {
            tableData.add(new TableData(c,mapper.getRongYuDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<Integer> getHonorBadge(Integer id) {
        List<People> people=mapper.getRongYuDetail(id);
        List<Integer> badges=new ArrayList<>();
        for (People p:people) {
            badges.add(p.getBadge());
        }
        return badges;
    }

    @Override
    public int editHonor(Integer id, String name, String partment, String finishtime, String teacher,String level) {
        int ref=mapper.editHonor(id,name,finishtime,partment,teacher,level);
        return ref;
    }

    @Override
    public JsonBean honorImport(MultipartFile file, String fileName) throws IOException {
        boolean notNull = false;
        List<HonorEntity> importHonors = new ArrayList();
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = null;
        try {
            is = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null){
            notNull = true;
        }
        HonorEntity imporHonor;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            imporHonor=new HonorEntity();

            row.getCell(0).setCellType(CellType.STRING);
            String name= row.getCell(0).getStringCellValue();

            if(name == null || name.isEmpty()){
                throw new MyException(500,"导入失败(第"+(r+1)+"行,项目名未填写)");
            }
            row.getCell(1).setCellType(CellType.STRING);
            String finishtime = row.getCell(1).getStringCellValue();
            if(finishtime==null || finishtime.isEmpty()){
                throw new MyException(500,"导入失败(第"+(r+1)+"行,获得时间未填写)");
            }

            row.getCell(2).setCellType(CellType.STRING);
            String level=row.getCell(2).getStringCellValue();
            if(level==null || level.isEmpty()){
                throw new MyException(500,"导入失败(第"+(r+1)+"行,竞赛等级未填写)");
            }
            row.getCell(3).setCellType(CellType.STRING);
            String teacher=row.getCell(3).getStringCellValue();
            if(teacher==null || teacher.isEmpty()){
                throw new MyException(500,"导入失败(第"+(r+1)+"行,获奖人员未填写)");
            }
            imporHonor.setFinishtime(finishtime);
            imporHonor.setLevel(level);
            imporHonor.setName(name);
            imporHonor.setPartment("软件学院");
            imporHonor.setTeacher(teacher);
            imporHonor.setStatus(1);
            importHonors.add(imporHonor);
        }
        int num=0;

        for (HonorEntity entity : importHonors) {

            try {
                num=mapper.insertRongYuChengHao(entity);
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        JsonBean jsonBean=new JsonBean();
        if (num>0){
            jsonBean= new JsonBean(200,"导入成功","");
        }
        return jsonBean;
    }

}

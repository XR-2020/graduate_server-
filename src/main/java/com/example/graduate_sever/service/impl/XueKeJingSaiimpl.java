package com.example.graduate_sever.service.impl;

import com.example.graduate_sever.Dao.ChanXueYanMapper;
import com.example.graduate_sever.Dao.XueKeJingSaiMapper;
import com.example.graduate_sever.common.*;
import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.entity.CompetitionEntity;
import com.example.graduate_sever.entity.ParticipationEntity;
import com.example.graduate_sever.model.Competition;
import com.example.graduate_sever.model.Honor;
import com.example.graduate_sever.model.MyShenBaoModel;
import com.example.graduate_sever.service.XueKeJingSaiService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service("XueKeJingSaiService")
public class XueKeJingSaiimpl implements XueKeJingSaiService {
    @Autowired
    private XueKeJingSaiMapper mapper;
    @Autowired
    private ChanXueYanMapper chanXueYanMapper;
    @Override
    public ResVO getAllJingSai(DTO dTO) {
        List<Competition> list=mapper.getAllJingSai(dTO);
        List<TableData> tableData=new ArrayList<>();
        for (Competition c:list) {
            tableData.add(new TableData(c,mapper.getJingSaiDetail(c.getId())));
        }
        return new ResVO(tableData, mapper.getAllJingSaiPageTotal());
    }

    @Override
    public ResVO getSearchJingSai(DTO dTO) {
        List<Competition> list=mapper.getSearchJingSai(dTO);
        List<TableData> tableData=new ArrayList<>();
        for (Competition c:list) {
            tableData.add(new TableData(c,mapper.getJingSaiDetail(c.getId())));
        }
        return new ResVO(tableData, mapper.getSearchJingSaiPageTotal());
    }

    @Override
    public JsonBean deleteJingSai(List<Integer> ids) {
        for (Integer id:ids) {
            mapper.deleteOneJingSai(id);
        }
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean deleteOneJingSai(Integer id) {
        mapper.deleteOneJingSai(id);
        return new JsonBean(200,"","");
    }

    @Override
    public JsonBean getJingSaiDetial(Integer id) {
        return new JsonBean(200,"",mapper.getJingSaiDetail(id));
    }

    @Override
    public JsonBean insertJingSai(CompetitionEntity entity) {
        return new JsonBean(200,"",  mapper.insertJingSai(entity));
    }

    @Override
    public JsonBean shenBaoJingSai(CompetitionEntity entity, Integer[] people) {
        int ref=mapper.shenBaoJingSai(entity);
        if(ref!=0){
            for (Integer ach_id:people) {
                mapper.insertJingSaiParticipation(new ParticipationEntity(ach_id,entity.getId(),13));
            }
        }
        return new JsonBean(200,"",ref);
    }

    @Override
    public Metails getXueKeJingSaiMetails(Integer id) {
        return mapper.XueKeJingSaiMetails(id);
    }

    @Override
    public List<TableData> waitingxuekejingsai(DTO dTO) {
        List<Competition> list=mapper.waitingxuekejingsai(dTO);
        List<TableData> tableData=new ArrayList<>();
        for (Competition c:list) {
            tableData.add(new TableData(c,mapper.getJingSaiDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public int passXueKeJingSai(Integer id, Integer ispass) {
        return mapper.passXueKeJingSai(id,ispass);
    }

    @Override
    public List<TableData> getDisData(MyShenBaoDTO dto) {
        List<MyShenBaoModel> list=mapper.getXueKeJingSaiDisData(dto);
        for (MyShenBaoModel b:list) {
            System.out.println(b.getId());
        }
        List<TableData> tableData=new ArrayList<>();
        for (MyShenBaoModel c:list) {
            tableData.add(new TableData(c,mapper.getJingSaiDetail(c.getId())));
        }
        return tableData;
    }

    @Override
    public List<Integer> getJingSaiDetailBadge(Integer id) {
        List<People> people=mapper.getJingSaiDetail(id);
        List<Integer> badges=new ArrayList<>();
        for (People p:people) {
            badges.add(p.getBadge());
        }
        return badges;
    }

    @Override
    public List<Integer> getComputitionBadge(Integer id) {
        List<People> people=mapper.getJingSaiDetail(id);
        List<Integer> badges=new ArrayList<>();
        for (People p:people) {
            badges.add(p.getBadge());
        }
        return badges;
    }

    @Override
    public int editCompetition(Integer id, String name, String partment, String finishtime, String teacher, String grade, String student, String level) {
        int ref=mapper.editCompetition(id,name,finishtime,grade,student,level,teacher,partment);
        return ref;
    }

    @Override
    public JsonBean competitionImport(MultipartFile file, String fileName) throws IOException {
        boolean notNull = false;
        List<CompetitionEntity> importCompetitions = new ArrayList();
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
        CompetitionEntity imporCompetition;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            imporCompetition=new CompetitionEntity();

            row.getCell(0).setCellType(CellType.STRING);
            String name= row.getCell(0).getStringCellValue();

            if(name == null || name.isEmpty()){
                throw new MyException(500,"导入失败(第"+(r+1)+"行,项目名未填写)");
            }
            row.getCell(1).setCellType(CellType.STRING);
            String finishtime = row.getCell(1).getStringCellValue();
            if(finishtime==null || finishtime.isEmpty()){
                throw new MyException(500,"导入失败(第"+(r+1)+"行,完成时间未填写)");
            }
            row.getCell(2).setCellType(CellType.STRING);
            String student=row.getCell(2).getStringCellValue();
            if(student==null || student.isEmpty()){
                throw new MyException(500,"导入失败(第"+(r+1)+"行,参赛学生未填写)");
            }
            row.getCell(3).setCellType(CellType.STRING);
            String level=row.getCell(3).getStringCellValue();
            if(level==null || level.isEmpty()){
                throw new MyException(500,"导入失败(第"+(r+1)+"行,竞赛等级未填写)");
            }
            row.getCell(4).setCellType(CellType.STRING);
            String grade=row.getCell(4).getStringCellValue();
            if(grade==null || grade.isEmpty()){
                throw new MyException(500,"导入失败(第"+(r+1)+"行,获奖等级未填写)");
            }
            row.getCell(5).setCellType(CellType.STRING);
            String teacher=row.getCell(5).getStringCellValue();
            if(teacher==null || teacher.isEmpty()){
                throw new MyException(500,"导入失败(第"+(r+1)+"行,指导教师未填写)");
            }
            imporCompetition.setTeacher(teacher);
            imporCompetition.setStatus(1);
            imporCompetition.setStudent(student);
            imporCompetition.setName(name);
            imporCompetition.setFinishtime(finishtime);
            imporCompetition.setGrade(grade);
            imporCompetition.setLevel(level);
            imporCompetition.setPartment("软件学院");
            System.out.println(imporCompetition);
            importCompetitions.add(imporCompetition);
        }
        int num=0;

        for (CompetitionEntity entity : importCompetitions) {

            try {
                num=mapper.insertJingSai(entity);
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

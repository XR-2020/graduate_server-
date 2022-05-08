package com.example.graduate_sever.service;

import com.example.graduate_sever.common.DTO.DTO;
import com.example.graduate_sever.common.DTO.MyShenBaoDTO;
import com.example.graduate_sever.common.JsonBean;
import com.example.graduate_sever.common.Metails;
import com.example.graduate_sever.common.ResVO;
import com.example.graduate_sever.common.TableData;
import com.example.graduate_sever.entity.CompetitionEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface XueKeJingSaiService {
     ResVO getAllJingSai(DTO dTO);
     ResVO getSearchJingSai(DTO dTO);
     JsonBean deleteJingSai(List<Integer> ids);
     JsonBean deleteOneJingSai(Integer id);
     JsonBean getJingSaiDetial(Integer id);
     JsonBean insertJingSai(CompetitionEntity entity);
     JsonBean shenBaoJingSai(CompetitionEntity entity);
     Metails getXueKeJingSaiMetails(Integer id);
     List<TableData>  waitingxuekejingsai(DTO dTO);

    int passXueKeJingSai(Integer id, Integer ispass);

     List<TableData> getDisData(MyShenBaoDTO dto);

    List<Integer> getJingSaiDetailBadge(Integer id);

    List<Integer> getComputitionBadge(Integer id);

    int editCompetition(Integer id, String name, String partment, String finishtime, String teacher, String grade, String student, String level);

    JsonBean competitionImport(MultipartFile file, String name) throws IOException;

    long waitingxuekejingsaiPageToTal();
}

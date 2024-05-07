package com.oasm.dao;

import com.oasm.domain.SurveyStateVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SurveyDAO {

    List<SurveyStateVO> selectSurveyResults (SurveyStateVO vo);
    Integer countSurveyResults (SurveyStateVO vo);

    Integer countAllSurveyResults ();

    Integer countTodaySurveyResults ();

    Integer countIllegalSurveyResults ();

    Integer countMySurveyResults (String id);

}

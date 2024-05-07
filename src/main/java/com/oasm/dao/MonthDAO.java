package com.oasm.dao;

import com.oasm.domain.MonthTypeFormVO;
import com.oasm.domain.SurveyStateVO;


import java.util.List;

public interface MonthDAO {

    // 월별 광고물 종류 통계 
    List<SurveyStateVO> monthadvtype(String survey_date, String code_nm);

    // 월별 광고물 종류 통계 (요청)
    List<MonthTypeFormVO> monthadvtypeWithList(MonthTypeFormVO formVO);


    // 월별 조명 종류 통계
    List<SurveyStateVO> monthLightType(String survey_date, String code_nm);

    // 월별 종류 종류 통계 (요청)
    List<MonthTypeFormVO> monthLightTypeWithList(MonthTypeFormVO formVO);



    // 월별 광고물 조사 결과 통계
    List<SurveyStateVO> monthSurveyResult(String survey_date, String code_nm);

    // 월별 광고물 조사 결과 통계 (요청)
    List<MonthTypeFormVO> monthSurveyResultList(MonthTypeFormVO formVO);


    // 도로 저촉 유무 통계
    List<SurveyStateVO> monthRoadInvasion(String survey_date, String building_cd, String road_yn);

    // 도로 저촉 유무 통계 (요청)
    List<MonthTypeFormVO> monthRoadInvasionList(MonthTypeFormVO formVO);


}

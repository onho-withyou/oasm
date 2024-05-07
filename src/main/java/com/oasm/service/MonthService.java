package com.oasm.service;


import com.oasm.dao.MonthDAO;
import com.oasm.domain.MonthTypeFormVO;
import com.oasm.domain.SurveyStateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthService {

    private final MonthDAO monthDAO;

    @Autowired
    public MonthService(MonthDAO monthDAO) {

        this.monthDAO = monthDAO;
    }

   // 월별 광고물 종류 통계
    public List<SurveyStateVO> monthadvtype(String survey_date, String code_nm) {
        return monthDAO.monthadvtype(survey_date,code_nm);
    }
    // 월별 광고물 종류 통계 (요청)
    public List<MonthTypeFormVO> monthadvtypeWithList(MonthTypeFormVO formVO) {
        return monthDAO.monthadvtypeWithList(formVO);
    }

    // 월별 조명 종류 통계
    public List<SurveyStateVO> monthLightType(String survey_date, String code_nm) {
        return monthDAO.monthadvtype(survey_date, code_nm);
    }

    // 월별 조명 종류 통계 (요청)
    public List<MonthTypeFormVO> monthLightTypeWithList(MonthTypeFormVO formVO) {
        return monthDAO.monthLightTypeWithList(formVO);
    }

    // 월별 광고물 조사 결과 통계
    public List<SurveyStateVO> monthSurveyResult(String survey_date, String code_nm) {
        return monthDAO.monthSurveyResult(survey_date, code_nm);
    }

    // 월별 광고물 조사 결과 통계 (요청)
    public List<MonthTypeFormVO> monthSurveyResultList(MonthTypeFormVO formVO) {
        return monthDAO.monthSurveyResultList(formVO);
    }

    // 도로 저촉 유무 통계
    public List<SurveyStateVO> monthRoadInvasion(String survey_date, String building_cd, String road_yn) {
        return monthDAO.monthRoadInvasion(survey_date, building_cd, road_yn);
    }

    // 도로 저촉 유무 통계 (요청)
    public List<MonthTypeFormVO> monthRoadInvasionList(MonthTypeFormVO formVO) {
        return monthDAO.monthRoadInvasionList(formVO);
    }

}
package com.oasm.service;

import com.oasm.dao.SurveyDAO;
import com.oasm.domain.PageDTO;
import com.oasm.domain.SurveyStateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyDAO dao;

    public List<SurveyStateVO> selectSurveyResults (SurveyStateVO vo) {
        return dao.selectSurveyResults(vo);
    }

    public Integer countSurveyResults (SurveyStateVO vo) {
        return dao.countSurveyResults(vo);
    }

    public Integer countAllSurveyResults () {return dao.countAllSurveyResults();}

    public Integer countTodaySurveyResults () {return dao.countTodaySurveyResults();}

    public Integer countIllegalSurveyResults () {return dao.countIllegalSurveyResults();}

    public Integer countMySurveyResults (String id) {return dao.countMySurveyResults(id);}

}

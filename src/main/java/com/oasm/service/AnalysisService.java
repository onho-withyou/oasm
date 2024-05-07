package com.oasm.service;

import com.oasm.dao.AnalysisDAO;
import com.oasm.domain.AnalysisVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalysisService {

    private final AnalysisDAO analysisdao;

    public List<AnalysisVO> getAdvType(String startDate, String endDate, List<String> selectedValues){
        return analysisdao.getAdvType(startDate, endDate, selectedValues);
    }

    public List<String> getcodeNm(){
        return analysisdao.getcodeNm();
    }

    public List<AnalysisVO> getLightType(String startDate, String endDate, List<String> selectedValues){
        return analysisdao.getLightType(startDate, endDate, selectedValues);
    }

    public List<String> getLightcodeNm(){ return analysisdao.getLightcodeNm(); }

    public List<AnalysisVO> getResult(String startDate, String endDate, List<String> selectedValues){
        return analysisdao.getResult(startDate, endDate, selectedValues);
    }

    public List<String> getResultCodeNm(){
        return analysisdao.getResultCodeNm();
    }

    public List<AnalysisVO> getFlag(String startDate, String endDate, List<String> selectedValues){
        return analysisdao.getFlag(startDate, endDate, selectedValues);
    }

    public List<String> getFlagCodeNm() {
        return analysisdao.getFlagCodeNm();
    }
}

package com.oasm.controller;

import com.oasm.domain.YearVO;
import com.oasm.service.YearService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;

@Slf4j
@Controller
@RequestMapping(value = "/yAnalysis")
@RequiredArgsConstructor
public class YearController {

    private final YearService yearService;

    // 페이지에 연도, 건수 구현부
    // 연도별 광고물 종류 통계
    @GetMapping("/yearAdvType")
    public String yearAdvTypeData(Model model, YearVO yearVO) {

        yearVO.setSign_kind("Adkind");
        int[] years = yearService.years();
        int[] num = yearService.yearAdvTypeSearchButton(yearVO);
        int[] reverseYears = new int[10];
        for (int i = years.length - 1, j = 0; i >= 0; i--, j++) {
            reverseYears[j] = years[i];
        }

        model.addAttribute("years", years);
        model.addAttribute("num", num);
        model.addAttribute("reverseYears", reverseYears);

        return "analysis/yearAdvType";
    }
    // 표 역정렬 구현부
    // 연도별 광고물 종류 통계
    @PostMapping("/yearAdvType")
    @ResponseBody
    public int[] yearAdvTypeTable(YearVO yearVO){
        return yearService.yearsReverse(yearVO);
    }
    // 검색버튼 구현부
    // 연도별 광고물 종류 통계
    @PostMapping("/yearAdvType/searchButton")
    @ResponseBody
    public int[] yearAdvTypeSearchButton(YearVO yearVO){
        yearVO.setSign_kind("Adkind");
        return yearService.yearAdvTypeSearchButton(yearVO);
    }
////////////////////////////////////////////////////////////////////////////
    // 페이지에 연도, 건수 구현부
    // 연도별 조명 종류 통계
    @GetMapping("/yearLightType")
    public String yearLightTypeData(Model model, YearVO yearVO) {

        yearVO.setSign_kind("LightType");
        int[] years = yearService.years();
        int[] num = yearService.yearAdvTypeSearchButton(yearVO);
        int[] reverseYears = new int[10];
        for (int i = years.length - 1, j = 0; i >= 0; i--, j++) {
            reverseYears[j] = years[i];
        }

        model.addAttribute("years", years);
        model.addAttribute("num", num);
        model.addAttribute("reverseYears", reverseYears);

        return "analysis/yearLightType";
    }
    // 표 역정렬 구현부
    // 연도별 조명 종류 통계
    @PostMapping("/yearLightType")
    @ResponseBody
    public int[] yearLightTypeTable(YearVO yearVO){
        return yearService.yearsReverse(yearVO);
    }
    // 검색버튼 구현부
    // 연도별 조명 종류 통계
    @PostMapping("/yearLightType/searchButton")
    @ResponseBody
    public int[] yearLightTypeSearchButton(YearVO yearVO){
        yearVO.setSign_kind("LightType");
        return yearService.yearAdvTypeSearchButton(yearVO);
    }
////////////////////////////////////////////////////////////////////////////
    // 페이지에 연도, 건수 구현부
    // 연도별 광고물 조사 결과 통계
    @GetMapping("/yearSurveyResult")
    public String yearSurveyResultData(Model model, YearVO yearVO) {

        yearVO.setSign_kind("Result");
        int[] years = yearService.years();
        int[] num = yearService.yearAdvTypeSearchButton(yearVO);
        int[] reverseYears = new int[10];
        for (int i = years.length - 1, j = 0; i >= 0; i--, j++) {
            reverseYears[j] = years[i];
        }

        model.addAttribute("years", years);
        model.addAttribute("num", num);
        model.addAttribute("reverseYears", reverseYears);

        return "analysis/yearSurveyResult";
    }
    // 표 역정렬 구현부
    // 연도별 광고물 조사 결과 통계
    @PostMapping("/yearSurveyResult")
    @ResponseBody
    public int[] yearSurveyResultTable(YearVO yearVO){
        return yearService.yearsReverse(yearVO);
    }
    // 검색버튼 구현부
    // 연도별 광고물 조사 결과 통계
    @PostMapping("/yearSurveyResult/searchButton")
    @ResponseBody
    public int[] yearSurveyResultSearchButton(YearVO yearVO){
        yearVO.setSign_kind("Result");
        return yearService.yearAdvTypeSearchButton(yearVO);
    }
////////////////////////////////////////////////////////////////////////////
    // 페이지에 연도, 건수 구현부
    // 도로 저촉 유무 통계
    @GetMapping("/yearRoadInvasion")
    public String yearRoadInvasionData(Model model, YearVO yearVO) {

        yearVO.setSign_kind("Flag");
        int[] years = yearService.years();
        int[] num = yearService.yearAdvTypeSearchButton(yearVO);
        int[] reverseYears = new int[10];
        for (int i = years.length - 1, j = 0; i >= 0; i--, j++) {
            reverseYears[j] = years[i];
        }

        model.addAttribute("years", years);
        model.addAttribute("num", num);
        model.addAttribute("reverseYears", reverseYears);

        return "analysis/yearRoadInvasion";
    }
    // 표 역정렬 구현부
    // 도로 저촉 유무 통계
    @PostMapping("/yearRoadInvasion")
    @ResponseBody
    public int[] yearRoadInvasionTable(YearVO yearVO){
        return yearService.yearsReverse(yearVO);
    }
    // 검색버튼 구현부
    // 도로 저촉 유무 통계
    @PostMapping("/yearRoadInvasion/searchButton")
    @ResponseBody
    public int[] yearRoadInvasionSearchButton(YearVO yearVO){
        yearVO.setSign_kind("Flag");
        return yearService.yearAdvTypeSearchButton(yearVO);
    }
}

package com.oasm.controller;


import com.oasm.dao.MonthDAO;
import com.oasm.domain.MonthTypeFormVO;
import com.oasm.domain.SurveyStateVO;
import com.oasm.service.MonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class MonthController {
    private final MonthDAO monthDAO;


    @Autowired
    public MonthController(MonthDAO monthDAO) {
        this.monthDAO = monthDAO;
    }

    @Autowired
    private MonthService monthService;


    // 월별 광고물 종류 통계
    @GetMapping("/analysis/monthAdvType")
    public String monthAdvType(Model model, String permission_date, String code_nm) {


        List<SurveyStateVO> statistics = monthDAO.monthadvtype( permission_date,code_nm);
        System.out.println("Statistics: " + statistics);

        model.addAttribute("statistics", statistics);
        return "analysis/monthAdvType";
    }

    // 월별 광고물 종류 통계 (요청)
    @PostMapping("/analysis/monthAdvType")
    public ResponseEntity<List<MonthTypeFormVO>> monthAdvTypePost(@RequestBody MonthTypeFormVO form) {
        // 서비스 메소드 호출 시, 전체 form 객체를 전달합니다.
        List<MonthTypeFormVO> result = monthService.monthadvtypeWithList(form);

        // 결과를 ResponseEntity로 감싸서 반환
        System.out.println("result" + result);
        return ResponseEntity.ok(result);
    }

    // 월별 조명 종류 통계
    @GetMapping("/analysis/monthLightType")
    public String monthLightType(Model model, String survey_date, String code_nm) {


        List<SurveyStateVO> statistics = monthDAO.monthLightType( survey_date,code_nm);
        System.out.println("Statistics: " + statistics);

        model.addAttribute("statistics", statistics);
        return "analysis/monthLightType";
    }

    // 월별 광고물 종류 통계 (요청)
    @PostMapping("/analysis/monthLightType")
    public ResponseEntity<List<MonthTypeFormVO>> monthLightTypePost(@RequestBody MonthTypeFormVO form) {
        // 서비스 메소드 호출 시, 전체 form 객체를 전달합니다.
        List<MonthTypeFormVO> result = monthService.monthLightTypeWithList(form);

        // 결과를 ResponseEntity로 감싸서 반환
        System.out.println("result" + result);
        return ResponseEntity.ok(result);
    }

    // 월별 광고물 조사 결과 통계
    @GetMapping("/analysis/monthSurveyResult")
    public String monthSurveyResult(Model model, String survey_date, String code_nm) {


        List<SurveyStateVO> statistics = monthDAO.monthSurveyResult( survey_date,code_nm);
        System.out.println("Statistics: " + statistics);

        model.addAttribute("statistics", statistics);
        return "analysis/monthSurveyResult";
    }

    // 월별 광고물 조사 결과 통계 (요청)
    @PostMapping("/analysis/monthSurveyResult")
    public ResponseEntity<List<MonthTypeFormVO>> monthSurveyResultList(@RequestBody MonthTypeFormVO form) {
        // 서비스 메소드 호출 시, 전체 form 객체를 전달합니다.
        List<MonthTypeFormVO> result = monthService.monthSurveyResultList(form);

        // 결과를 ResponseEntity로 감싸서 반환
        System.out.println("result" + result);
        return ResponseEntity.ok(result);
    }



    // 도로 저촉 유무 통계
    @GetMapping("/analysis/monthRoadInvasion")
    public String monthRoadInvasion(Model model, String survey_date, String building_cd, String road_yn) {


        List<SurveyStateVO> statistics = monthDAO.monthRoadInvasion( survey_date, building_cd, road_yn);
        System.out.println("Statistics: " + statistics);

        model.addAttribute("statistics", statistics);
        return "analysis/monthRoadInvasion";
    }

    // 도로 저촉 유무 통계(요청)
    @PostMapping("/analysis/monthRoadInvasion")
    public ResponseEntity<List<MonthTypeFormVO>> monthRoadInvasionList(@RequestBody MonthTypeFormVO form) {
        // 서비스 메소드 호출 시, 전체 form 객체를 전달합니다.
        List<MonthTypeFormVO> result = monthService.monthRoadInvasionList(form);

        // 결과를 ResponseEntity로 감싸서 반환
        System.out.println("result" + result);
        return ResponseEntity.ok(result);
    }




}
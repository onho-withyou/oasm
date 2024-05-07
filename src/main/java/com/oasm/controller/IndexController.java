package com.oasm.controller;


import com.oasm.domain.InquirySignboardVO;
import com.oasm.domain.SurveyStateVO;
import com.oasm.service.InquirySignboardService;
import com.oasm.service.SurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final SurveyService surveyService;
    private final InquirySignboardService inquirySignboardService;

    @GetMapping(value={"/index", "/"})
    public String Index (Model m) {
        m.addAttribute("data1", surveyService.countAllSurveyResults());
        m.addAttribute("data2", surveyService.countTodaySurveyResults());
        m.addAttribute("data3", surveyService.countIllegalSurveyResults());
        m.addAttribute("data4", surveyService.countMySurveyResults("admin"));   /* 시큐리티로 변경 예정 */
        return "index";
    }

    @PostMapping("/adKind")
    @ResponseBody
    public List<InquirySignboardVO> AdKinds () {
        return inquirySignboardService.selectAllSignAdkinds();
    }

    @PostMapping("/lightType")
    @ResponseBody
    public List<InquirySignboardVO> lightTypes () {
        return inquirySignboardService.selectAllSignLightTypes();
    }

}

package com.oasm.controller;

import com.oasm.domain.AnalysisVO;
import com.oasm.service.AnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
public class AnalysisController {

    @Autowired
    private  AnalysisService analysisservice;

    /*광고물통계*/
    @GetMapping("analysis/advType")
    public String advTypePage(Model m){
        List<String> codeNm = analysisservice.getcodeNm();
        m.addAttribute("codeNm", codeNm);

        return "analysis/advType";
    }
    @PostMapping("analysis/advType")
    @ResponseBody
    public ResponseEntity<List<AnalysisVO>> getselcetAdvtype(@RequestParam(name = "startDate", required = false) String startDate ,
                                                                @RequestParam(name = "endDate", required = false) String endDate,
                                                                @RequestParam(name = "selectedItems[]") List<String> selectedItems,
                                                                Model m){

        m.addAttribute("startDate", startDate);
        m.addAttribute("endDate", endDate);
        m.addAttribute("selectedItems", selectedItems);

        List<AnalysisVO> advtypeList = analysisservice.getAdvType(startDate, endDate, selectedItems);

        log.info("Start Date: {}", startDate);
        log.info("End Date: {}", endDate);
        log.info("Selected Items: {}", selectedItems);
        log.info("Adv Type List:");
        for (AnalysisVO item : advtypeList) {
            log.info("{}", item);
        }

        return ResponseEntity.ok().body(advtypeList);
    }
    /*조명통계*/
    @GetMapping("analysis/lightType")
    public String lightTypepage(Model m){
        List<String> lightcodeNm = analysisservice.getLightcodeNm();
        m.addAttribute("lightcodeNm", lightcodeNm);
        return "analysis/lightType";
    }

    @PostMapping("analysis/lightType")
    public ResponseEntity<List<AnalysisVO>> getSelectLightType(@RequestParam(name = "startDate", required = false) String startDate ,
                                                                @RequestParam(name = "endDate", required = false) String endDate,
                                                                @RequestParam(name = "selectedItems[]") List<String> selectedItems,
                                                                Model m){
        m.addAttribute("startDate", startDate);
        m.addAttribute("endDate", endDate);
        m.addAttribute("selectedItems", selectedItems);

        List<AnalysisVO> lightTypeList = analysisservice.getLightType(startDate, endDate, selectedItems);

        log.info("Start Date: {}", startDate);
        log.info("End Date: {}", endDate);
        log.info("Selected Items: {}", selectedItems);
        log.info("Adv Type List:");
        for (AnalysisVO item : lightTypeList) {
            log.info("{}", item);
        }
        return ResponseEntity.ok().body(lightTypeList);
    }

    @GetMapping("analysis/surveyResult")
    public String resultpage(Model m){
        List<String> resultCodeNm = analysisservice.getResultCodeNm();
        m.addAttribute("resultCodeNm", resultCodeNm);
        return "analysis/surveyResult";
    }

    @PostMapping("analysis/surveyResult")
    @ResponseBody
    public ResponseEntity<List<AnalysisVO>> getResult(@RequestParam(name = "startDate", required = false) String startDate ,
                                                        @RequestParam(name = "endDate", required = false) String endDate,
                                                        @RequestParam(name = "selectedItems[]") List<String> selectedItems,
                                                        Model m){
        m.addAttribute("startDate", startDate);
        m.addAttribute("endDate", endDate);
        m.addAttribute("selectedItems", selectedItems);

        List<AnalysisVO> resultList = analysisservice.getResult(startDate, endDate, selectedItems);
        return ResponseEntity.ok().body(resultList);
    }

    @GetMapping("analysis/roadinvasion")
    public String roadInvasionPage(Model m){
        List<String> flagCodeNmList = analysisservice.getFlagCodeNm();
        m.addAttribute("FlagCodeNmList",flagCodeNmList);
        return "analysis/roadinvasion";
    }

    @PostMapping("analysis/roadinvasion")
    public ResponseEntity<List<AnalysisVO>> getFlag(@RequestParam(name = "startDate", required = false) String startDate ,
                                                    @RequestParam(name = "endDate", required = false) String endDate,
                                                    @RequestParam(name = "selectedItems[]") List<String> selectedItems,
                                                    Model m){
        m.addAttribute("startDate", startDate);
        m.addAttribute("endDate", endDate);
        m.addAttribute("selectedItems", selectedItems);

        List<AnalysisVO> flagList = analysisservice.getFlag(startDate, endDate, selectedItems);
        return ResponseEntity.ok().body(flagList);
    }

}

package com.oasm.controller;

import com.oasm.domain.CodeVO;
import com.oasm.domain.PageDTO;
import com.oasm.domain.SurveyStateVO;
import com.oasm.service.BaseService;
import com.oasm.utils.FileService;
import com.oasm.service.SurveyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService service;
    private final FileService fileService;
    private final BaseService baseService;

    @GetMapping("/stat/advSurveyStatus")
    public String surveyStatus (Model m, @Valid SurveyStateVO vo) {

        PageDTO<SurveyStateVO> dto = new PageDTO<>();
        if(vo.getPageNumber() != null) dto.setPageNumber(Integer.parseInt(vo.getPageNumber()));
        if(vo.getSize() != null) dto.setPageSize(Integer.parseInt(vo.getSize()));
        if(vo.getFirstDate() != null) {
            String date1[] =  vo.getFirstDate().split("-");
            String newFirstDate = date1[0] + date1[1] + date1[2];
            vo.setFirstDate(newFirstDate);
        }
        if(vo.getSecondDate() != null) {
            String date2[] = vo.getSecondDate().split("-");
            String newSecondDate = date2[0] + date2[1] + date2[2];
            vo.setSecondDate(newSecondDate);
        }
        vo.setOffset(dto.calculateOffset());


        List<SurveyStateVO> lists = service.selectSurveyResults(vo);
        m.addAttribute("lists", lists);
        m.addAttribute("current", dto.getPageNumber());

        dto.setTotalElements(service.countSurveyResults(vo));
        dto.setTotalPages(dto.getTotalPages());
        //PageDTO<SurveyStateVO> pgDto = new PageDTO<>(lists, dto.getPageNumber(), elements, dto.getPageSize());
        m.addAttribute("elements", dto.getTotalElements());
        m.addAttribute("totalPage", dto.getTotalPages());
        m.addAttribute("first", dto.getGroupFirstPage());
        m.addAttribute("last", dto.getGroupLastPage());

        CodeVO codeVO = new CodeVO();
        codeVO.setMain_cd("Result");
        List<CodeVO> results = baseService.selectCommonCodes(codeVO);
        m.addAttribute("results", results);

        return "stat/advSurveyStatus";
    }

    @PostMapping("/stat/advSurveyStatus")
    public List<SurveyStateVO> surveyStatus () {
        return null;
    }
/*
    @PostMapping("/file")
    @ResponseBody
    public String File (MultipartFile file) {
        fileService.fileUpload(file);
        return "success";
    }*/
}

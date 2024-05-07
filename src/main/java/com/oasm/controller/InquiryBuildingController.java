package com.oasm.controller;

import com.oasm.domain.InquiryBuildingVO;
import com.oasm.service.InquiryBuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class InquiryBuildingController {

    private final InquiryBuildingService inquiryBuildingService;

    @GetMapping("stat/inquiryBuilding")
    public String inquiryBuildingPage(){
        return "stat/inquiryBuilding";
    }
    @GetMapping("/buildingList")
    public ResponseEntity<List<InquiryBuildingVO>> getBuildingList() {
        List<InquiryBuildingVO> buildingList = inquiryBuildingService.getBuildingList();
        return ResponseEntity.ok().body(buildingList);
    }

    @GetMapping("stat/inquiryBuildingPopup")
    public String showPopup() {
        return "stat/inquiryBuildingPopup";
    }

    @PostMapping("/insertPopup")
    public ResponseEntity<String> insertPopup(@RequestBody InquiryBuildingVO inquiryBuildingVO){
        String buildingCode = inquiryBuildingService.getBuildingCode();
        inquiryBuildingVO.setBuilding_cd(buildingCode);
        inquiryBuildingService.insertBuildingList(inquiryBuildingVO);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/searchBuilding")
    public ResponseEntity<List<InquiryBuildingVO>> searchBuilding(@RequestBody InquiryBuildingVO inquiryBuildingVO,Model m){
        String keyword1 = inquiryBuildingVO.getKeyword1();
        String keyword2 = inquiryBuildingVO.getKeyword2();
        String keyword3 = inquiryBuildingVO.getKeyword3();
        String keyword4 = inquiryBuildingVO.getKeyword4();
        String keyword5 = inquiryBuildingVO.getKeyword5();
        String keyword6 = inquiryBuildingVO.getKeyword6();
        List<InquiryBuildingVO> searchResults = inquiryBuildingService.searchBuilding(keyword1, keyword2, keyword3, keyword4, keyword5,keyword6);
        m.addAttribute("searchResults", searchResults);
        return ResponseEntity.ok().body(searchResults);
    }

}

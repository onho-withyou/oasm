package com.oasm.controller;

import com.oasm.domain.BuildingEnterpriseVO;
import com.oasm.domain.InquiryBuildingVO;
import com.oasm.domain.InquiryEnterpriseVO;
import com.oasm.service.InquiryBuildingService;
import com.oasm.service.InquiryEnterpriseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class InquiryEnterpriseController {

    private final InquiryEnterpriseService inquiryEnterpriseService;
    private final InquiryBuildingService inquiryBuildingService;

    @GetMapping("stat/inquiryEnterprise")
    public String inquiryEnterprisePage(Model m){
        List<String> cm_code = inquiryEnterpriseService.getBusinessCate();
        m.addAttribute("cm_code",cm_code);
        return "stat/inquiryEnterprise";
    }

    @GetMapping("stat/inquiryEnterprisePopup")
    public String showPopup(Model m){
        List<String> cm_code = inquiryEnterpriseService.getBusinessCate();
        m.addAttribute("cm_code",cm_code);
        return "stat/inquiryEnterprisePopup";
    }

    @GetMapping("stat/inquiryEnterprisePopup2")
    public String showPopup2(){
        return "stat/inquiryEnterprisePopup2";
    }

    @GetMapping("/EnterpriseList")
    public ResponseEntity<List<InquiryEnterpriseVO>> getEnterpriseList(@RequestParam("enterprise_cd") String enterprise_cd){
        List<InquiryEnterpriseVO> enterpriseList = inquiryEnterpriseService.getEnterpriseList(enterprise_cd);
        return ResponseEntity.ok().body(enterpriseList);
    }

    @GetMapping("/Enterprise")
    public ResponseEntity<List<InquiryEnterpriseVO>> getEnterprise(){
        List<InquiryEnterpriseVO> enterprise = inquiryEnterpriseService.getEnterprise();
        return ResponseEntity.ok().body(enterprise);
    }

    @PostMapping("/searchEnterpriseList")
    public ResponseEntity<List<InquiryEnterpriseVO>> searchEnterpriseList(@RequestBody InquiryEnterpriseVO inquiryEnterpriseVO, Model m) {
        String keyword1 = inquiryEnterpriseVO.getKeyword1();
        String keyword2 = inquiryEnterpriseVO.getKeyword2();
        String keyword3 = inquiryEnterpriseVO.getKeyword3();
        List<InquiryEnterpriseVO> searchEnterprise = inquiryEnterpriseService.searchEnterpriseList(keyword1, keyword2, keyword3);
        m.addAttribute("searchEnterprise", searchEnterprise);
        return ResponseEntity.ok().body(searchEnterprise);
    }



    @PostMapping("/insertEnterprise")
    public ResponseEntity<String> insertEnterprise(@RequestBody InquiryEnterpriseVO inquiryEnterpriseVO, InquiryBuildingVO inquiryBuildingVO, BuildingEnterpriseVO buildingEnterpriseVO) {
        String building_cd = inquiryEnterpriseVO.getBuilding_cd();
        boolean buildingExists = inquiryEnterpriseService.checkBuildingCode(building_cd);

        // building_list 가 없을 때
        if (!buildingExists) {
            // building_list insert
            String buildingCode = inquiryEnterpriseService.getBuildingCode();
            inquiryEnterpriseVO.setBuilding_cd(buildingCode);
            inquiryEnterpriseService.insertBuilding(inquiryEnterpriseVO);

            // enterprise_list insert
            String enterpriseCode = inquiryEnterpriseService.getEnterpriseCode();
            inquiryEnterpriseVO.setEnterprise_cd(enterpriseCode);
            inquiryEnterpriseService.insertEnterprise(inquiryEnterpriseVO);

            // building_enter_list insert
            buildingEnterpriseVO.setBuilding_cd(buildingCode);
            buildingEnterpriseVO.setEnterprise_cd(enterpriseCode);
            buildingEnterpriseVO.setSeq(inquiryEnterpriseService.getSeq(buildingCode));
            inquiryEnterpriseService.insertBuildingEnterprise(buildingEnterpriseVO);
        } else {
            String enterpriseCode = inquiryEnterpriseService.getEnterpriseCode();

            // enterprise_list insert
            inquiryEnterpriseVO.setEnterprise_cd(enterpriseCode);
            inquiryEnterpriseService.insertEnterprise(inquiryEnterpriseVO);
            // building_enter_list insert
            Integer seq = inquiryEnterpriseService.getSeq(building_cd);
            buildingEnterpriseVO.setBuilding_cd(building_cd);
            buildingEnterpriseVO.setEnterprise_cd(enterpriseCode);
            buildingEnterpriseVO.setSeq(seq);
            inquiryEnterpriseService.insertBuildingEnterprise(buildingEnterpriseVO);

        }

        if (!buildingExists) {
            return ResponseEntity.ok("Inserted building and enterprise information.");
        } else {
            return ResponseEntity.ok("Inserted enterprise information.");
        }
    }

}

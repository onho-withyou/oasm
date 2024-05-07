package com.oasm.controller;

import com.oasm.domain.CodeVO;
import com.oasm.domain.InquirySignboardVO;
import com.oasm.domain.PageDTO;
import com.oasm.service.BaseService;
import com.oasm.service.InquirySignboardService;
import com.oasm.utils.FileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class InquirySignboardController {

    private final InquirySignboardService service;
    private final BaseService baseService;
    private final FileService fileService;

    @GetMapping("/stat/inquirySignboard")
    public String inquirySignboard (Model m, InquirySignboardVO vo) {

        PageDTO<InquirySignboardVO> dto = new PageDTO<>();
        if(vo.getPageNumber() != null) dto.setPageNumber((Integer.parseInt(vo.getPageNumber())));
        if(vo.getSize() != null) dto.setPageSize(Integer.parseInt(vo.getSize()));
        vo.setOffset(dto.calculateOffset());

        List<InquirySignboardVO> signs = service.selectSignboards(vo);
        m.addAttribute("signs", signs);

        CodeVO codeVO = new CodeVO();

        codeVO.setMain_cd("Adkind");
        m.addAttribute("adKind", baseService.selectCommonCodes(codeVO));

        codeVO.setMain_cd("BusinessCate");
        m.addAttribute("job", baseService.selectCommonCodes(codeVO));

        codeVO.setMain_cd("LightType");
        m.addAttribute("lightType", baseService.selectCommonCodes(codeVO));

        dto.setTotalElements(service.countAllSignboards(vo));
        dto.setTotalPages(dto.getTotalPages());
        m.addAttribute("elements", dto.getTotalElements());
        m.addAttribute("totalPage", dto.getTotalPages());
        m.addAttribute("current", dto.getPageNumber());
        m.addAttribute("first", dto.getGroupFirstPage());
        m.addAttribute("last", dto.getGroupLastPage());


        return "stat/inquirySignboard";
    }

    @GetMapping("/stat/inquirySignboardPopup")
    public String inquirySignboardPopup (Model m) {

        CodeVO codeVO = new CodeVO();

        codeVO.setMain_cd("Maintenance");
        m.addAttribute("lightType", baseService.selectCommonCodes(codeVO));

        codeVO.setMain_cd("Adkind");
        m.addAttribute("lightType", baseService.selectCommonCodes(codeVO));

        codeVO.setMain_cd("LightType");
        m.addAttribute("lightType", baseService.selectCommonCodes(codeVO));

        return "stat/inquirySignboardPopup";
    }

    @PostMapping("/stat/insertSignboard")
    @ResponseBody
    @Transactional
    public ResponseEntity<?> insertSignboard (InquirySignboardVO vo, MultipartFile file) {
        try {
            String code = service.selectLastSignCode().substring(3);
            String newCode = "SGN" + String.format("%08d", Integer.parseInt(code) + 1);

            vo.setSign_cd(newCode);
            if (vo.getEnterprise_cd() == null || vo.getEnterprise_cd().isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Error: Enterprise code is empty");
            }

            String newName = fileService.fileUpload(file);
            vo.setFile_nm(newName);
            if (service.insertSignboard(vo) > 0){
                return ResponseEntity.ok().body("Data processed successfully");
            } else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error: An error occurred while processing your request");
            }

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: An error occurred while processing your request");
        }

    }

    @GetMapping("/stat/inquirySignboardPopup2")
    public String inquirySignboardPopup2 (Model m) {
        m.addAttribute("enters", service.selectAllEnterprises());
        return "stat/inquirySignboardPopup2";
    }

}

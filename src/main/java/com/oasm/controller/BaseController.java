package com.oasm.controller;

import com.oasm.domain.CodeVO;
import com.oasm.service.BaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/base")
public class BaseController {

    private final BaseService service;

    @GetMapping("/commonCode")
    public String commonCode (Model m, CodeVO vo){
        List<CodeVO> mains = service.selectMainCodes();
        if (vo.getMain_cd() != null && vo.getMain_cd().equals("All")){
            vo.setMain_cd("");
        }

        List<CodeVO> codes = service.selectCommonCodes(vo);

        m.addAttribute("mains", mains);
        m.addAttribute("codes", codes);
        return "base/commonCode";
    }

    @GetMapping("/commonCodePopup")
    public String commonCodePopup (Model m,
                                   @RequestParam(name="main", required = false) String main,
                                   @RequestParam(name="sub", required = false) String sub) {
        List<CodeVO> mains = service.selectMainCodes();

        if (main != null && sub != null){
            CodeVO code = service.selectCommonCode(main, sub);
            m.addAttribute("code", code);
        }

        m.addAttribute("mains", mains);
        return "base/commonCodePopup";
    }

    @Transactional
    @PostMapping("/commonCodePopup")
    @ResponseBody
    public int commonCodePopup (@RequestBody CodeVO vo) {

        log.info(vo.toString());

        /**
         * count : 이 main code가 DB 내에 이미 존재하면 1, 없을 경우 0
         * result : 쿼리 수행 결과로 수행된 경우 1, 수행되지 않으면 0
         */

        int count = service.countCommonCode(vo.getMain_cd());
        int result = 0;

        if (count == 0) {
            result = service.insertCommonCode(vo.getMain_cd(), vo.getCode_nm(), "*", "0", "", "admin");
            if (vo.getSub_cd() != null) {
                service.insertCommonCode(vo.getMain_cd(), vo.getSub_nm(), vo.getSub_cd(), "1", vo.getRmk(), "admin");
            }
        } else if (count > 0) {
            int newCount = service.countCommonFullCode(vo.getMain_cd(), vo.getSub_cd());
            if (newCount > 0){
                result = service.updateCommonCode(vo.getMain_cd(), vo.getSub_cd(), vo.getSub_nm(), vo.getRmk(), "admin");
                log.info("main : " + vo.getMain_cd() + "/ name : " + vo.getSub_nm() + "/ code : " + vo.getSub_cd());
                log.info("result ::: " + result);
            } else {
                result = service.insertCommonCode(vo.getMain_cd(), vo.getSub_nm(), vo.getSub_cd(), Integer.toString(count), vo.getRmk(), "admin");
            }
        }


        return result;
    }

}

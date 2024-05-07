package com.oasm.service;

import com.oasm.dao.BaseDAO;
import com.oasm.domain.CodeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseService {

    private final BaseDAO dao;


    public int insertCommonCode (String main, String sub, String code, String seq, String rmk, String id) {
        return dao.insertCommonCode(main, sub, code, seq, rmk, id);
    }

    public CodeVO selectCommonCode (String main, String sub) { return dao.selectCommonCode(main, sub); }

    public List<CodeVO> selectCommonCodes (CodeVO vo) {
        return dao.selectCommonCodes(vo);
    }

    public List<CodeVO> selectMainCodes () {
        return dao.selectMainCodes();
    }

    public int countCommonCode (String main) { return dao.countCommonCode(main); }

    public int countCommonFullCode (String main, String sub) {
        return dao.countCommonFullCode(main, sub);
    }

    public int updateCommonCode (String main, String sub, String code, String rmk, String id) {
        return dao.updateCommonCode(main, sub, code, rmk, id);
    }



}

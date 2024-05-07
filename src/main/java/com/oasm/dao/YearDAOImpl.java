package com.oasm.dao;

import com.oasm.domain.YearVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class YearDAOImpl implements YearDAO {

    private final SqlSession sqlSession;

    @Override
    public List<YearVO> typeOfSign(YearVO yearVO) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("year", yearVO.getYear());
        paramMap.put("code_nm", yearVO.getCode_nm());

        return sqlSession.selectList("typeOfSign", paramMap);
    }

    @Override
    public List<YearVO> typeOfLight(YearVO yearVO) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("year", yearVO.getYear());
        paramMap.put("code_nm", yearVO.getCode_nm());

        return sqlSession.selectList("typeOfLight", paramMap);
    }

    @Override
    public List<YearVO> typeOfResult(YearVO yearVO) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("year", yearVO.getYear());
        paramMap.put("code_nm", yearVO.getCode_nm());

        return sqlSession.selectList("typeOfResult", paramMap);
    }

    @Override
    public List<YearVO> typeOfRoad(YearVO yearVO) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("year", yearVO.getYear());
        paramMap.put("code_nm", yearVO.getCode_nm());

        return sqlSession.selectList("typeOfRoad", paramMap);
    }

}

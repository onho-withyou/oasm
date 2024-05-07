package com.oasm.dao;

import com.oasm.domain.CodeVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BaseDAO {

    Integer insertCommonCode (String main, String sub, String code, String seq, String rmk, String id);
    CodeVO selectCommonCode (String main, String sub);
    List<CodeVO> selectCommonCodes (CodeVO vo);
    List<CodeVO> selectMainCodes ();
    Integer countCommonCode (String main);
    Integer countCommonFullCode (String main, String sub);
    Integer updateCommonCode (String main, String sub, String code, String rmk, String id);

}

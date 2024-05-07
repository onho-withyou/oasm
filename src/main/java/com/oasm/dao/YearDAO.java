package com.oasm.dao;

import com.oasm.domain.YearVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface YearDAO {

    List<YearVO> typeOfSign(YearVO yearVO);
    List<YearVO> typeOfLight(YearVO yearVO);
    List<YearVO> typeOfResult(YearVO yearVO);
    List<YearVO> typeOfRoad(YearVO yearVO);

}

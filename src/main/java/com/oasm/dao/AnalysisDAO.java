package com.oasm.dao;

import com.oasm.domain.AnalysisVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnalysisDAO {
    List<AnalysisVO> getAdvType(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                @Param("selectedItems") List<String> selectedItems);

    List<String> getcodeNm();

    List<AnalysisVO> getLightType(@Param("startDate") String startDate,@Param("endDate") String endDate,
                                    @Param("selectedItems") List<String> selectedItems);

    List<String> getLightcodeNm();

    List<AnalysisVO> getResult(@Param("startDate") String startDate,@Param("endDate") String endDate,
                                @Param("selectedItems") List<String> selectedItems);

    List<String> getResultCodeNm();

    List<AnalysisVO> getFlag(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                @Param("selectedItems") List<String> selectedItems);

    List<String> getFlagCodeNm();
}



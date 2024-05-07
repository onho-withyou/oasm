package com.oasm.dao;

import com.oasm.domain.InquiryBuildingVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InquiryBuildingDAO {
    Integer insertBuildingList(InquiryBuildingVO inquiryBuildingVO);

    String getBuildingCode();

    List<InquiryBuildingVO> getBuildingList();

    List<InquiryBuildingVO> searchBuilding(String keyword1, String keyword2, String keyword3, String keyword4, String keyword5, String keyword6);

    Integer checkBuildingCode(String building_cd);


}

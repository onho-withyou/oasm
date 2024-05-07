package com.oasm.dao;

import com.oasm.domain.BuildingEnterpriseVO;
import com.oasm.domain.InquiryBuildingVO;
import com.oasm.domain.InquiryEnterpriseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InquiryEnterpriseDAO {

    List<String> getBusinessCate();

    List<InquiryEnterpriseVO> getEnterpriseList(@Param("enterprise_cd") String enterprise_cd);

    List<InquiryEnterpriseVO> getEnterprise();

    List<InquiryEnterpriseVO> searchEnterpriseList(String keyword1, String keyword2, String keyword3);

    String getEnterpriseCode();

    Integer insertEnterprise(InquiryEnterpriseVO inquiryEnterpriseVO);

    Integer insertBuildingEnterprise(BuildingEnterpriseVO buildingEnterpriseVO);

    Integer getSeq(String building_cd);

    Integer insertBuilding(InquiryEnterpriseVO inquiryEnterpriseVO);

    String getBuildingCode();

}

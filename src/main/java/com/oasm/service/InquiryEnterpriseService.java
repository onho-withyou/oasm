package com.oasm.service;

import com.oasm.dao.InquiryBuildingDAO;
import com.oasm.dao.InquiryEnterpriseDAO;
import com.oasm.domain.BuildingEnterpriseVO;
import com.oasm.domain.InquiryEnterpriseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InquiryEnterpriseService {

    private final InquiryEnterpriseDAO inquiryEnterpriseDAO;
    private final InquiryBuildingDAO inquiryBuildingDAO;

    public List<String> getBusinessCate(){
        return inquiryEnterpriseDAO.getBusinessCate();
    }

    public List<InquiryEnterpriseVO> getEnterpriseList(String enterprise_cd){
        return inquiryEnterpriseDAO.getEnterpriseList(enterprise_cd);
    }

    public List<InquiryEnterpriseVO> getEnterprise(){
        return inquiryEnterpriseDAO.getEnterprise();
    }

    public List<InquiryEnterpriseVO> searchEnterpriseList(String keyword1, String keyword2, String keyword3){
        return inquiryEnterpriseDAO.searchEnterpriseList(keyword1,keyword2,keyword3);
    }

    public String getEnterpriseCode() {
        String maxEnterpriseCode = inquiryEnterpriseDAO.getEnterpriseCode();
        int nextNumber = 1;
        if (maxEnterpriseCode != null && maxEnterpriseCode.length() > 3) {
            try {
                nextNumber = Integer.parseInt(maxEnterpriseCode.substring(3)) + 1;
            } catch (NumberFormatException e) {
                log.error("Failed to parse building code : {}", e.getMessage());
            }
        }
        return "ENT" + String.format("%08d", nextNumber);
    }

    public Integer insertEnterprise(InquiryEnterpriseVO inquiryEnterpriseVO){
        return inquiryEnterpriseDAO.insertEnterprise(inquiryEnterpriseVO);
    }
    public Integer insertBuildingEnterprise(BuildingEnterpriseVO buildingEnterpriseVO){

        return inquiryEnterpriseDAO.insertBuildingEnterprise(buildingEnterpriseVO);
    }

    public Integer insertBuilding(InquiryEnterpriseVO inquiryEnterpriseVO){
        return inquiryEnterpriseDAO.insertBuilding(inquiryEnterpriseVO);
    }
    public String getBuildingCode(){
        String maxBuildingCode = inquiryBuildingDAO.getBuildingCode();
        int nextNumber = 1;
        if(maxBuildingCode != null && maxBuildingCode.length() > 3){
            try{
                nextNumber = Integer.parseInt(maxBuildingCode.substring(3)) + 1;
            } catch (NumberFormatException e){
                log.error("Failed to parse building code : {}",e.getMessage());
            }
        }
        return "BDN" + String.format("%08d",nextNumber);
    }


    public Boolean checkBuildingCode(String building_cd){
        Integer count = inquiryBuildingDAO.checkBuildingCode(building_cd);
        return count != null && count > 0;
    }

    public Integer getSeq(String building_cd){
        // 건물별 엔터프라이즈 수를 세는 쿼리 실행
        Integer enterpriseCount = inquiryEnterpriseDAO.getSeq(building_cd);
        // 새로운 엔터프라이즈의 순번은 기존 엔터프라이즈 수 + 1
        return enterpriseCount + 1;
    }




}

package com.oasm.service;

import com.oasm.dao.InquiryBuildingDAO;
import com.oasm.domain.InquiryBuildingVO;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InquiryBuildingService {

    private final InquiryBuildingDAO inquiryBuildingDAO;

    public int insertBuildingList(InquiryBuildingVO inquiryBuildingVO){
        return inquiryBuildingDAO.insertBuildingList(inquiryBuildingVO);
    }
    // building_cd 생성
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

    public List<InquiryBuildingVO> getBuildingList(){
        return inquiryBuildingDAO.getBuildingList();
    }

    public List<InquiryBuildingVO> searchBuilding(String keyword1, String keyword2, String keyword3, String keyword4, String keyword5, String keyword6){
        return inquiryBuildingDAO.searchBuilding(keyword1,keyword2,keyword3,keyword4,keyword5,keyword6);
    }



}

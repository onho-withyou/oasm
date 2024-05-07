package com.oasm.service;

import com.oasm.domain.RegisterStatusVO;
import com.oasm.dao.RegisterStatusDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
public class RegisterStatusService {

    private static final Logger logger = LoggerFactory.getLogger(RegisterStatusService.class);

    @Autowired
    private RegisterStatusDAO registerStatusDAO;

    @Transactional
    public void addRegisterStatus(List<RegisterStatusVO> registerStatusVOList) {
        for (RegisterStatusVO vo : registerStatusVOList) {
            try {
                // 로깅 강화
                logger.info("Before insert - road_yn: '{}'", vo.getRoadYn());
                logger.info("Adding RegisterStatus: {}", vo);
                logger.debug("RoadYn byte representation: {}", Arrays.toString(vo.getRoadYn().getBytes(StandardCharsets.UTF_8)));

                // road_yn 값을 trim 처리
                vo.setRoadYn(vo.getRoadYn().trim());
                logger.info("Ready to insert with road_yn: '{}'", vo.getRoadYn());

                // Insert 호출
                registerStatusDAO.insertRegisterStatus(vo);
                logger.info("Successfully inserted RegisterStatus for enterprise_nm: '{}'", vo.getEnterpriseNm());
            } catch (Exception e) {
                Throwable rootCause = e;
                while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
                    rootCause = rootCause.getCause();
                }
                logger.error("Error inserting RegisterStatus - enterprise_nm: '{}', survey_date: '{}', sign_width: '{}', sign_length: '{}', lot_number_adr: '{}', street_adr: '{}', street_no1: '{}', street_no2: '{}', sigungu_adr: '{}', survey_result: '{}', sign_kind: '{}', light_kind: '{}', road_yn: '{}', Root Cause: {}",
                        vo.getEnterpriseNm(), vo.getSurveyDate(), vo.getSignWidth(), vo.getSignLength(), vo.getLotNumberAdr(), vo.getStreetAdr(), vo.getStreetNo1(), vo.getStreetNo2(), vo.getSigunguAdr(), vo.getSurveyResult(), vo.getSignKind(), vo.getLightKind(), vo.getRoadYn(), rootCause.getMessage());
                throw e;
            }
        }
    }


    @Transactional
    public void batchInsertRegisterStatuses(List<RegisterStatusVO> registerStatusList) {
        // 실제 배치 처리를 수행하는 로직
        registerStatusDAO.batchInsertRegisterStatuses(registerStatusList);
    }
}
package com.oasm.dao;


import com.oasm.dao.RegisterStatusDAO;
import com.oasm.domain.RegisterStatusVO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegisterStatusDAOImpl implements RegisterStatusDAO {

    private final Logger logger = LoggerFactory.getLogger(RegisterStatusDAOImpl.class);

    private final SqlSessionFactory sqlSessionFactory;

    @Autowired
    public RegisterStatusDAOImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insertRegisterStatus(RegisterStatusVO registerStatusVO) {
        // 데이터베이스에 저장되기 전에 값을 로깅
        logger.info("Inserting RegisterStatus impl - enterprise_nm: '{}', survey_date: '{}', sign_width: '{}', sign_length: '{}', lot_number_adr: '{}', street_adr: '{}', street_no1: '{}', street_no2: '{}', sigungu_adr: '{}', survey_result: '{}', sign_kind: '{}', light_kind: '{}', road_yn: '{}'",
                registerStatusVO.getEnterpriseNm(), registerStatusVO.getSurveyDate(), registerStatusVO.getSignWidth(), registerStatusVO.getSignLength(), registerStatusVO.getLotNumberAdr(), registerStatusVO.getStreetAdr(), registerStatusVO.getStreetNo1(), registerStatusVO.getStreetNo2(), registerStatusVO.getSigunguAdr(), registerStatusVO.getSurveyResult(), registerStatusVO.getSignKind(), registerStatusVO.getLightKind(), registerStatusVO.getRoadYn());

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.insert("com.oasm.dao.RegisterStatusDAO.insertRegisterStatus", registerStatusVO);
            sqlSession.commit();
            logger.info("Successfully inserted RegisterStatus - enterprise_nm: '{}'", registerStatusVO.getEnterpriseNm());
        } catch (Exception e) {
            logger.error("Error inserting RegisterStatus impl - enterprise_nm: '{}', survey_date: '{}', sign_width: '{}', sign_length: '{}', lot_number_adr: '{}', street_adr: '{}', street_no1: '{}', street_no2: '{}', sigungu_adr: '{}', survey_result: '{}', sign_kind: '{}', light_kind: '{}', road_yn: '{}'",
                    registerStatusVO.getEnterpriseNm(), registerStatusVO.getSurveyDate(), registerStatusVO.getSignWidth(), registerStatusVO.getSignLength(), registerStatusVO.getLotNumberAdr(), registerStatusVO.getStreetAdr(), registerStatusVO.getStreetNo1(), registerStatusVO.getStreetNo2(), registerStatusVO.getSigunguAdr(), registerStatusVO.getSurveyResult(), registerStatusVO.getSignKind(), registerStatusVO.getLightKind(), registerStatusVO.getRoadYn(), e);
            throw e;
        }
    }

    @Override
    public void excelInsertRegisterStatus(RegisterStatusVO registerStatusVO) {
        // 데이터베이스에 저장되기 전에 값을 로깅
        logger.info("Excel Inserting RegisterStatus - enterprise_nm: '{}', survey_date: '{}', sign_width: '{}', sign_length: '{}', lot_number_adr: '{}', street_adr: '{}', street_no1: '{}', street_no2: '{}', sigungu_adr: '{}', survey_result: '{}', sign_kind: '{}', light_kind: '{}', road_yn: '{}'",
                registerStatusVO.getEnterpriseNm(), registerStatusVO.getSurveyDate(), registerStatusVO.getSignWidth(), registerStatusVO.getSignLength(), registerStatusVO.getLotNumberAdr(), registerStatusVO.getStreetAdr(), registerStatusVO.getStreetNo1(), registerStatusVO.getStreetNo2(), registerStatusVO.getSigunguAdr(), registerStatusVO.getSurveyResult(), registerStatusVO.getSignKind(), registerStatusVO.getLightKind(), registerStatusVO.getRoadYn());

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.insert("com.oasm.mapper.RegisterStatusMapper.excelInsertRegisterStatus", registerStatusVO);
            sqlSession.commit();
            logger.info("Successfully Excel inserted RegisterStatus - enterprise_nm: '{}'", registerStatusVO.getEnterpriseNm());
        } catch (Exception e) {
            logger.error("Error Excel inserting RegisterStatus - enterprise_nm: '{}', survey_date: '{}', sign_width: '{}', sign_length: '{}', lot_number_adr: '{}', street_adr: '{}', street_no1: '{}', street_no2: '{}', sigungu_adr: '{}', survey_result: '{}', sign_kind: '{}', light_kind: '{}', road_yn: '{}'",
                    registerStatusVO.getEnterpriseNm(), registerStatusVO.getSurveyDate(), registerStatusVO.getSignWidth(), registerStatusVO.getSignLength(), registerStatusVO.getLotNumberAdr(), registerStatusVO.getStreetAdr(), registerStatusVO.getStreetNo1(), registerStatusVO.getStreetNo2(), registerStatusVO.getSigunguAdr(), registerStatusVO.getSurveyResult(), registerStatusVO.getSignKind(), registerStatusVO.getLightKind(), registerStatusVO.getRoadYn(), e);
            throw e;
        }
    }

    @Override
    public void batchInsertRegisterStatuses(List<RegisterStatusVO> registerStatusList) {
        for (RegisterStatusVO registerStatusVO : registerStatusList) {
            insertRegisterStatus(registerStatusVO);
        }
    }
}
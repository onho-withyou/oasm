package com.oasm.domain;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class RegisterStatusVO {

    // enterprise_list // 상호명(업체명)
    private String enterprise_nm;

    // survey_state // 조사일자 (연+월+일 8자리)
    private String survey_date;

    // sign_list // 광고물 가로
    private BigDecimal sign_width;

    // sign_list // 광고물 세로
    private BigDecimal  sign_length;

    // building_list // 지번 주소
    private String lot_number_adr;

    // building_list // 도로명 주소
    private String street_adr;

    // building_list // 건물번호1 (주번호)
    private String street_no1;

    // building_list // 건물번호2 (주번호)
    private String street_no2;

    // building_list // 시군구
    private String sigungu_adr;

    // survey_state // 전수조사결과
    private String survey_result;

    // sign_list // 광고물 종류
    private String sign_kind;

    // sign_list // 조명 종류
    private String light_kind;

    // building_list // 도로저촉 유무
    private String road_yn;

    public void setEnterpriseNm(String enterpriseNm) {

        this.enterprise_nm = enterpriseNm;
    }

    public String getEnterpriseNm() {
        return enterprise_nm;
    }

    public void setSurveyResult(String surveyResult) {
        this.survey_result = surveyResult;
    }

    public String getSurveyResult() {
        return survey_result;
    }


    public void setSurveyDate(String surveyDate) {
        this.survey_date = surveyDate;
    }

    public String getSurveyDate() {
        return survey_date;
    }


    public void setLotNumberAdr(String lotNumberAdr) {
        this.lot_number_adr = lotNumberAdr;
    }

    public String getLotNumberAdr() {
        return lot_number_adr;
    }

    public void setStreetAdr(String streetAdr) {
        this.street_adr = streetAdr;
    }

    public String getStreetAdr() {
        return street_adr;
    }

    public void setStreetNo1(String streetNo1) {
        this.street_no1 = streetNo1;
    }

    public String getStreetNo1() {
        return street_no1;
    }

    public void setStreetNo2(String streetNo2) {
        this.street_no2 = streetNo2;
    }

    public String getStreetNo2() {
        return street_no2;
    }

    public void setSigunguAdr(String sigunguAdr) {
        this.sigungu_adr = sigunguAdr;
    }

    public String getSigunguAdr() {
        return sigungu_adr;
    }
    public void setSignWidth(BigDecimal  signWidth) {
        this.sign_width = signWidth;
    }

    public BigDecimal  getSignWidth() {
        return sign_width;
    }

    public void setSignLength(BigDecimal  signLength) {
        this.sign_length = signLength;
    }

    public BigDecimal  getSignLength() {
        return sign_length;
    }
    public void setRoadYn(String roadYn) {
        if (roadYn != null && roadYn.length() == 1 && ("Y".equals(roadYn) || "N".equals(roadYn))) {
            this.road_yn = roadYn;
        } else {
            throw new IllegalArgumentException("Invalid road_yn value: " + roadYn);
        }
    }

    public void setSignKind(String signKind) {
        this.sign_kind = signKind;
    }

    public String getSignKind() {
        return sign_kind;
    }

    public void setLightKind(String lightKind) {
        this.light_kind = lightKind;
    }

    public String getLightKind() {
        return light_kind;
    }

    public String getRoadYn() {
        return road_yn;
    }

}
/*

  */
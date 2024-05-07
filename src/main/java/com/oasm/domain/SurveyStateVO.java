package com.oasm.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SurveyStateVO {

    // 조사번호 (조사일자 6자리+당일순번 6자리+간판코드 뒤 8자리)
    private String survey_no;

    // 간판코드 (SGN + 숫자 8자리)
    private String sign_cd;

    // 조사일자 (연+월+일 8자리)
    private String survey_date;

    // 전수조사 결과
    private String survey_result;

    // 신고
    private String survey_report;

    // 전수조사 판별
    private String survey_distinct;

    // 비고
    private String rmk;

    // 사용여부
    private String survey_flag_no;

    // 최초 입력자
    private String insert_id;

    // 최초 입력 일자
    private Date insert_date;

    // 최종 수정자
    private String update_id;

    // 최종 수정 일자
    private Date update_date;

    // 기타 (추가 컬럼용)
    private String etc;


    private String permission_date;

    private String code_nm;

    private String building_cd;
    private String road_yn;
    private String file_nm;

    private String firstDate;
    private String secondDate;

    private String pageNumber;
    private String size = "10";
    private int offset;

    @Override
    public String toString() {
        return "SurveyStateVO{" +
                ", building_cd='" + building_cd + '\'' +
                ", road_yn='" + road_yn + '\'' +
                ", survey_date='" + survey_date + '\'' +
                ", code_nm='" + code_nm + '\'' +
                
                '}';
    }
}
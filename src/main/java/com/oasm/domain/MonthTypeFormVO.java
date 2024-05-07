package com.oasm.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Data
public class MonthTypeFormVO {
    private String survey_date;
    private List<String> code_nm; // 사용자가 선택한 광고물 종류 코드 목록
    private String building_cd;
    private List<String> road_yn;

    private String startDate;
    private String endDate;

    // MonthAdvTypeFormVO 클래스는 사용자로부터 특정 동작(예: 특정 기간 내의 광고물 종류별 통계 조회)에 필요한 입력 데이터를 받기 위해 만들어진 것
    // 라이언트 측에서 서버로 전송되는 데이터를 구조화하고, 이 데이터를 효과적으로 처리하기 위해
    // 사용자가 선택한 특정 기간(permission_date)과 하나 이상의 광고물 종류(code_nm)에 대한 정보를 포함하는 데 사용됩니다. 이 정보는 사용자가 웹 인터페이스에서 선택한 입력값을 기반



}
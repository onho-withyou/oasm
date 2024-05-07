package com.oasm.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class AnalysisVO {
    private String sign_kind;
    private String sub_cd;
    private String code_nm;
    private String main_cd;
    private String survey_date;
    private int count;
    private String startDate;
    private String endDate;
    private List<String> selectedItems;
    private String road_yn;
    private String building_cd;
    private String enterprise_cd;

}

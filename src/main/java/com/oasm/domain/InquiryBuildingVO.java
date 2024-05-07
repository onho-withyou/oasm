package com.oasm.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InquiryBuildingVO {
    private String building_cd;
    private String sigungu_adr;
    private String lot_number_adr;
    private String street_adr;
    private String street_no1;
    private String street_no2;
    private String building_nm;
    private String road_width;
    private String road_length;
    private String rmk;
    private String file_nm;
    private String enterprise_nm;
    private String code_nm;
    private LocalDateTime insert_date;
    private int enterprise_count; // building_cd 의 count 상호갯수
    private int data_count; // 데이터 건수
    private String keyword1; // street_adr 검색
    private String keyword2; // street_no1 검색
    private String keyword3; // street_no2 검색
    private String keyword4; // enterprise_nm 검색
    private String keyword5; // building_nm 검색
    private String keyword6;
    private String map_latitude;  //위도
    private String map_longitude; //경도
}

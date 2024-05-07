package com.oasm.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class InquiryEnterpriseVO {

    private String building_cd;
    private String enterprise_cd;
    private String enterprise_nm;
    private String add_addr;
    private String street_adr;
    private String street_no1;
    private String street_no2;
    private String code_nm;
    private String business_cate;
    private String adkind;
    private String building_nm;
    private String adkind_count;
    private String sign_cd;
    private String file_nm;
    private String permission_yn;
    private String lightType;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private String lot_number_adr;
    private String enterprise_no;
    private String enterprise_type;
    private String enterprise_rep;
    private String enterprise_rep_no;
    private String enterprise_tel_no;
    private String rmk;
    private String sigungu_adr;
    private String map_latitude;  //위도
    private String map_longitude; //경도
    private LocalDateTime insert_date;
}

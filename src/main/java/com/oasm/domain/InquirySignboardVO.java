package com.oasm.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InquirySignboardVO {

    private String sign_cd;
    private String enterprise_cd;
    private String enterprise_nm;   // 추가필드
    private String enterprise_type; // 추가필드
    private String file_nm;
    private String permission_yn;
    private String permission_no;
    private String permission_date;
    private String permission_end_date;
    private String inspect_date;
    private String sign_width;
    private String sign_length;
    private String sign_depth;
    private String sign_area;
    private String sign_rmk;
    private String sign_content;
    private String maintain_state;
    private String maintain_nm;     // 추가필드
    private String first_date;
    private String sign_kind;
    private String sign_nm;         // 추가필드
    private String light_kind;
    private String light_nm;        // 추가필드
    private String rmk;
    private String sign_flag_yn;
    private String insert_id;
    private String insert_date;
    private String update_id;
    private String update_date;
    private String code_nm;         // 추가필드

    /* 추가필드 */
    private String lot_number_adr;
    private String street_adr;
    private String street_no1;
    private String street_no2;
    private String pageNumber;
    @Builder.Default
    private String size = "10";
    private int offset;


}

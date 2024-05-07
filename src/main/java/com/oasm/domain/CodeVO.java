package com.oasm.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodeVO {

    private String main_cd;
    private String sub_cd;
    private String code_nm;
    private Integer seq;
    private String manage_cate1;
    private String manage_cate2;
    private String manage_cate3;
    private String manage_cate4;
    private String manage_cate5;
    private String cd_flag_yn;
    private String rmk;
    private String insert_id;
    private String insert_date;
    private String update_id;
    private String update_date;

    /* 추가 필드 */
    private String sub_nm;

}

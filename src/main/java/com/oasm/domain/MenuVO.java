package com.oasm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuVO {

    private String menu_cd;
    private String menu_nm;
    private String menu_lvl;
    private Integer seq;
    private String menu_high_cd;
    private String menu_flag_yn;
    private String insert_id;
    private String insert_date;
}

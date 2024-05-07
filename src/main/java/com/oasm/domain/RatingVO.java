package com.oasm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingVO {

    private String rating_cd;
    private String rating_nm;
    private String rating_flag_yn;
    private String rmk;
    private String insert_id;
    private String insert_date;

}

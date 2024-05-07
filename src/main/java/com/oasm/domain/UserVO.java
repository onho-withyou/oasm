package com.oasm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {

    private String user_id;
    private String user_pw;
    private String user_nm;
    @Builder.Default
    private String rmk = "";
    private String client_nm;
    private String user_flag_yn;
    private String insert_id;
    private String insert_date;
    private String update_id;
    private String update_date;

    /* 추가필드 */
    private String rating_cd;
    private String rating_nm;


}

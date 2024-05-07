package com.oasm.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class YearVO {

    // 간판 종류
    private String sign_kind;
    // 간판 번호
    private String sign_num;
    // 간판 이름
    private String code_nm;

    // ajax
    //정방향, 역방향
    private String order;
    // 앞 연도
    @Builder.Default
    private String year = "2015";
    // 뒤 연도
    @Builder.Default
    private String year2 = "2024";
    // 간판 모음
    @Builder.Default
    private String[] checkbox = {"All"};

}

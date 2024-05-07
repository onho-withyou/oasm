package com.oasm.domain;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class SignListVO {

    // 간판코드 (SGN + 숫자 8자리)
    private String sign_cd;

    // 업체코드 (ENT + 숫자 8자리)
    private String enterprise_cd;

    // 사진파일명
    private String file_nm;

    // 허가 유무
    private String permission_yn;

    // 광고물 허가 번호
    private String permission_no;

    // 허가일자
    private String permission_date;

    // 만료일자
    private String permission_end_date;

    // 안전도 검사 일자
    private String inspect_date;

    // 광고 내용
    private String sign_content;

    // 광고물 가로
    private BigDecimal sign_width;

    // 광고물 세로
    private BigDecimal sign_length;

    // 광고물 노ㅠ이
    private BigDecimal sign_depth;

    // 광고물 면적
    private BigDecimal sign_area;

    // 광고물 규격 기타
    private String sign_rmk;

    // 정비상태
    private String maintain_state;

    // 최초 표시일자
    private String first_date;

    // 광고물 종류
    private String sign_kind;

    // 조명 종류
    private String light_kind;

    // 비고
    private String rmk;

    // 사용여부
    private String sign_flag_yn;

    // 최초입력자
    private String insert_id;

    // 최초입력일자
    private Date insert_date;

    // 최종수정자
    private String update_id;

    // 최종수정일자
    private Date update_date;

    // 기타(추가용 컬럼)
    private String etc;
}
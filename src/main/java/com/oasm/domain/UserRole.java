package com.oasm.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum UserRole {

    A01("관리자"),
    B01("조사자"),
    B02("일반사용자"),
    C01("정리"),
    middle("중간관리자"),
    TEST("테스터")
    ;

    private final String value;
    UserRole(String value) {
        this.value = value;
    }

}

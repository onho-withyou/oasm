package com.oasm.domain;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user_list")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserList {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId = "";

    @Column(nullable = false)
    private String user_pw = "";

    @Column(nullable = false)
    private String user_nm = "";

    private String rmk = "";

    private String client_nm = "";

    @Column(columnDefinition = "CHAR", nullable = false)
    private String user_flag_yn = "";

    private String insert_id = "";

    private LocalDateTime insert_date;

    private String update_id = "";

    private LocalDateTime update_date;

    private String etc = "";

}

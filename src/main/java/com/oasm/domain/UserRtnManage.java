package com.oasm.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "user_rtn_manage")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRtnManage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String rating_cd;

    @Column(nullable = false)
    private String user_id;

    private String insert_id;

    private LocalDateTime insert_date;

    private String update_id;

    private LocalDateTime update_date;

}

package com.oasm.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "user_rating")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String rating_cd;

    private String rating_nm;

    @Column(columnDefinition = "CHAR", nullable = false)
    private String rating_flag_yn;

    private String rmk;

    private String insert_id;

    private LocalDateTime insert_date;

}

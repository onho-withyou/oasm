package com.oasm.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_list")
public class UserListDTO {

    @Builder.Default
    @Column(name = "user_id")
    private String userId = "";

    @Builder.Default
    private String user_pw = "";

    @Builder.Default
    private String user_nm = "";

    @Builder.Default
    private String rmk = "";

    @Builder.Default
    private String client_nm = "";

    @Builder.Default
    private String user_flag_yn = "";

    @Builder.Default
    private String insert_id = "";

    private LocalDateTime insert_date;

    @Builder.Default
    private String update_id = "";

    private LocalDateTime update_date;

    @Builder.Default
    private String etc = "";

}
package com.oasm.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class menuAuthManageVO {
    private String menu_cd;
    private String menu_nm;
    private String rating_cd;
    private String menu_flag_yn;
    private String insert_id;
    private String insert_update;
    private String update_id;
    private String update_date;
}

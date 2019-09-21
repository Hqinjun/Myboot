package com.hqinjun.myboot.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Sys_user_role")
public class SuserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "sys_user_id")
    private Integer sys_user_id;

    @Column(name = "sys_role_id")
    private Integer sys_role_id;

}

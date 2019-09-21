package com.hqinjun.myboot.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Sys_role_permission")
public class SrolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_id")
    private Integer role_id;

    @Column(name = "permission_id")
    private Integer permission_id;
}

package com.hqinjun.myboot.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Sys_User")
public class Suser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username",  length = 32)
    private String username;

    @Column(name = "password",  length = 32)
    private String password;
}

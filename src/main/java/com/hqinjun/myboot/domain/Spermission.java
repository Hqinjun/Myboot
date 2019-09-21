package com.hqinjun.myboot.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Sys_Permission")
public class Spermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String url;

    @Column(name = "pid")
    private Integer pid;
}

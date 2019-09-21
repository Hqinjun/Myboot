package com.hqinjun.myboot.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Sys_Role")
public class Srole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name",  length = 32)
    private String name;

}

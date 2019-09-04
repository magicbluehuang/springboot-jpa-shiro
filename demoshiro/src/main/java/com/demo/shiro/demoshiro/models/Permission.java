package com.demo.shiro.demoshiro.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "T_PERMISSION")
public class Permission {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;
}

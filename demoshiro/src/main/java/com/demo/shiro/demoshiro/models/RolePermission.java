package com.demo.shiro.demoshiro.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "T_ROLE_PERMISSION")
public class RolePermission {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pid")
    private Integer uid;

    @Column(name = "rid")
    private Integer rid;
}

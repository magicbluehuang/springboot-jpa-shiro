package com.demo.shiro.demoshiro.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_user_role")
public class UserRole {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "rid")
    private Integer rid;

}

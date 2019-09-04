package com.demo.shiro.demoshiro.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "shiro_user")
public class UserEntity {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id")
    private String id;
    @Column
    public String name;
    @Column
    public String password;
    @Column
    public String phone;
}

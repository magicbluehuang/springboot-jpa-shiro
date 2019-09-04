package com.demo.shiro.demoshiro.dao;

import com.demo.shiro.demoshiro.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity ,String> {

    UserEntity getUserEntitiesByName(@Param("name") String name);

}

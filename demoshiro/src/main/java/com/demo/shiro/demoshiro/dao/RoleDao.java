package com.demo.shiro.demoshiro.dao;

import com.demo.shiro.demoshiro.models.Permission;
import com.demo.shiro.demoshiro.models.Role;
import com.demo.shiro.demoshiro.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<Role ,String> {

    @Query(value = "select r.id,r.name,r.memo from t_role r " +
            "left join t_user_role ur on(r.id = ur.rid)" +
            "left join shiro_user u on(u.id = ur.rid)" +
            "where u.name = :name", nativeQuery = true)
    List<Role> getUserRole(@Param("name") String name);



}

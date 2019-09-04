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
public interface PermissionDao extends JpaRepository<Permission ,String> {

    @Query(value = " select p.id,p.url,p.name from t_role r" +
            " left join t_user_role ur on(r.id = ur.rid)" +
            "left join shiro_user u on(u.id = ur.rid)" +
            "left join t_role_permission rp on(rp.rid = r.id)" +
            "left join t_permission p on(p.id = rp.pid )"+
            "where u.name = 'test'", nativeQuery = true)
    List<Permission> getUserPermission(@Param("name") String name);

}

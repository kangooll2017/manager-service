package com.chilema.manager.service;

import java.util.List;

import com.chilema.manager.bean.Permission;


public interface PermissionService {
    
    //获取所有权限
    List<Permission> getAllPermissions();
    
    //获取组装好的许可(有分级关系的)
    List<Permission> getAllPermissionsPackage();

    //获取所有权限list
    List<Permission> getAllPermissionList();
    
    //添加权限的方法
    boolean add(Permission permission);

    //删除权限的方法
    boolean delete(Integer id);

    //修改权限的方法
    boolean edit(Permission permission);

}

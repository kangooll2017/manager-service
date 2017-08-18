package com.chilema.manager.service;

import java.util.List;

import com.chilema.manager.bean.Permission;


public interface ManagerPermissionService {
    
    //根据管理员的id获取他所拥有的权限，不包括pid为0的权限
    List<Permission> getPermissonByManagerId(Integer mid);
    
    //根据管理员的id获取他所拥有的权限，带分级关系的
    List<Permission> getPermissonByManagerIdPackage(Integer id);

}

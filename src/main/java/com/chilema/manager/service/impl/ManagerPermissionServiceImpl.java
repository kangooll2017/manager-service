package com.chilema.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chilema.manager.bean.Permission;
import com.chilema.manager.dao.PermissionMapper;
import com.chilema.manager.service.ManagerPermissionService;

@Service
public class ManagerPermissionServiceImpl implements ManagerPermissionService {
    
    @Autowired
    private PermissionMapper permissionMapper;
    
    /**
     * 根据管理员的id获取他所拥有的权限，不包括pid为0的权限
     */
    @Override
    public List<Permission> getPermissonByManagerId(Integer mid) {
        List<Permission> list = new ArrayList<>();
        List<Permission> permissions = permissionMapper.getPermissonByManagerId(mid);
        for (Permission permission : permissions) {
            if (permission.getPid() != 0) {
                list.add(permission);
            }
        }
        return list;
    }
    
    /**
     * 根据管理员的id获取他所拥有的权限，带分级关系的
     */
    @Override
    public List<Permission> getPermissonByManagerIdPackage(Integer mid) {
        //将要组装的list
        List<Permission> list = new ArrayList<>();
        //该管理员的所有权限
        List<Permission> permissions = permissionMapper.getPermissonByManagerIdPackage(mid);
        for (Permission Permission : permissions) {
            //组装一个父权限
            if (Permission.getPid() == 0) {
                for (Permission per : permissions) {
                    if (per.getPid() == Permission.getId()) {
                        List<Permission> childs = Permission.getChilds();
                        childs.add(per);
                    }
                }
                list.add(Permission);
            }
        }
        return list;
    }

}

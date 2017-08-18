package com.chilema.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chilema.manager.bean.Permission;
import com.chilema.manager.dao.PermissionMapper;
import com.chilema.manager.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    
    /**
     * 获取所有权限
     */
    @Override
    public List<Permission> getAllPermissions() {
        return permissionMapper.selectByExample(null);
    }
    
    /**
     * 获取所有权限，带分级关系的
     */
    @Override
    public List<Permission> getAllPermissionsPackage() {
        List<Permission> list = new ArrayList<>();
        List<Permission> permissions = permissionMapper.selectByExample(null);
        for (Permission Permission : permissions) {
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
    
    /**
     * 获取所有权限list
     */
    @Override
    public List<Permission> getAllPermissionList() {
        return permissionMapper.selectByExample(null);
    }
    
    /**
     * 添加权限的方法
     */
    @Override
    public boolean add(Permission permission) {
        int row = permissionMapper.insertSelective(permission);
        return row == 1;
    }
    
    /**
     * 删除权限的方法
     */
    @Override
    public boolean delete(Integer id) {
        int row = permissionMapper.deleteByPrimaryKey(id);
        return row == 1;
    }
    
    /**
     * 修改权限的方法
     */
    @Override
    public boolean edit(Permission permission) {
        int row = permissionMapper.updateByPrimaryKeySelective(permission);
        return row == 1;
    }

}

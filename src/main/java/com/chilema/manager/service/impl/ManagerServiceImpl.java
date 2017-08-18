package com.chilema.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chilema.commons.test.MD5Util;
import com.chilema.manager.bean.Manager;
import com.chilema.manager.bean.ManagerExample;
import com.chilema.manager.bean.ManagerExample.Criteria;
import com.chilema.manager.bean.ManagerPermissionExample;
import com.chilema.manager.dao.ManagerMapper;
import com.chilema.manager.dao.ManagerPermissionMapper;
import com.chilema.manager.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;
    
    @Autowired
    private ManagerPermissionMapper managerPermissionMapper;

    /**
     * 注册
     */
    @Override
    public boolean regist(Manager manager) {
        String pswd = MD5Util.digest(manager.getPassword());
        manager.setPassword(pswd);
        int i = managerMapper.insertSelective(manager);
        return i == 1;
    }

    /**
     * 登录
     */
    @Override
    public Manager login(Manager manager) {
        ManagerExample example = new ManagerExample();
        Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(manager.getAccount());
        criteria.andPasswordEqualTo(MD5Util.digest(manager.getPassword()));
        List<Manager> list = managerMapper.selectByExample(example);
        if (list.size() == 1) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 检查账户是否存在
     */
    @Override
    public boolean checkAccount(String account) {
        ManagerExample example = new ManagerExample();
        Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);
        List<Manager> list = managerMapper.selectByExample(example);
        return list.size() != 0;
    }

    /**
     * 检查邮箱是否存在
     */
    @Override
    public boolean checkEmail(String email) {
        ManagerExample example = new ManagerExample();
        Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email);
        List<Manager> list = managerMapper.selectByExample(example);
        return list.size() != 0;
    }

    /**
     * 获取所有管理员
     */
    @Override
    public List<Manager> getAllManagers() {
        return managerMapper.selectByExample(null);
    }
    
    /**
     * 修改管理员
     */
    @Override
    public boolean edit(Manager manager) {
        int row = managerMapper.updateByPrimaryKey(manager);
        return row == 1;
    }
    
    /**
     * 删除管理员
     */
    @Override
    public boolean delete(Integer id) {
        int row = managerMapper.deleteByPrimaryKey(id);
        return row == 1;
    }
    
    /**
     * 给mid的管理员分配权限
     */
    @Override
    public void assignPermission(Integer mid, String pids) {
        //先删除此管理员所拥有的所有权限
        ManagerPermissionExample example = new ManagerPermissionExample();
        com.chilema.manager.bean.ManagerPermissionExample.Criteria criteria = example.createCriteria();
        criteria.andMidEqualTo(mid);
        managerPermissionMapper.deleteByExample(example);
        //添加权限
        if (!pids.equals("") && pids != null) {
            String[] strings = pids.split(",");
            managerPermissionMapper.assignPermission(mid,strings);
        }
    }
    
    /**
     * 根据email获取管理员对象
     */
    @Override
    public Manager getManagerByEmail(String email) {
        ManagerExample example = new ManagerExample();
        Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email);
        List<Manager> list = managerMapper.selectByExample(example );
        return list.size()==0?null:list.get(0);
    }
    
    /**
     * 根据id获取manager
     */
    @Override
    public Manager getManagerById(Integer mid) {
        return managerMapper.selectByPrimaryKey(mid);
    }
    
    /**
     * 选择性更新
     */
    @Override
    public boolean updateByCondition(Manager manager) {
        int row = managerMapper.updateByPrimaryKeySelective(manager);
        return row == 1;
    }
    
    /**
     * 根据remToken查出manager
     */
    @Override
    public Manager getManagerByRemToken(String remToken) {
        return managerMapper.getManagerByRemToken(remToken);
    }

}

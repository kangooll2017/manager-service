package com.chilema.manager.service;

import java.util.List;

import com.chilema.manager.bean.Manager;


public interface ManagerService {
    
    //注册管理员
    boolean regist(Manager manager);
    
    //管理员登录
    Manager login(Manager manager);
    
    //检查账户是否存在
    boolean checkAccount(String account);
    
    //检查邮箱是否存在
    boolean checkEmail(String email);
    
    //获取所有管理员组成的list
    List<Manager> getAllManagers();
    
    //修改管理员
    boolean edit(Manager manager);
    
    //删除管理员
    boolean delete(Integer id);

    //给管理员分配权限
    void assignPermission(Integer mid, String pids);
    
    //根据email获取管理员对象
    Manager getManagerByEmail(String email);
    
    //根据id获取manager
    Manager getManagerById(Integer mid);
    
    //选择性更新
    boolean updateByCondition(Manager manager);
    
    //根据remToken连表查询manager
    Manager getManagerByRemToken(String remToken);

}

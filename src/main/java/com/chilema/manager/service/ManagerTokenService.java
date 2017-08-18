package com.chilema.manager.service;

import com.chilema.manager.bean.Manager;
import com.chilema.manager.bean.ManagerToken;


public interface ManagerTokenService {
    
    //根据manager的id找到对应的 ManagerToken
    ManagerToken getManagerTokenByManagerId(Integer mid);
    
    //插入一条ManagerToken
    boolean insert(ManagerToken mt);
    
    //更新ManagerToken
    boolean update(ManagerToken managerToken);
    
    //根据pswdToken去查ManagerToken对象
    ManagerToken getManagerTokenByPswdToken(String pswdToken);
    
    //根据manager的id删除ManagerToken中的PswdToken
    boolean deletePswdTokenByManagerId(Integer id,Integer mid);
    
    //为man的manager添加remToken
    boolean addRemToken(Manager man, String remToken);

}

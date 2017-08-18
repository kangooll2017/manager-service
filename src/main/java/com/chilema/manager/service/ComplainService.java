package com.chilema.manager.service;

import java.util.List;
import java.util.Map;

import com.chilema.manager.bean.Complain;


public interface ComplainService {
    
    //获取所有投诉信
    List<Complain> getAll();
    
    //切换投诉信的状态
    boolean changeStatus(Integer id, Integer status);

    //根据eid删除投诉信
    boolean delete(Integer id);
    
    //根据id获取投诉信
    Complain getComplainById(Integer id);
    
    //根据条件查询 投诉信+商家名称 的组合
    List<Map<String, Object>> getListByCondition(String condition);

}

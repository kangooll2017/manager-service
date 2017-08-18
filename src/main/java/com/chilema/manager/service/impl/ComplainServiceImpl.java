package com.chilema.manager.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chilema.manager.bean.Complain;
import com.chilema.manager.bean.ComplainExample;
import com.chilema.manager.bean.ComplainExample.Criteria;
import com.chilema.manager.bean.Shop;
import com.chilema.manager.bean.ShopExample;
import com.chilema.manager.dao.ComplainMapper;
import com.chilema.manager.dao.ShopMapper;
import com.chilema.manager.service.ComplainService;

@Service
public class ComplainServiceImpl implements ComplainService {
    
    @Autowired
    private ComplainMapper complainMapper;
    
    @Autowired
    private ShopMapper shopMapper;
    
    /**
     * 获取所有投诉信
     */
    @Override
    public List<Complain> getAll() {
        return complainMapper.selectByExample(null);
    }
    
    /**
     * 切换投诉信的状态
     */
    @Override
    public boolean changeStatus(Integer id, Integer status) {
        Complain complain = new Complain();
        complain.setId(id);
        complain.setStatus(status==0?1:0);
        complainMapper.updateByPrimaryKeySelective(complain );
        return false;
    }
    
    /**
     * 根据eid删除投诉信
     */
    @Override
    public boolean delete(Integer id) {
        int row = complainMapper.deleteByPrimaryKey(id);
        return row == 1;
    }
    
    /**
     * 根据id获取投诉信
     */
    @Override
    public Complain getComplainById(Integer id) {
        Complain complain = complainMapper.selectByPrimaryKey(id);
        return complain;
    }
    
    /**
     * 根据条件查询 投诉信+商家名称 的组合
     */
    @Override
    public List<Map<String, Object>> getListByCondition(String condition) {
        List<Map<String, Object>> list = new ArrayList<>();
        //符合投诉原因条件的投诉信
        ComplainExample example = new ComplainExample();
        Criteria criteria = example.createCriteria();
        criteria.andReasonLike("%"+condition+"%");
        List<Complain> complains = complainMapper.selectByExample(example );
        
        //符合投诉商家名称条件的投诉信
        List<Complain> complains2 = new ArrayList<>();
        ShopExample example2 = new ShopExample();
        com.chilema.manager.bean.ShopExample.Criteria criteria2 = example2.createCriteria();
        criteria2.andNameLike("%"+condition+"%");
        List<Shop> shops = shopMapper.selectByExample(example2);
        for (Shop Shop : shops) {
            ComplainExample example3 = new ComplainExample();
            Criteria criteria3 = example3.createCriteria();
            criteria3.andSidEqualTo(Shop.getId());
            complains2 = complainMapper.selectByExample(example3);
            //合并
            complains.addAll(complains2);
        }
        
        lable:for (Complain Complain : complains) {
            //之前有了就不再添加
            for (Map<String, Object> m : list) {
                if (Complain.getId() == m.get("id")) {
                    continue lable;
                }
            }
            
            Shop shop = shopMapper.selectByPrimaryKey(Complain.getSid());
            String shopName = shop.getName();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id" , Complain.getId());
            map.put("shopName", shopName);
            map.put("reason", Complain.getReason());
            map.put("status", Complain.getStatus());
            list.add(map);
         }
        
        return list;
    }

}

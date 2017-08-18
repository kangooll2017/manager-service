package com.chilema.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chilema.manager.bean.Shop;
import com.chilema.manager.dao.ShopMapper;
import com.chilema.manager.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService {
    
    @Autowired
    private ShopMapper shopMapper;
    
    /**
     * 根据shop的id获取shop对象
     */
    @Override
    public Shop getShopById(Integer sid) {
        return shopMapper.selectByPrimaryKey(sid);
    }

}

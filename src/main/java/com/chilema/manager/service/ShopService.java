package com.chilema.manager.service;

import com.chilema.manager.bean.Shop;


public interface ShopService {
    
    //根据shop的id获取shop对象
    Shop getShopById(Integer sid);

}

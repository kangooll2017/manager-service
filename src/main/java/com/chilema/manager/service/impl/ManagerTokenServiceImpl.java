package com.chilema.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chilema.manager.bean.Manager;
import com.chilema.manager.bean.ManagerToken;
import com.chilema.manager.bean.ManagerTokenExample;
import com.chilema.manager.bean.ManagerTokenExample.Criteria;
import com.chilema.manager.dao.ManagerTokenMapper;
import com.chilema.manager.service.ManagerTokenService;

@Service
public class ManagerTokenServiceImpl implements ManagerTokenService {

    @Autowired
    private ManagerTokenMapper managerTokenMapper;
    
    /**
     * 根据manager的id找到对应的 ManagerToken
     */
    @Override
    public ManagerToken getManagerTokenByManagerId(Integer mid) {
        ManagerTokenExample example = new ManagerTokenExample();
        Criteria criteria = example.createCriteria();
        criteria.andMidEqualTo(mid);
        List<ManagerToken> list = managerTokenMapper.selectByExample(example );
        return list.size()==0?null:list.get(0);
    }
    
    /**
     * 插入一条ManagerToken
     */
    @Override
    public boolean insert(ManagerToken mt) {
        int row = managerTokenMapper.insertSelective(mt);
        return row == 1;
    }
    
    /**
     * 更新ManagerToken
     */
    @Override
    public boolean update(ManagerToken managerToken) {
        int row = managerTokenMapper.updateByPrimaryKey(managerToken);
        return row == 1;
    }
    
    /**
     * 根据pswdToken去查ManagerToken对象
     */
    @Override
    public ManagerToken getManagerTokenByPswdToken(String pswdToken) {
        ManagerTokenExample example = new ManagerTokenExample();
        Criteria criteria = example.createCriteria();
        criteria.andPswdTokenEqualTo(pswdToken);
        List<ManagerToken> list = managerTokenMapper.selectByExample(example );
        return list.size()==0?null:list.get(0);
    }
    
    /**
     * 根据manager的id删除ManagerToken中的PswdToken
     */
    @Override
    public boolean deletePswdTokenByManagerId(Integer id,Integer mid) {
        ManagerToken record = new ManagerToken();
        record.setId(id);
        record.setMid(mid);
        record.setPswdToken("");
        managerTokenMapper.updateByPrimaryKeySelective(record );
        return false;
    }
    
    /**
     * 为manager添加remToken
     */
    @Override
    public boolean addRemToken(Manager man, String remToken) {
        ManagerTokenExample example = new ManagerTokenExample();
        Criteria criteria = example.createCriteria();
        criteria.andMidEqualTo(man.getId());
        List<ManagerToken> list = managerTokenMapper.selectByExample(example );
        
        if (list == null || list.size() == 0) {
            //添加一条数据
            ManagerToken record = new ManagerToken();
            record.setMid(man.getId());
            record.setRemToken(remToken);
            managerTokenMapper.insertSelective(record );
        } else {
            ManagerToken managerToken = list.get(0);
            //更新数据中的rem_token
            managerToken.setRemToken(remToken);
            managerTokenMapper.updateByPrimaryKey(managerToken);
        }
        
        return true;
    }

}

package com.example.demo3.IService.impl;

import com.example.demo3.IService.IUser2Service;
import com.example.demo3.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class User2Service implements IUser2Service {

    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateName(String id, String name) {
        userMapper.updateName(id,name);

    }
}

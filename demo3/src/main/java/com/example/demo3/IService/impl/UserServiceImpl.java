package com.example.demo3.IService.impl;

import com.example.demo3.IService.IUser2Service;
import com.example.demo3.IService.IUserService;
import com.example.demo3.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.ErrorHandler;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    IUser2Service user2Service;

    @Override
    @Transactional
    public void updateAge(String id, String age) {

        userMapper.updateAge(id,age);
//        user2Service.updateName(id,"李四1");
//        int a = 1/0;

    }
}

package org.example.graduatemanage.service;

import org.example.graduatemanage.dox.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.graduatemanage.repostory.UserRepository;

@Service
public class UserService {
    //依赖类声明为常量 保证以来的不可变性
    private final UserRepository userRepository;

    //使用构造函数注入的方法 防止脱离了spring环境无法新建
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    //判断用户是否存在
    public User judgeIfPresentUser(String number){
        return userRepository.findUserByNumber(number).orElse(null);
    }
}

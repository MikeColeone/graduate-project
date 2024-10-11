package org.example.graduatemanage.service;

import org.example.graduatemanage.dox.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.graduatemanage.repostory.UserRepository;

import java.util.List;
import java.util.Optional;

/*
用户管理服务：包括查询用户信息、列出用户、学生列表、按角色和组别列出用户等功能。
密码管理服务：支持用户密码的加密与更新。
流程管理服务：提供流程（Process）的查询功能，适用于与流程相关的展示或管理需求。
*/
@Service
public class UserService {
    //依赖类声明为常量 保证以来的不可变性
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    //判断用户是否存在
    public User judgeIfPresentUser(String number){
        return userRepository.findUserByNumber(number).orElse(null);
    }

    //查询用户信息 参数：用户编号
    public Optional<User> getUsersInfo(String number){
        return userRepository.findUserByNumber(number);
    }

    //获取学生列表 得到所有学生 参数:专业编号 role
    public List<User> getStudentsDepartment(String depId,String role){
        return userRepository.findStudentsAll(depId,role);
    }
}

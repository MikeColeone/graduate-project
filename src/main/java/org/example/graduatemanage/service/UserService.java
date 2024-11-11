package org.example.graduatemanage.service;

import lombok.RequiredArgsConstructor;
import org.example.graduatemanage.dox.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.graduatemanage.repostory.UserRepository;
import org.example.graduatemanage.components.ShuffleAlgorithm;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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

    //实现学生分配
    public void assignment(Map<User, List<User>> teacherStudentMap, List<User> students){
        ShuffleAlgorithm.assignStudents(teacherStudentMap,students);
    }


    /*登陆时查询是否存在用户*/
    public User getUserByNumber(String number, String password) {
        return userRepository.findUserByNumberAndPassword(number,password);
    }
}

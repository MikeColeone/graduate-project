package org.example.graduatemanage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.graduatemanage.components.ShuffleAlgorithm;
import org.example.graduatemanage.dox.User;
import org.example.graduatemanage.exception.XException;
import org.example.graduatemanage.repostory.ProcessRepository;
import org.example.graduatemanage.repostory.UserRepository;
import org.springframework.stereotype.Service;
import org.example.graduatemanage.exception.Code;
import org.example.graduatemanage.dox.Process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
* 创建过程，过程子项等
导入学生表格
按数量等自动分配导师
将学生随机分组/答辩顺序。分组原则，学生与指导教师不在同组，各组学生数尽量平均。
导入覆盖毕设题目/分组/顺序/导师等信息
重置指定账号密码
更新用户信息。支持修改导师/分组等。
导出详细评分表格
展示组内过程学生文件*/

@RequiredArgsConstructor
@Service
@Slf4j
public class TeachersService {
    private final UserRepository userRepository;

    private final ProcessRepository processRepository;
    //读取表格并写入数据库
    private void upLoadFiles(List<User> users){
        //将学生上传数据库
        userRepository.saveAll(users);
        log.debug("{}",users);
    }

    //分配导师
    //实现学生分配
    private void assignment(Map<User, List<User>> teacherStudentMap, List<User> students){
        ShuffleAlgorithm.assignStudents(teacherStudentMap,students);
    }


    /**
     * @Param groups 分好的组
     * @Return 返回每个组对应的答辩顺序
     */

    //更新用户信息
    private User updateUserInfo(User user){
        log.debug("{}",user);
        return userRepository.save(user);
    }
    //随机分组 答辩顺序
    private List<String> generatePresentationOrder(List<List<User>> groups) {
        List<String> orderList = new ArrayList<>();

        for (int i = 0; i < groups.size(); i++) {
            List<User> group = groups.get(i);
            orderList.add("Group " + (i + 1) + " - Order: " + getStudentNames(group));
        }

        return orderList;
    }

    private String getStudentNames(List<User> students) {
        StringBuilder names = new StringBuilder();
        for (User user : students) {
            names.append(user.getName()).append(", ");
        }
        return names.substring(0, names.length() - 2); // 移除最后一个逗号和空格
    }
    //重置指定账号密码
    //不要验证旧密码
    private String changePasseword(String number,String password){
        Optional<User> user = userRepository.findUserByNumber(number);

        if (user.isEmpty()) {
            log.debug("所查用户为空，无法重置密码");
            throw new XException(Code.USER_NOT_FOUND);  // 抛出自定义异常
        }

        User userFound = user.get();
        userRepository.save(userFound);
        log.debug("密码重置成功");

        return user.get().getPassword();
    }


    public Process createProcess(Process process) {

        return processRepository.save(process);
    }
}
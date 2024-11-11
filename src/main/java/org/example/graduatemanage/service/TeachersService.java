package org.example.graduatemanage.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.graduatemanage.dox.User;
import org.example.graduatemanage.exception.XException;
import org.example.graduatemanage.repostory.ProcessRepository;
import org.example.graduatemanage.repostory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.graduatemanage.exception.Code;
import org.example.graduatemanage.dox.Process;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.Builder;

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


    public Process createProcess(String name, String items, int point, String auth,String departmentId, String student) {
        Process process = Process.builder()
                .name(name)
                .items(items)
                .point(point)
                .departmentId(departmentId)
                .student(student)
                .insertTime(LocalDateTime.now()) // 设置创建时间
                .updateTime(LocalDateTime.now()) // 设置更新时间
                .build();
        return processRepository.save(process);
    }
}
package org.example.graduatemanage.service;


import lombok.RequiredArgsConstructor;
import org.example.graduatemanage.dox.Department;
import org.example.graduatemanage.dox.User;
import org.example.graduatemanage.exception.Code;
import org.example.graduatemanage.exception.XException;
import org.example.graduatemanage.repostory.DepartmentRepository;
import org.example.graduatemanage.repostory.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*无导师权限创建专业*/
@Service
@RequiredArgsConstructor
public class AdminService {


    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    //初始化加密密码(管理员)
    public void initPassword(String account){
        String encodePassword=passwordEncoder.encode(account);
        userRepository.updatePasswordByAccount(account,encodePassword);
    }

    //用户
    public void addUser(User user){
        userRepository.save(user);
    }
    


    //删除空的专业
    @Transactional
    public void removeDepartment(String did){
        if(userRepository.countByDepartment(did)>0){
            throw XException.builder()
                    .number(Code.ERROR)
                    .message("无法删除有学生的专业")
                    .build();
        }
        departmentRepository.deleteById(did);
    }
    public Department createDepartments(Department department){return departmentRepository.save(department);}
    //查看所有的专业
    public List<Department> showDepartment(){
        return departmentRepository.showAll();
    }

}

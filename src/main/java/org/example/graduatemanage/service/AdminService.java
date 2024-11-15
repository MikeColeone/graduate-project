package org.example.graduatemanage.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.graduatemanage.dox.Department;
import org.example.graduatemanage.exception.Code;
import org.example.graduatemanage.exception.XException;
import org.example.graduatemanage.repostory.DepartmentRepository;
import org.example.graduatemanage.repostory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*无导师权限创建专业*/
@Service
@RequiredArgsConstructor
public class AdminService {

    private final DepartmentRepository departmentRepository;

    //删除专业
    @Transactional
    public void removeDepartment(String id){
        if(departmentRepository.countDepartmentsBy(id)){
            throw XException.builder()
                    .number(Code.ERROR)
                    .message("部门包含用户禁止删除")
                    .build();
        }
        departmentRepository.deleteById(did);
    }
    public Department createDepartments(Department department){return departmentRepository.save(department);}
}

package org.example.graduatemanage.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.graduatemanage.dox.Department;
import org.example.graduatemanage.repostory.DepartmentRepository;
import org.example.graduatemanage.repostory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*无导师权限创建专业*/
@Service
@RequiredArgsConstructor
public class AdminService {

    private final DepartmentRepository departmentRepository;

    public Department createDepartments(Department department){return departmentRepository.save(department);}
}

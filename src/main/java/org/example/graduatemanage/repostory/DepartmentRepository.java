package org.example.graduatemanage.repostory;

import org.example.graduatemanage.dox.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, String> {

    List<Department> showAll();
}

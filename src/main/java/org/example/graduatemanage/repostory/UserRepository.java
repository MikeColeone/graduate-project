package org.example.graduatemanage.repostory;

import org.example.graduatemanage.dox.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/* Spring Data JPA 的查询方法不支持静态方法。例如：*/

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    //根据账号信息到数据库查询用户
    @Query("""
    select * from myUser u where u.number =:number
    """)
    Optional<User> findUserByNumber(String number);

    //根据账号和密码查询是否存在该用户
    @Query("""
    SELECT * from myUser u where number=:number and password=:password
    """)
    User findUserByNumberAndPassword(String number,String password);

    //拿到某专业所有学生 将学生随机分配给教师
    @Query("""
    select * from myUser u where u.department_id=:depId and u.role=:role
    """)
    List<User> findStudentsAll(String depId,String role);
    // 根据需求 可以拿出某专业老师的学生
    @Query("""
    select * from myUser u where u.student->>'$.teacherId'=:tid and u.department_id=:depId
    """)
    List<User> findStudentByTeacherId(String tid, String depId);

    //查询某专业下的教师
    @Query("""
    select * from myUser u where u.department_id=:depId and u.role=:role;
    """)
    List<User> findTeachersOneDepartment(String depId,String role);


}

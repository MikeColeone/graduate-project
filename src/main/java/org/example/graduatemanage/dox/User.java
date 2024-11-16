package org.example.graduatemanage.dox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    //区分user类别
    public static final String STUDENT_ROLE = "bala";
    public static final String ADMIN_ROLE = "venv";
    public static final String TEACHER_ROLE = "race";
    @Id
    @CreatedBy
    private String id;
    private String name;
    private String number;  //账号
    private String password;
    private String description;
    private String departmentId;
    private String role;
    private int groupNumber;
    private String student;
    private String teacher;
    @ReadOnlyProperty

//    也可以由数据库生成时间
//    @CreatedDate
    private LocalDateTime createTime;
    @ReadOnlyProperty
//    @LastModifiedDate
    private LocalDateTime insertTime;

}

package org.example.graduatemanage.dox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    //区分user类别
    public static final String STUDENT_ROLE = "uaba";
    public static final String ADMIN_ROLE = "vnev";
    public static final String TEACHER_ROLE = "rnte";
    @Id
    @CreatedBy
    private String id;
    private String name;
    private String number;
    private String password;
    private String description;
    private String departmentId;
    private String role;
    private int groupNumber;
    private String student;
    private String teacher;
    @ReadOnlyProperty
    private LocalDateTime createTime;
    @ReadOnlyProperty
    private LocalDateTime insertTime;

}

package org.example.graduatemanage.dox;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDateTime;

public class Process {
    @Id
    private String id;
    private String name;
    private String items;
    private int point;
    private String auth;
    private String departmentId;
    private String student;
    @ReadOnlyProperty
    private LocalDateTime insertTime;
    @ReadOnlyProperty
    private LocalDateTime updateTime;
}

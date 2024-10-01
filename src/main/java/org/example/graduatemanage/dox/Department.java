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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @CreatedBy
    private String id;
    private String name;
    @ReadOnlyProperty //加插入时间声明为只读属性 有数据库默认值
    private LocalDateTime insertTime;
    @ReadOnlyProperty //自动更新
    private LocalDateTime updateTime;
}
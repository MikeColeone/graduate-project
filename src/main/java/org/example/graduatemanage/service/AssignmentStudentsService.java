package org.example.graduatemanage.service;

import org.example.graduatemanage.dox.User;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

//洗牌算法 实现随机学生分配
@Service
public class AssignmentStudentsService {


    public static void shuffle(List<User> students){
        Random random = new Random();
        for (int i = students.size()-1; i>0; i--){
            int j = random.nextInt(i+1);
            swap(students,i,j);
        }
    }

    //实现学生给教师的分配 讲用户作为参数传入
    public static void assignStudents(Map<User, List<User>> teacherStudentMap, List<User> students) {
        //打乱学生顺序
        shuffle(students);
        /*
        可以考虑加打乱教师顺序 因为多余学生的分配方式
         */

        //获取教师数量
        int teacherCount = teacherStudentMap.size();
        //教师分配的学生数量
        int studentPerTeacher = students.size() / teacherCount;
        //教师最多多一个学生 那么获取多少教师获取多一个学生
        int extraStudents = students.size() % teacherCount;

        for (User teacher : teacherStudentMap.keySet()) {
            List<User> assignedStudents = new ArrayList<>();

            // 给每位教师分配 studentPerTeacher 个学生
            int indexStudents = 0;
            for (int j = 0; j < studentPerTeacher; j++) {
                assignedStudents.add(students.get(j));
                indexStudents = j;
            }

            // 如果有剩余学生，给前 extraStudents 位教师多分配1个学生
            if (extraStudents > 0) {
                assignedStudents.add(students.get(studentIndex));
                indexStudents++;
                extraStudents--;
            }

            // 将分配的学生存入教师的映射中
            teacherStudentMap.put(teacher, assignedStudents);
        }
    }

    //对get(index) 直接赋值是不合法的
    //使用set实现赋值交换
    public static void swap(List<User> students, int i, int j) {
        User temp = students.get(i);
        students.set(i, students.get(j));
        students.set(j, temp);
    }

}

package org.example.graduatemanage.components;

import lombok.RequiredArgsConstructor;
import org.example.graduatemanage.dox.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
/*
* 洗牌算法实现分配学生
* */
@Component
public class ShuffleAlgorithm {


    // 打乱学生 但是不能完全打乱
    public static void shuffle(List<User> students) {
        Random random = new Random();
        for (int i = students.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            swap(students, i, j);
        }
    }

    public static void assignStudents(Map<User, List<User>> teacherStudentMap, List<User> students) {
        shuffle(students);
        int teacherCount = teacherStudentMap.size();
        int studentPerTeacher = students.size() / teacherCount;
        int extraStudents = students.size() % teacherCount;
        int indexStudents = 0; // 学生索引
        for (User teacher : teacherStudentMap.keySet()) {
            List<User> assignedStudents = new ArrayList<>();
            for (int j = 0; j < studentPerTeacher; j++) {
                if (indexStudents < students.size()) {
                    assignedStudents.add(students.get(indexStudents));
                    indexStudents++;
                }
            }
            if (extraStudents > 0 && indexStudents < students.size()) {
                assignedStudents.add(students.get(indexStudents));
                indexStudents++;
                extraStudents--;
            }
            teacherStudentMap.put(teacher, assignedStudents);
        }
    }
    public static void swap(List<User> students, int i, int j) {
        User temp = students.get(i);
        students.set(i, students.get(j));
        students.set(j, temp);
    }
}

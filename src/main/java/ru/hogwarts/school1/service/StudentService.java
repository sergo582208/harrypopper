package ru.hogwarts.school1.service;

import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.model.Student;

import java.util.List;

public interface StudentService {
    Student add(Student student);
    Student get(Long id);
    Student update(Long id, Student student);
    void remove(Long id);

    List<Student> getStudentsByAge(int age);

    List<Student> getStudentByAge(int age);

    List<Student> findByAgeBetween(int min, int max);
    Faculty getFacultyByStudent(Long id);

    int getNumberOfStudents();

    int getAverageAgeStudents();

    List<Student> getLastFiveStudents();
}

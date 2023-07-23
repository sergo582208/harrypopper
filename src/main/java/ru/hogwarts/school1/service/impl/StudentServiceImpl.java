package ru.hogwarts.school1.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private static long counter = 0;

    @Override
    public Student add(Student student) {
        student.setId(++counter);
        return students.put(student.getId(), student);
    }

    @Override
    public Student get(Long id) {
        return students.get(id);
    }

    @Override
    public Student update(Long id, Student student) {
        student.setId(id);
        return students.put(student.getId(), student);
    }

    @Override
    public void remove(Long id) {
        students.remove(id);
    }

    @Override
    public List<Student> getStudentsByAge(int age) {
        return null;
    }

    @Override
    public List<Student> getStudentByAge(int age){
        return students.values()
                .stream()
                .filter(it -> it.getAge() == age)
            .collect(Collectors.toList());
}
    }

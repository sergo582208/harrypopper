package ru.hogwarts.school1.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.repository.StudentRepository;
import ru.hogwarts.school1.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student add(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student get(Long id) {
        return studentRepository.findById(id). orElse(null);
    }

    @Override
    public Student update(Long id, Student student) {
        Student studentFromDb = get(id);
        if(studentFromDb == null){
            return null;
        }
        studentFromDb.setName(student.getName());
        studentFromDb.setAge(student.getAge());
        return studentRepository.save(studentFromDb);
    }

    @Override
    public void remove(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudentsByAge(int age) {
        return null;
    }

    @Override
    public List<Student> getStudentByAge(int age){
        return studentRepository.findByAge(age)
                .stream()
                .filter(it -> it.getAge() == age)
            .collect(Collectors.toList());
}
    }

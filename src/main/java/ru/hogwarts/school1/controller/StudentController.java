package ru.hogwarts.school1.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.repository.StudentRepository;
import ru.hogwarts.school1.service.StudentService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;


    private StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable long id) {
        return studentService.get(id);
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable long id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

    @DeleteMapping("/{id}")
    public void remove(@RequestBody long id) {
        studentService.remove(id);
    }

    @GetMapping("/by-age")
    public List<Student> getStudentByAge(@RequestParam int age) {

        return studentService.getStudentByAge(age);

    }
    @GetMapping("/by-age-between")
    public List<Student> getByAgeBetween(@RequestParam int min, @RequestParam int max){
return studentRepository.findByAgeBetween(min, max);
    }
    @GetMapping("/{id}/faculty")
    public Faculty getFacultyByStudent(@PathVariable long id){
return studentService.getFacultyByStudent(id);
    }
}

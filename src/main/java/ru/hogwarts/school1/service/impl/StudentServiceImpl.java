package ru.hogwarts.school1.service.impl;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.repository.StudentRepository;
import ru.hogwarts.school1.service.StudentService;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final Logger logger = (Logger) LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student add(Student student) {
        logger.info("Method add was invoked!");
        return studentRepository.save(student);
    }

    @Override
    public Student get(Long id) {
        logger.info("Method get was invoked!");
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student update(Long id, Student student) {
        logger.info("Method update was invoked!");
        Student studentFromDb = get(id);
        if (studentFromDb == null) {
            return null;
        }
        studentFromDb.setName(student.getName());
        studentFromDb.setAge(student.getAge());
        return studentRepository.save(studentFromDb);
    }

    @Override
    public void remove(Long id) {
        logger.info("Method remove was invoked!");
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudentsByAge(int age) {

        logger.info("Method getStudentsByAge was invoked!");
        return null;
    }

    @Override
    public List<Student> getStudentByAge(int age) {
        logger.info("Method getStudentsByAge was invoked!");
        return studentRepository.findByAge(age)
                .stream()
                .filter(it -> it.getAge() == age)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> findByAgeBetween(int min, int max) {
        logger.info("Method findByAgeBetween was invoked!");
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Faculty getFacultyByStudent(Long id) {
        logger.info("Method getFacultyByStudent was invoked!");
        return studentRepository.findById(id)
                .map(it -> it.getFaculty())
                .orElse(null);
    }

    @Override
    public int getNumberOfStudents() {
        logger.info("Method getNumberOfStudents was invoked!");
        return studentRepository.getNumberOfStudents();
    }

    @Override
    public int getAverageAgeStudents() {
        logger.info("Method getAverageAgeStudents was invoked!");
        return studentRepository.getAverageAgeStudents();
    }

    @Override
    public List<Student> getLastFiveStudents() {
        logger.info("Method getLastFiveStudents was invoked!");
        return studentRepository.getLastFiveStudents();
    }

    @Override
    public List<String> getStudentNamesStartedWithA() {
        return studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(it -> it.startsWith("A"))
                .collect(Collectors.toList());
    }
        @Override
        public Double getAverageAge () {
            return studentRepository.findAll()
                    .stream()
                    .mapToInt(Student::getAge)
                    .average()
                    .orElse(0.0);

        }

    @Override
    public void threads() {
        List<Student> students = studentRepository.findAll();

        printName(students.get(0));
        printName(students.get(1));

        new Thread(() -> {
            printName(students.get(2));
            printName(students.get(3));
        }).start();

        new Thread(() -> {
            printName(students.get(4));
            printName(students.get(5));
        }).start();
    }
private void printName(Student student){
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    System.out.println(student.getName());
}
    @Override
    public void threadsSync() {
        List<Student> students = studentRepository.findAll();
        printNameSynchronized(students.get(0));
        printNameSynchronized(students.get(1));

        new Thread(() -> {
            printNameSynchronized(students.get(2));
            printNameSynchronized(students.get(3));
        }).start();

        new Thread(() -> {
            printNameSynchronized(students.get(4));
            printNameSynchronized(students.get(5));
        }).start();
    }
    private synchronized void printNameSynchronized(Student student){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(student.getName());
    }
    }




package ru.hogwarts.school1.service;

import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.model.Student;

import java.util.List;

public interface FacultyService {
    Faculty add(Faculty faculty);
    Faculty get(Long id);
    Faculty update(Long id,Faculty faculty);
    void remove(Long id);
    List<Faculty> getFacultiesByColor(String colour);

    List<Faculty> findByColorOrName(String color, String name);
    List<Student> getStudents(Long id);
}

package ru.hogwarts.school1.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.service.FacultyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final Map<Long,Faculty> faculties = new HashMap<>();
    private static long counter = 0;
    @Override
    public Faculty add(Faculty faculty) {
        faculty.setId(++counter);
        return faculties.put(faculty.getId(), faculty);
    }

    @Override
    public Faculty get(Long id) {
        return faculties.get(id);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        return faculties.put(faculty.getId(), faculty);
    }

    @Override
    public void remove(Long id) {
        faculties.remove(id);
    }

    @Override
    public List<Faculty> getFacultiesByColor(String color) {
            return faculties.values()
                    .stream()
                    .filter(it -> it.getColor() == color)
                    .collect(Collectors.toList());
    }
}


package ru.hogwarts.school1.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.repository.FacultyRepository;
import ru.hogwarts.school1.service.FacultyService;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty add(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty get(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        Faculty facultyFromDb = get(id);
        if (facultyFromDb == null) {
            return null;
        }
        facultyFromDb.setName(faculty.getName());
        facultyFromDb.setColor(faculty.getColor());
        return facultyRepository.save(facultyFromDb);
    }

    @Override
    public void remove(Long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public List<Faculty> getFacultiesByColor(String color) {
        return facultyRepository.findAllByColor(color);
    }
    @Override
    public List<Faculty> findByColorOrName(String color, String name) {
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    @Override
    public List<Student> getStudents(Long id) {
        return facultyRepository.findById(id)
                .map(Faculty::getStudents)
                .orElse(null);
    }
}




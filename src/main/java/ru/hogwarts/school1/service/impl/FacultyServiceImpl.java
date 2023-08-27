package ru.hogwarts.school1.service.impl;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.repository.FacultyRepository;
import ru.hogwarts.school1.service.FacultyService;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;
    private final Logger logger = (Logger) LoggerFactory.getLogger(FacultyServiceImpl.class);
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty add(Faculty faculty) {
        logger.info("Method add was invoked!");
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty get(Long id) {
        logger.info("Method get was invoked!");
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        logger.info("Method update was invoked!");
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
        logger.info("Method remove was invoked!");
        facultyRepository.deleteById(id);
    }

    @Override
    public List<Faculty> getFacultiesByColor(String color) {
        logger.info("Method getFacultiesByColor was invoked!");
        return facultyRepository.findAllByColor(color);
    }
    @Override
    public List<Faculty> findByColorOrName(String color, String name) {
        logger.info("Method findByColorOrName was invoked!");
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    @Override
    public List<Student> getStudents(Long id) {
        logger.info("Method getStudents was invoked!");
        return facultyRepository.findById(id)
                .map(Faculty::getStudents)
                .orElse(null);
    }

    @Override
    public String getTheLongestName() {
        return facultyRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.comparing(String::length))
                .orElse(null);

    }
}




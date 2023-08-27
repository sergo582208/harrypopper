package ru.hogwarts.school1.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.repository.FacultyRepository;
import ru.hogwarts.school1.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;
    private final FacultyRepository facultyRepository;


    private FacultyController(FacultyService facultyService, FacultyRepository facultyRepository) {
        this.facultyService = facultyService;
        this.facultyRepository = facultyRepository;
    }

    @GetMapping("/{id}")
    public Faculty get(@PathVariable long id) {
        return facultyService.get(id);
    }

    @PostMapping
    public Faculty add(@RequestBody Faculty faculty) {
        return facultyService.add(faculty);
    }

    @PutMapping("/{id}")
    public Faculty update(@PathVariable long id, @RequestBody Faculty faculty) {
        return facultyService.update(id, faculty);
    }

    @DeleteMapping("/{id}")
    public void remove(@RequestBody long id) {
        facultyService.remove(id);
    }

    @GetMapping("/by-age")
    public List<Faculty> getFacultyByColor(@RequestParam String color) {

        return facultyService.getFacultiesByColor(color);

    }

    @GetMapping("/by-color-or-name")
    public List<Faculty> getByColorOrName(@RequestParam String color, @RequestParam String name) {
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }
    @GetMapping("/{id}/students")
    public List<Student> getStudents(@PathVariable long id) {
        return facultyService.getStudents(id);
    }

    @GetMapping("the-longest-name")
    public String getTheLongestName() {
        return facultyService.getTheLongestName();
    }

}





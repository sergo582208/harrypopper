package ru.hogwarts.school1.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.service.FacultyService;
import ru.hogwarts.school1.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;


    private FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}")
    public Faculty get(@PathVariable long id) {
        return facultyService.get(id);
    }

    @PostMapping
    public Faculty add(@RequestBody Faculty faculty){
        return facultyService.add(faculty);
    }
    @PutMapping("/{id}")
    public Faculty update(@PathVariable long id,@RequestBody Faculty faculty){
        return facultyService.update(id, faculty);
    }
    @DeleteMapping("/{id}")
    public void remove(@RequestBody long id){
        facultyService.remove(id);
    }
    @GetMapping("/by-age")
    public List<Faculty> getFacultyByColor(@RequestParam String color) {

        return facultyService.getFacultiesByColor(color);

    }
}





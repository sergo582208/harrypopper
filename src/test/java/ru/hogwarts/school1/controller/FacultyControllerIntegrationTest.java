package ru.hogwarts.school1.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.repository.FacultyRepository;

import java.util.function.BooleanSupplier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
private FacultyRepository facultyRepository;
    @AfterEach
public void resetDb(){facultyRepository.deleteAll();}
    @Test
    void shouldCreateFaculty(){
        //given
        Faculty faculty = new Faculty();
        faculty.setName("Griffindor");
        faculty.setColor("Color");
        //when
        ResponseEntity<Faculty> response = restTemplate.postForEntity("/faculties", faculty, Faculty.class);
        //then
        Assertions.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        Assertions.assertTrue((BooleanSupplier) response.getBody());
        Assertions.assertTrue((BooleanSupplier) response.getBody());
        Assertions.assertTrue((BooleanSupplier) response.getBody());
        Assertions.assertTrue((BooleanSupplier) response.getBody());

    }

    private Object isEqualTo(String name) {
        return null;
    }

    @Test
    void shouldGetFaculty() throws InstantiationException, IllegalAccessException {
        //given
        Long facultyId;
        facultyId = (Long) persistTestFaculty("Griffindor","Color").getClass().newInstance();
        //when
        ResponseEntity<Faculty>responseEntity = restTemplate.getForEntity("/faculties/{id}", Faculty.class, facultyId);
        //then
        Faculty faculty = responseEntity.getBody();
        Assertions.assertTrue((BooleanSupplier) faculty);
        Assertions.assertTrue(faculty.getId((facultyId)));

    }

    private Object persistTestFaculty(String griffindor, String color) {
        return null;
    }


    private BooleanSupplier persistTestFaculty(String Griffindor, String Color, Long facultyId) {
        Faculty faculty = new Faculty();
        faculty.setName("Griffindor");
        faculty.setColor("Color");
        return facultyRepository.save(faculty).getId((facultyId));
    }
    @Test
    void shouldRemoveFaculty() throws InstantiationException, IllegalAccessException {
        //given
        Long facultyId;
        facultyId = (Long) persistTestFaculty("Griffindor","Color").getClass().newInstance();
        //when
        restTemplate.delete("/faculties/{id}", facultyId);
        //then
        Assertions.assertTrue((BooleanSupplier) facultyRepository, (String) facultyRepository.findById());
    }
}

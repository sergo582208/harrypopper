package ru.hogwarts.school1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.function.RequestPredicates;
import ru.hogwarts.school1.model.Faculty;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.service.StudentService;

import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.http.RequestEntity.put;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(StudentControllerWebMvcTest.class)
public class StudentControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private StudentService studentService;

    @Test
    void shouldCreateStudent() throws Exception {
        //given
        Student student = new Student();
        student.setName("Harry Potter");
        student.setAge(25);

        when(student.add(student)).thenReturn(student);

        //when
        ResultActions resultActions = mockMvc.perform((RequestBuilder) post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.valueOf(objectMapper.writeValueAsString(student))))
                ;
//then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect((ResultMatcher) jsonPath("$.name").value(student.getName()))
        .andExpect((ResultMatcher) jsonPath("$.age").value(student.getAge()))
        .andDo(print());
    }
    @Test
    void shouldReturnStudent() throws Exception {
        //given
        Student student = new Student(1L, "Harry Potter", 20);
        when(studentService.get(1L)).thenReturn(student);
        //when
        RequestPredicates.Visitor applicationJson = (RequestPredicates.Visitor) MediaType.APPLICATION_JSON;
        ResultActions resultActions = mockMvc.perform((RequestBuilder) MediaType.APPLICATION_JSON);

        //then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id").value(student.getId()))
                .andExpect((ResultMatcher) jsonPath("$.age").value(student.getAge()))
                .andDo(print());
    }
    @Test
    void shouldUpdateStudent() throws Exception {
        //given
        Long studentId = 1L;
        Student student = new Student(studentId, "Harry Potter", 20);
        when(studentService.update(studentId, student));
        //when
        ResultActions resultActions = mockMvc.perform((RequestBuilder) put("/students/1" + studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.valueOf(objectMapper.writeValueAsString(student))))
                ;
        //then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id").value(student.getId()))
                .andExpect((ResultMatcher) jsonPath("$.age").value(student.getAge()))
                .andDo(print());
    }
@Test
void shouldDeleteStudents() throws Exception {
        //given
    Long studentId = 1L;
    //when
    ResultActions resultActions = mockMvc.perform((RequestBuilder) delete("/students/1" + studentId)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            )
            ;
    //then
    resultActions
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(print());
}
    private Object get(String path) {
        return null;
    }

    @Test
    void shouldReturnFaculty(Student student) throws Exception {
        //given
        Long studentId = 1L;
        Faculty faculty = new Faculty(1L, "Griffindor", "Green");
        when(studentService.getFacultyByStudent(studentId)).thenReturn(faculty);

//when
        ResultActions resultActions = mockMvc.perform((RequestBuilder) put("/students/1" + studentId + "/faculty")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.valueOf(objectMapper.writeValueAsString(student))))
                ;
        //then
        Long facultyId = null;
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id").value(faculty.getId((facultyId))))
                .andExpect((ResultMatcher) jsonPath("$.name").value(faculty.getName()))
                .andExpect((ResultMatcher) jsonPath("$.color").value(faculty.getColor()))
                .andDo(print());
    }
    }



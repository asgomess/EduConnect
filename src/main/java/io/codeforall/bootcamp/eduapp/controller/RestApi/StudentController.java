package io.codeforall.bootcamp.eduapp.controller.RestApi;

import io.codeforall.bootcamp.eduapp.exceptions.IdNotFoundException;
import io.codeforall.bootcamp.eduapp.model.Student;
import io.codeforall.bootcamp.eduapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api") //
public class StudentController {

    @Autowired
    private StudentService studentService;

    // --- 1. READ: Get All Teachers (GET /api/v1/teachers) ---

    @GetMapping("/students")
    public ResponseEntity<List<Student>> listStudents() {

        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer id) {

        try {
            Student getStudent = studentService.getStudentById(id);
            return new ResponseEntity<>(getStudent, HttpStatus.OK);

        } catch (IdNotFoundException e) {

            throw new RuntimeException(e);
        }

    }

//    @PostMapping("/students/login")
//    public ResponseEntity<Student> loginStudent(@RequestBody Dto dto)
//        {
//
//
//
//        }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {

        Student savedStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);

    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        boolean deleted = studentService.deleteStudent(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

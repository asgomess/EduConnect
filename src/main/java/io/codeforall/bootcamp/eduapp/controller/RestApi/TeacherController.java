package io.codeforall.bootcamp.eduapp.controller.RestApi;


import io.codeforall.bootcamp.eduapp.Dtos.LoginDto;
import io.codeforall.bootcamp.eduapp.exceptions.IdNotFoundException;
import io.codeforall.bootcamp.eduapp.model.Teacher;
import io.codeforall.bootcamp.eduapp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api") // Base URL for all teacher operations
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // --- 1. READ: Get All Teachers (GET /api/v1/teachers) ---

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> listTeachers() {

        List<Teacher> teachers = teacherService.getAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable Integer id){

        try {
            Teacher getTeacher = teacherService.getTeacherById(id);
            return new ResponseEntity<>(getTeacher, HttpStatus.OK);

        } catch (IdNotFoundException e) {

            throw new RuntimeException(e);
        }

    }


    @PostMapping("/teachers")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {

        Teacher savedTeacher = teacherService.saveTeacher(teacher);
        return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
    }

    @PostMapping("/teachers/login")
    public ResponseEntity<Teacher> loginTeacher(@RequestBody LoginDto loginDto) {

        try {

            Teacher existingTeacher = teacherService.getTeacherByEmail(loginDto.getEmail());

            if (existingTeacher!=null) {

                return new ResponseEntity<>(HttpStatus.OK);

            } else {

                return new ResponseEntity<>(existingTeacher, HttpStatus.UNAUTHORIZED);
            }

        } catch (IdNotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }









    // THERE WAS NOT ENOUGH TIME TO IMPLEMENT UPDATE FUNCTIONALITY, AND WE CHOOSE TO NOT IMPLEMENT IT WITHOUT DTOS OBJECTS


//    @PutMapping("/teachers/{id}")
//    public ResponseEntity<Teacher> updateTeacher(@PathVariable Integer id, @RequestBody Teacher teacher) {
//
//        try {
//            Teacher existingTeacher = teacherService.getTeacherById(id);
//
//            existingTeacher.setName(teacher.getName());
//            existingTeacher.setSubject(teacher.getSubject());
//            // Add other fields as necessary
//
//            Teacher updatedTeacher = teacherService.saveTeacher(existingTeacher);
//            return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
//
//        } catch (IdNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//    }


    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Integer id) {
        boolean deleted = teacherService.deleteTeacher(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
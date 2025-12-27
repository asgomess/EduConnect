package io.codeforall.bootcamp.eduapp.controller.RestApi;

import io.codeforall.bootcamp.eduapp.exceptions.IdNotFoundException;
import io.codeforall.bootcamp.eduapp.model.Subject;
import io.codeforall.bootcamp.eduapp.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")

public class SubjectController {


        @Autowired
        private SubjectService subjectService;



        @GetMapping("/subjects")
        public ResponseEntity<List<Subject>> listSubjects() {

            List<Subject> subjects = subjectService.getAllSubjects();
            return new ResponseEntity<>(subjects, HttpStatus.OK);
        }

        @GetMapping("/subjects/{id}")
        public ResponseEntity<Subject> getSubjects(@PathVariable Integer id){

            try {
                Subject getSubject = subjectService.getSubjectById(id);
                return new ResponseEntity<>(getSubject, HttpStatus.OK);

            } catch (IdNotFoundException e) {

                throw new RuntimeException(e);
            }

        }

        @PostMapping("/subjects")
        public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {

            Subject savedSubject = subjectService.saveSubject(subject);
            return new ResponseEntity<>(savedSubject, HttpStatus.CREATED);

        }

        @DeleteMapping("/subjects/{id}")
        public ResponseEntity<Void> deleteSubject(@PathVariable Integer id) {
            boolean deleted = subjectService.deleteSubject(id);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



}

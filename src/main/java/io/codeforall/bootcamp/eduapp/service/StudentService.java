package io.codeforall.bootcamp.eduapp.service;

import io.codeforall.bootcamp.eduapp.exceptions.IdNotFoundException;
import io.codeforall.bootcamp.eduapp.model.Student;
import io.codeforall.bootcamp.eduapp.persistence.daos.jpa.StudentJpa;
import io.codeforall.bootcamp.eduapp.persistence.daos.jpa.SubjectJpa;
import io.codeforall.bootcamp.eduapp.persistence.managers.jpa.JpaTransactionManager;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentJpa studentJpa;

    public List<Student> getAllStudents() {
        System.out.println("asking service for list of all students...");
        return studentJpa.findAll();
    }


    @Autowired
    private JpaTransactionManager transactionManager;


    public Student getStudentById(Integer id) throws IdNotFoundException {
        Student student = studentJpa.findById(id);

        if (student == null) {
            throw new IdNotFoundException();
        }

        return student;
    }

    public Student saveStudent(Student student) {
        try {
            transactionManager.beginWrite();
            return studentJpa.saveOrUpdate(student);
        } finally {
            transactionManager.commit();
        }
    }


    public boolean deleteStudent(Integer id) {
        try {
            transactionManager.beginWrite();

            Student delStudent = studentJpa.findById(id);

            if (delStudent == null) {
                throw new IdNotFoundException();
            }

            studentJpa.delete(id);
            transactionManager.commit();
            return true;

        } catch (PersistenceException | IdNotFoundException e) {
            transactionManager.rollback();
            return false;

        } finally {
            transactionManager.rollback();

        }
    }

}

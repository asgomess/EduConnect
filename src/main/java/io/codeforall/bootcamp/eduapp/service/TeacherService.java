package io.codeforall.bootcamp.eduapp.service;


import io.codeforall.bootcamp.eduapp.exceptions.IdNotFoundException;
import io.codeforall.bootcamp.eduapp.model.Teacher;
import io.codeforall.bootcamp.eduapp.persistence.daos.jpa.TeacherJpa;
import io.codeforall.bootcamp.eduapp.persistence.managers.jpa.JpaTransactionManager;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class TeacherService {

    @Autowired
    private TeacherJpa teacherJpa;

    @Autowired
    private JpaTransactionManager transactionManager;


    public List<Teacher> getAllTeachers() {
        System.out.println("asking service for list of all teachers...");
        return teacherJpa.findAll();
    }

    public Teacher getTeacherByEmail(String email) throws IdNotFoundException  {

        Teacher teacher = teacherJpa.findByEmail(email);

        if (teacher == null) {
            throw new IdNotFoundException();
        }

        return teacher;
    }



    public Teacher getTeacherById(Integer id) throws IdNotFoundException {
        Teacher teacher = teacherJpa.findById(id);

        if (teacher == null) {
            throw new IdNotFoundException();
        }

        return teacher;
    }

    public Teacher saveTeacher(Teacher teacher) {
        try {
            transactionManager.beginWrite();
            return teacherJpa.saveOrUpdate(teacher);
        } finally {
            transactionManager.commit();
        }
    }


    public boolean deleteTeacher(Integer id) {
        try {
            transactionManager.beginWrite();

            Teacher delTeacher = teacherJpa.findById(id);

            if (delTeacher == null) {
                throw new IdNotFoundException();
            }

            teacherJpa.delete(id);
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
package io.codeforall.bootcamp.eduapp.persistence.daos.jpa;

import io.codeforall.bootcamp.eduapp.model.Student;

import org.springframework.stereotype.Repository;

@Repository
public class StudentJpa extends JpaGenericDao<Student> {

    public StudentJpa() {
        super(Student.class);
    }

}

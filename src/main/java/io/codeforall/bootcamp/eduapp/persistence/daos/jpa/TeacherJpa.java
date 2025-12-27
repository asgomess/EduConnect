package io.codeforall.bootcamp.eduapp.persistence.daos.jpa;

import io.codeforall.bootcamp.eduapp.model.Teacher;
import io.codeforall.bootcamp.eduapp.persistence.managers.jpa.JpaSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherJpa extends JpaGenericDao<Teacher> {


    public TeacherJpa() {

        super(Teacher.class);
    }



}

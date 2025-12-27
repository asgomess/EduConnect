package io.codeforall.bootcamp.eduapp.persistence.daos.jpa;


import io.codeforall.bootcamp.eduapp.model.Subject;
import org.springframework.stereotype.Repository;



@Repository
public class SubjectJpa extends JpaGenericDao<Subject> {

    public SubjectJpa() {
        super(Subject.class);
    }

}

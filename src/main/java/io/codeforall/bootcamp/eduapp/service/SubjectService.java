package io.codeforall.bootcamp.eduapp.service;

import io.codeforall.bootcamp.eduapp.exceptions.IdNotFoundException;
import io.codeforall.bootcamp.eduapp.model.Subject;
import io.codeforall.bootcamp.eduapp.persistence.daos.jpa.SubjectJpa;
import io.codeforall.bootcamp.eduapp.persistence.managers.jpa.JpaTransactionManager;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class SubjectService {



        @Autowired
        private SubjectJpa subjectJpa;

        public List<Subject> getAllSubjects() {
            System.out.println("asking service for list of all students...");
            return subjectJpa.findAll();
        }


        @Autowired
        private JpaTransactionManager transactionManager;


        public Subject getSubjectById(Integer id) throws IdNotFoundException {
            Subject subject = subjectJpa.findById(id);

            if (subject == null) {
                throw new IdNotFoundException();
            }

            return subject;
        }

        public Subject saveSubject(Subject subject) {
            try {
                transactionManager.beginWrite();

                return subjectJpa.saveOrUpdate(subject);

            } finally {
                transactionManager.commit();
            }
        }


        public boolean deleteSubject(Integer id) {
            try {
                transactionManager.beginWrite();

                Subject delSubject = subjectJpa.findById(id);

                if (delSubject == null) {
                    throw new IdNotFoundException();
                }

                subjectJpa.delete(id);
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

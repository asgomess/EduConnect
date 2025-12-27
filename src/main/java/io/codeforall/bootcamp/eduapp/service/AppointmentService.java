package io.codeforall.bootcamp.eduapp.service;

import io.codeforall.bootcamp.eduapp.exceptions.IdNotFoundException;
import io.codeforall.bootcamp.eduapp.model.Appointment;
import io.codeforall.bootcamp.eduapp.model.Subject;
import io.codeforall.bootcamp.eduapp.model.Teacher;
import io.codeforall.bootcamp.eduapp.persistence.daos.jpa.AppointmentJpa;
import io.codeforall.bootcamp.eduapp.persistence.managers.jpa.JpaTransactionManager;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AppointmentService {

    @Autowired
    private AppointmentJpa appointmentJpa;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TeacherService teacherService;


    public List<Appointment> getAllAppointments() {
        System.out.println("asking service for list of all appointments...");
        return appointmentJpa.findAll();
    }


    @Autowired
    private JpaTransactionManager transactionManager;


    public Appointment getAppointmentById(Integer id) throws IdNotFoundException {
        Appointment appointment = appointmentJpa.findById(id);

        if (appointment == null) {
            throw new IdNotFoundException();
        }

        return appointment;
    }

    public Appointment saveAppointment(Appointment appointment) {


        int teacherId = appointment.getTeacher().getId();

        Integer subjectId = appointment.getSubject().getId();

        try {
            Teacher teacher = teacherService.getTeacherById(teacherId);
            Subject subject = subjectService.getSubjectById(subjectId);
            appointment.setTeacher(teacher);
            appointment.setSubject(subject);

        } catch (IdNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {
            transactionManager.beginWrite();
            return appointmentJpa.saveOrUpdate(appointment);
        } finally {
            transactionManager.commit();
        }
    }

    public List<Appointment>getTeacherSchedule(Integer teacherId) {
        Teacher teacher = null;

        try {
            transactionManager.beginWrite();
            teacher = teacherService.getTeacherById(teacherId);
            transactionManager.commit();
        } catch (IdNotFoundException e) {

            transactionManager.rollback();
            throw new RuntimeException(e);

        }
        if (teacher != null) {

            return teacher.getAppointments();
        }
        return new java.util.ArrayList<>();
    }


    public boolean deleteAppointment(Integer id) {
        try {
            transactionManager.beginWrite();

            Appointment delAppointment = appointmentJpa.findById(id);

            if (delAppointment == null) {
                throw new IdNotFoundException();
            }

            appointmentJpa.delete(id);
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

package io.codeforall.bootcamp.eduapp.persistence.daos.jpa;

import io.codeforall.bootcamp.eduapp.model.Appointment;
import io.codeforall.bootcamp.eduapp.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppointmentJpa extends JpaGenericDao<Appointment> {

    public AppointmentJpa() {
        super(Appointment.class);
    }
}


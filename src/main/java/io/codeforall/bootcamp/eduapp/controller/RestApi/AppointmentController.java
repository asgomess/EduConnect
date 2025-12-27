package io.codeforall.bootcamp.eduapp.controller.RestApi;


import io.codeforall.bootcamp.eduapp.Dtos.RequestAppointmentDto;
import io.codeforall.bootcamp.eduapp.Dtos.SchedulesDto;
import io.codeforall.bootcamp.eduapp.Dtos.StudScheduleDto;
import io.codeforall.bootcamp.eduapp.exceptions.IdNotFoundException;
import io.codeforall.bootcamp.eduapp.model.Appointment;
import io.codeforall.bootcamp.eduapp.model.Subject;
import io.codeforall.bootcamp.eduapp.model.Teacher;
import io.codeforall.bootcamp.eduapp.service.AppointmentService;
import io.codeforall.bootcamp.eduapp.service.SubjectService;
import io.codeforall.bootcamp.eduapp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api") //
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;



    @GetMapping("/schedules")
    public ResponseEntity<List<SchedulesDto>> listAppointments() {


        List<Appointment> appointments = appointmentService.getAllAppointments();
        List<SchedulesDto> schedulesDtoList = new java.util.ArrayList<>();


        for (Appointment appointment : appointments) {

            System.out.println("appointment id: " + appointment.getId());

            SchedulesDto dto = new SchedulesDto();

            dto.setId(appointment.getId());
            dto.setStatus(appointment.getStatus().getCode());
            dto.setTeacherId(appointment.getTeacher().getId());
            dto.setTeacherQualificationId(appointment.getSubject().getId());
            System.out.println("this is the output of qualification id: " + appointment.getSubject().getId());
            dto.setDescription(appointment.getSubject().getDescription());
            dto.setDateTimeBegin(appointment.getStartDate().toLocalDate());
            dto.setDateTimeEnd(appointment.getEndDate().toLocalDate());
            dto.setType(appointment.getSubject().getName());
            dto.setOnlineMeetingUrl("https://meet.jit.si/MyMeetingRoom" + dto.getType()); // IN THE FUTURE THIS METHOD SHOULD RETURN THE ACTUAL URL FROM THE APPOINTMENT ENTITY OR CONSUME FROM AN EXTERNAL SERVICE
            schedulesDtoList.add(dto);
            System.out.println("schedules dto list size: " + schedulesDtoList.size());

        }
        return new ResponseEntity<>(schedulesDtoList, HttpStatus.OK);
    }

    @GetMapping("/students/schedules")
    public ResponseEntity<List<StudScheduleDto>> StudentAppointments() {


        List<Appointment> appointments = appointmentService.getAllAppointments();
        List<StudScheduleDto> studSchedulesDtoList = new java.util.ArrayList<>();


        for (Appointment appointment : appointments) {

            System.out.println("appointment id: " + appointment.getId());

            StudScheduleDto studDto = new StudScheduleDto();

            studDto.setId(appointment.getId());
            studDto.setStatus(appointment.getStatus().getCode());
            studDto.setTeacherId(appointment.getTeacher().getId());
            studDto.setDescription(appointment.getSubject().getDescription());
            studDto.setDateTimeBegin(appointment.getStartDate().toLocalDate());
            studDto.setDateTimeEnd(appointment.getEndDate().toLocalDate());
            studDto.setType(appointment.getSubject().getName());
            studDto.setTeacherName(appointment.getTeacher().getFirstName() + " " + appointment.getTeacher().getLastName());
            studDto.setOnlineMeetingUrl("https://meet.jit.si/MyMeetingRoom" + studDto.getType()); // IN THE FUTURE THIS METHOD SHOULD RETURN THE ACTUAL URL FROM THE APPOINTMENT ENTITY OR CONSUME FROM AN EXTERNAL SERVICE

            studSchedulesDtoList.add(studDto);

        }
        return new ResponseEntity<>(studSchedulesDtoList, HttpStatus.OK);
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable Integer id){

        try {
            Appointment getAppointment = appointmentService.getAppointmentById(id);
            return new ResponseEntity<>(getAppointment, HttpStatus.OK);

        } catch (IdNotFoundException e) {

            throw new RuntimeException(e);
        }

    }

    @PostMapping("/schedules")
    public ResponseEntity<Void> createAppointment(@RequestBody RequestAppointmentDto requestAppointmentDto) {


        Appointment savedAppointment = new Appointment();

        try {

            Teacher teacher = teacherService.getTeacherById(requestAppointmentDto.getTeacherId());
            Subject subject = subjectService.getSubjectById(requestAppointmentDto.getType());

            savedAppointment.setStartDate(requestAppointmentDto.getDateTimeBegin());
            savedAppointment.setEndDate(requestAppointmentDto.getDateTimeEnd());
            savedAppointment.setTeacher(teacher);
            savedAppointment.setSubject(subject);
            savedAppointment.setStatus(Appointment.Status.fromCode(requestAppointmentDto.getStatus()));
            appointmentService.saveAppointment(savedAppointment);

            System.out.println();

            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (IdNotFoundException e) {

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }





    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Integer id) {
        boolean deleted = appointmentService.deleteAppointment(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

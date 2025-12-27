package io.codeforall.bootcamp.eduapp.Dtos;


import java.time.LocalDateTime;

public class RequestAppointmentDto {

    private int id;
    private int teacherId;
    private int teacherQualificationId;
    private LocalDateTime dateTimeBegin; //SAME AS FRONTEND - JUST INJECT IN THE CONTROLLER
    private LocalDateTime dateTimeEnd; //SAME AS FRONTEND - JUST INJECT IN THE CONTROLLER
    private String description; // COME FROM FRONTEND AS SUBJECT ID
    private int status; // COME FROM FRONTEND AS
    private String onlineMeetingUrl;
    private int type; // COMES FROM FRONTEND AS SUBJECTID



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOnlineMeetingUrl() {
        return onlineMeetingUrl;
    }

    public void setOnlineMeetingUrl(String onlineMeetingUrl) {
        this.onlineMeetingUrl = onlineMeetingUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTimeEnd() {
        return dateTimeEnd;
    }

    public void setDateTimeEnd(LocalDateTime dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }

    public LocalDateTime getDateTimeBegin() {
        return dateTimeBegin;
    }

    public void setDateTimeBegin(LocalDateTime dateTimeBegin) {
        this.dateTimeBegin = dateTimeBegin;
    }

    public int getTeacherQualificationId() {
        return teacherQualificationId;
    }

    public void setTeacherQualificationId(int teacherQualifcationId) {
        this.teacherQualificationId = teacherQualifcationId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
}

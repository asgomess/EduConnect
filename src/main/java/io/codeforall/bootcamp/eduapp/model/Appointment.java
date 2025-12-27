package io.codeforall.bootcamp.eduapp.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "appointments")
public class Appointment extends AbstractModel{


        @Column(nullable = false)
        private LocalDateTime startDate;

        @Column(nullable = false)
        private LocalDateTime endDate;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Status status = Status.Pending;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "teacher_fk") // Keep this so students see who is teaching
        @JsonIgnoreProperties({"appointments", "hibernateLazyInitializer", "handler"})
        private Teacher teacher;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "subject_fk") // Keep this so students see the subject name
        @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
        private Subject subject;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }



    public enum Status {
        Pending(0),
        Done(1),
        Cancelled(2);

        private final int code;


        Status(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static Status fromCode(int code) {
            for (Status status : Status.values()) {
                if (status.getCode() == code) {
                    return status;
                }
            }
            throw new IllegalArgumentException("Invalid Status code: " + code);
        }
    }
}
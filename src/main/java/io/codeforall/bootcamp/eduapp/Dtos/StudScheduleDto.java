package io.codeforall.bootcamp.eduapp.Dtos;

import java.time.LocalDate;



public class StudScheduleDto {


        private int id;
        private int teacherId;
        private LocalDate dateTimeBegin;
        private LocalDate dateTimeEnd;
        private String description;
        private int status;
        private String onlineMeetingUrl;
        private String type;
        private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

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

        public LocalDate getDateTimeEnd() {
            return dateTimeEnd;
        }

        public void setDateTimeEnd(LocalDate dateTimeEnd) {
            this.dateTimeEnd = dateTimeEnd;
        }

        public LocalDate getDateTimeBegin() {
            return dateTimeBegin;
        }

        public void setDateTimeBegin(LocalDate dateTimeBegin) {
            this.dateTimeBegin = dateTimeBegin;
        }



        public int getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
        }

        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
    }


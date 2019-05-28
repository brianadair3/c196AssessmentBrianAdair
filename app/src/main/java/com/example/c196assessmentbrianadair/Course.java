package com.example.c196assessmentbrianadair;

import java.util.ArrayList;

public class Course {
    private String id;
    private String title;
    private String startDate;
    private String startDateAlert;
    private String endDate;
    private String endDateAlert;
    private String status;
    private String mentorName;
    private String mentorPhone;
    private String mentorEmail;
    private String tId;

    public Course(String title, String startDate, String startDateAlert, String endDate, String endDateAlert, String status, String mentorName, String mentorPhone, String mentorEmail, String tId) {
        this.title = title;
        this.startDate = startDate;
        this.startDateAlert = startDateAlert;
        this.endDate = endDate;
        this.endDateAlert = endDateAlert;
        this.status = status;
        this.mentorName = mentorName;
        this.mentorPhone = mentorPhone;
        this.mentorEmail = mentorEmail;
        this.tId = tId;
    }

    public String getCourseId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    public String getStartDate() {
        return this.startDate;
    }
    public String getStartDateAlert() {
        return this.startDateAlert;
    }
    public String getEndDate() {
        return this.endDate;
    }
    public String getEndDateAlert() {
        return this.endDateAlert;
    }
    public String getStatus() {
        return this.status;
    }
    public String getMentorName() {
        return this.mentorName;
    }
    public String getMentorPhone() {
        return this.mentorPhone;
    }
    public String getMentorEmail() {
        return this.mentorEmail;
    }
    public String gettId() {
        return this.tId;
    }

    public void setCourseId(String id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public void setStartDateAlert(String startDateAlert) {
        this.startDateAlert = startDateAlert;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public void setEndDateAlert(String endDateAlert) {
        this.endDateAlert = endDateAlert;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }
    public void setMentorPhone(String mentorPhone) {
        this.mentorPhone = mentorPhone;
    }
    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }
    public void settId(String tId) {
        this.tId = tId;
    }
}

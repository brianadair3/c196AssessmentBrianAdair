package com.example.c196assessmentbrianadair;

public class Assessment {
    private String id;
    private String title;
    private String dueDate;
    private String dueDateAlert;
    private String type;
    private String cId;

    public Assessment(String id, String title, String dueDate, String dueDateAlert, String type, String cId) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.dueDateAlert = dueDateAlert;
        this.type = type;
        this.cId = cId;
    }

    public String getAssessmentId() { return this.id; }

    public String getTitle() { return this.title; }

    public String getDueDate() { return this.dueDate; }

    public String getDueDateAlert() { return this.dueDateAlert; }

    public String getType() { return this.type; }

    public String getcId() { return this.cId; }

    public void setAssessmentId(String id) { this.id = id; }

    public void setTitle(String title) { this.title = title; }

    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public void setDueDateAlert(String dueDateAlert) { this.dueDateAlert = dueDateAlert; }

    public void setType(String type) { this.type = type; }

    public void setcId(String cId) { this.cId = cId; }
}

package JavaObjects;

import java.sql.Date;

public class Activity{
    int activityID;
    Course course;
    Student student;
    Professor professor;
    Date date;
    Date startDate;
    Date endDate;
    String type;
    double grade;
    int weightOfGrade;

    public Activity() {
    }

    public Activity(int activityID, Course course, Student student, Professor professor, Date date, Date startDate, Date endDate, String type, double grade, int weightOfGrade) {
        this.activityID = activityID;
        this.course = course;
        this.student = student;
        this.professor = professor;
        this.date = date;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.grade = grade;
        this.weightOfGrade = weightOfGrade;
    }

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getWeightOfGrade() {
        return weightOfGrade;
    }

    public void setWeightOfGrade(int weightOfGrade) {
        this.weightOfGrade = weightOfGrade;
    }
}

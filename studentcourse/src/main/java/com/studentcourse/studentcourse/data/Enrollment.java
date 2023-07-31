package com.studentcourse.studentcourse.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
public class Enrollment {
    @EmbeddedId
    private EnrollmentId enrollmentId;

    @ManyToOne
    @MapsId("studentId") // Maps the "studentId" attribute of EnrollmentId to the corresponding attribute in the Student entity
    private Student student;

    @ManyToOne
    @MapsId("courseId") // Maps the "courseId" attribute of EnrollmentId to the corresponding attribute in the Course entity
    private Course course;

    private LocalDate enrollmentDate;

    public Enrollment(EnrollmentId enrollmentId, Student student, Course course, LocalDate enrollmentDate) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
    }

    public Enrollment() {

    }

    public EnrollmentId getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(EnrollmentId enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    @PrePersist
    protected void onCreate() {
        this.enrollmentDate = LocalDate.now(); // Set the current date as the enrollment date
    }
}

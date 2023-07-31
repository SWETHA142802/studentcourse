package com.studentcourse.studentcourse.resquestDTO;

public class StudentRequestDTO {

    private String studentName;

    public StudentRequestDTO() {
    }

    public StudentRequestDTO(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}

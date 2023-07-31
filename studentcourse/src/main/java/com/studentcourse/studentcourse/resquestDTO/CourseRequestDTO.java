package com.studentcourse.studentcourse.resquestDTO;

public class CourseRequestDTO {
    private String courseName;

    public CourseRequestDTO() {
    }

    public CourseRequestDTO(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}

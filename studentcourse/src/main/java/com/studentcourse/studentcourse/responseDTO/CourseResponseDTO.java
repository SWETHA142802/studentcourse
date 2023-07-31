package com.studentcourse.studentcourse.responseDTO;

public class CourseResponseDTO {
    private Long courseId;
    private String courseName;

    public CourseResponseDTO() {
    }

    public CourseResponseDTO(Long courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}

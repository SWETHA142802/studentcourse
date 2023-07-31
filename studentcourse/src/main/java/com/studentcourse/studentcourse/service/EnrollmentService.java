package com.studentcourse.studentcourse.service;

import com.studentcourse.studentcourse.data.Course;
import com.studentcourse.studentcourse.data.Enrollment;
import com.studentcourse.studentcourse.data.EnrollmentId;
import com.studentcourse.studentcourse.data.Student;
import com.studentcourse.studentcourse.repository.CourseRepository;
import com.studentcourse.studentcourse.repository.EnrollmentRepository;
import com.studentcourse.studentcourse.repository.StudentRepository;
import com.studentcourse.studentcourse.responseDTO.EnrollmentResponseDTO;
import com.studentcourse.studentcourse.resquestDTO.EnrollmentRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;
    public EnrollmentResponseDTO registerEnrollment(EnrollmentRequestDTO requestDTO) {
        Long studentId = requestDTO.getStudentId();
        Long courseId = requestDTO.getCourseId();
        LocalDate enrollmentDate = LocalDate.now(); // Set the enrollment date automatically
        // Fetch the corresponding Student and Course entities from their respective repositories
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (studentOptional.isPresent() && courseOptional.isPresent()) {
            Student student = studentOptional.get();
            Course course = courseOptional.get();
            EnrollmentId enrollmentId = new EnrollmentId(studentId, courseId);
            Enrollment enrollment = new Enrollment(enrollmentId, student, course, enrollmentDate);
            enrollmentRepository.save(enrollment);
            EnrollmentResponseDTO responseDTO = new EnrollmentResponseDTO();
            responseDTO.setStudentId(studentId);
            responseDTO.setStudentName(student.getStudentName());
            responseDTO.setCourseId(courseId);
            responseDTO.setCourseName(course.getCourseName());
            responseDTO.setEnrollmentDate(enrollmentDate);
            return responseDTO;
        } else {
            String errorMessage;
            if (!studentOptional.isPresent() && !courseOptional.isPresent()) {
                errorMessage = "Student and course do not exist.";
            } else if (!studentOptional.isPresent()) {
                errorMessage = "Student does not exist.";
            } else {
                errorMessage = "Course does not exist.";
            }
            EnrollmentResponseDTO errorResponse = new EnrollmentResponseDTO();
            errorResponse.setErrorMessage(errorMessage);
            return errorResponse;
        }
    }
    public boolean deleteEnrollment(Long studentId, Long courseId) {
        EnrollmentId enrollmentId = new EnrollmentId(studentId, courseId);
        Optional<Enrollment> enrollmentOptional = enrollmentRepository.findById(enrollmentId);

        if (enrollmentOptional.isPresent()) {
            enrollmentRepository.deleteById(enrollmentId);
            return true;
        } else {
            return false;
        }
    }
    public List<EnrollmentResponseDTO> findEnrollmentsByCourseId(Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findByEnrollmentIdCourseId(courseId);
        return mapEnrollmentsToResponseDTO(enrollments);
    }

    // Method to find enrollments by studentId
    public List<EnrollmentResponseDTO> findEnrollmentsByStudentId(Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByEnrollmentIdStudentId(studentId);
        return mapEnrollmentsToResponseDTO(enrollments);
    }

    private List<EnrollmentResponseDTO> mapEnrollmentsToResponseDTO(List<Enrollment> enrollments) {
        return enrollments.stream()
                .map(enrollment -> new EnrollmentResponseDTO(
                        enrollment.getEnrollmentId().getStudentId(),
                        enrollment.getStudent().getStudentName(),
                        enrollment.getEnrollmentId().getCourseId(),
                        enrollment.getCourse().getCourseName(),
                        enrollment.getEnrollmentDate()
                ))
                .collect(Collectors.toList());
    }
}

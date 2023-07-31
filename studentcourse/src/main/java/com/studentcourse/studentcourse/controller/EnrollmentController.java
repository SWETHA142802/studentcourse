package com.studentcourse.studentcourse.controller;


import com.studentcourse.studentcourse.responseDTO.EnrollmentResponseDTO;
import com.studentcourse.studentcourse.resquestDTO.EnrollmentRequestDTO;
import com.studentcourse.studentcourse.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;
    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> registerEnrollment(@RequestBody EnrollmentRequestDTO requestDTO) {
        EnrollmentResponseDTO responseDTO = enrollmentService.registerEnrollment(requestDTO);

        if (responseDTO != null) {
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{studentId}/{courseId}")
    public ResponseEntity<String> deleteEnrollment(@PathVariable Long studentId, @PathVariable Long courseId) {
        boolean isDeleted = enrollmentService.deleteEnrollment(studentId, courseId);

        if (isDeleted) {
            return new ResponseEntity<>("Enrollment deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Enrollment not found.", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> findEnrollmentsByCourseId(@PathVariable Long courseId) {
        List<EnrollmentResponseDTO> enrollments = enrollmentService.findEnrollmentsByCourseId(courseId);
        if (!enrollments.isEmpty()) {
            return new ResponseEntity<>(enrollments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> findEnrollmentsByStudentId(@PathVariable Long studentId) {
        List<EnrollmentResponseDTO> enrollments = enrollmentService.findEnrollmentsByStudentId(studentId);
        if (!enrollments.isEmpty()) {
            return new ResponseEntity<>(enrollments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

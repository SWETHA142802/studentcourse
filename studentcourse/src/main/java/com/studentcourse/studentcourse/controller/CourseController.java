package com.studentcourse.studentcourse.controller;

import com.studentcourse.studentcourse.responseDTO.CourseResponseDTO;
import com.studentcourse.studentcourse.resquestDTO.CourseRequestDTO;
import com.studentcourse.studentcourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

        @Autowired
        private CourseService courseService;

        // Create Course
        @PostMapping
        public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseRequestDTO requestDTO) {
            CourseResponseDTO responseDTO = courseService.createCourse(requestDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

}

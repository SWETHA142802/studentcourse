package com.studentcourse.studentcourse.service;

import com.studentcourse.studentcourse.data.Course;
import com.studentcourse.studentcourse.repository.CourseRepository;
import com.studentcourse.studentcourse.responseDTO.CourseResponseDTO;
import com.studentcourse.studentcourse.resquestDTO.CourseRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

        @Autowired
        private CourseRepository courseRepository;

        public CourseResponseDTO createCourse(CourseRequestDTO requestDTO) {
            Course course = new Course();
            course.setCourseName(requestDTO.getCourseName());

            Course createdCourse = courseRepository.save(course);

            return new CourseResponseDTO(createdCourse.getCourseId(), createdCourse.getCourseName());
        }
    }


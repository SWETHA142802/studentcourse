package com.studentcourse.studentcourse.repository;

import com.studentcourse.studentcourse.data.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
}

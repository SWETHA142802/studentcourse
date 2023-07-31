package com.studentcourse.studentcourse.repository;

import com.studentcourse.studentcourse.data.Enrollment;
import com.studentcourse.studentcourse.data.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {
    @Modifying
    @Query("UPDATE Enrollment e SET e.enrollmentDate = :enrollmentDate WHERE e.enrollmentId.studentId = :studentId AND e.enrollmentId.courseId = :courseId")
    void updateEnrollmentDetails(@Param("studentId") Long studentId, @Param("courseId") Long courseId, @Param("enrollmentDate") LocalDate enrollmentDate);

    List<Enrollment> findByEnrollmentIdCourseId(Long courseId);

    List<Enrollment> findByEnrollmentIdStudentId(Long studentId);
}

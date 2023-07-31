package com.studentcourse.studentcourse.service;
import com.studentcourse.studentcourse.data.Student;
import com.studentcourse.studentcourse.repository.StudentRepository;
import com.studentcourse.studentcourse.responseDTO.StudentResponseDTO;
import com.studentcourse.studentcourse.resquestDTO.StudentRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentResponseDTO createStudent(StudentRequestDTO requestDTO) {
        Student student = new Student();
        student.setStudentName(requestDTO.getStudentName());

        Student createdStudent = studentRepository.save(student);

        return new StudentResponseDTO(createdStudent.getStudentId(), createdStudent.getStudentName());
    }
}

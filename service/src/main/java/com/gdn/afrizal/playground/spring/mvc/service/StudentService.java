package com.gdn.afrizal.playground.spring.mvc.service;

import com.gdn.afrizal.playground.spring.mvc.dto.StudentRegistration;
import com.gdn.afrizal.playground.spring.mvc.model.Student;

import java.util.List;

public interface StudentService {
    StudentRegistration registerStudent(StudentRegistration registration);

    Student findByStudentId(Long studentId);

    List<Student> findByProgramId(Long programId);
}

package com.gdn.afrizal.playground.spring.service;

import com.gdn.afrizal.playground.spring.dto.StudentRegistration;
import com.gdn.afrizal.playground.spring.model.Student;

import java.util.List;

public interface StudentService {
    StudentRegistration registerStudent(StudentRegistration registration);

    Student findByStudentId(Long studentId);

    List<Student> findByProgramId(Long programId);
}

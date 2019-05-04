package com.gdn.afrizal.playground.spring.mvc.data.repository;

import com.gdn.afrizal.playground.spring.mvc.model.Student;

import java.util.List;

public interface StudentCustomRepository {
  Student findByStudentId(Long studentId);
  List<Student> findByProgramId(Long programId);
  Long countByProgramId(Long programId);
}

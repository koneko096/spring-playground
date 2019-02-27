package com.gdn.afrizal.playground.spring.service;

import com.gdn.afrizal.playground.spring.data.repository.StudentAccountRepository;
import com.gdn.afrizal.playground.spring.data.repository.StudentRepository;
import com.gdn.afrizal.playground.spring.dto.StudentRegistration;
import com.gdn.afrizal.playground.spring.model.Student;
import com.gdn.afrizal.playground.spring.model.StudentAccount;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentAccountRepository studentAccountRepository;

    @Override
    public StudentRegistration registerStudent(StudentRegistration registration) {
        Date entryDate = GregorianCalendar.getInstance().getTime();
        Student student = Student.builder()
                .id(composeIdNum(registration.getProgramId(), entryDate.getYear()))
                .active(true)
                .nationalId(registration.getNationalId())
                .facultyId(registration.getFacultyId())
                .programId(registration.getProgramId())
                .entryDate(entryDate)
                .build();
        Student newStudent = studentRepository.save(student);
        if (newStudent == null) {
            throw new IllegalStateException();
        }

        StudentAccount studentAccount = StudentAccount.builder()
                .studentId(newStudent.getId())
                .active(true)
                .email(registration.getEmail())
                .createdAt(entryDate)
                .modifiedAt(entryDate)
                .username(registration.getUsername())
                .password(registration.getPassword())
                .build();
        StudentAccount newAccount = studentAccountRepository.save(studentAccount);
        if (newAccount == null) {
            throw new IllegalStateException();
        }

        return registration;
    }

    @Override
    public Student findByStudentId(Long studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    @Override
    public List<Student> findByProgramId(Long programId) {
        return studentRepository.findByProgramId(programId);
    }

    private Long composeIdNum(Long programId, int year) {
        return (programId * 100000) + (year % 100 * 1000) +
                (studentRepository.countByProgramId(programId) + 1);
    }
}

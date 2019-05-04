package com.gdn.afrizal.playground.spring.mvc.service;

import com.gdn.afrizal.playground.spring.mvc.data.repository.StudentAccountRepository;
import com.gdn.afrizal.playground.spring.mvc.data.repository.StudentRepository;
import com.gdn.afrizal.playground.spring.mvc.dto.StudentRegistration;
import com.gdn.afrizal.playground.spring.mvc.model.Student;
import com.gdn.afrizal.playground.spring.mvc.model.StudentAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@CacheConfig(cacheNames = "students")
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentAccountRepository studentAccountRepository;

    @Override
    @CachePut
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
    @Cacheable
    public Student findByStudentId(Long studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    @Override
    @Cacheable
    public List<Student> findByProgramId(Long programId) {
        return studentRepository.findByProgramId(programId);
    }

    private Long composeIdNum(Long programId, int year) {
        return (programId * 100000) + (year % 100 * 1000) +
                (studentRepository.countByProgramId(programId) + 1);
    }
}

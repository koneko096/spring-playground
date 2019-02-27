package com.gdn.afrizal.playground.spring.service;

import com.gdn.afrizal.playground.spring.data.repository.CourseRepository;
import com.gdn.afrizal.playground.spring.data.repository.EnrollmentRepository;
import com.gdn.afrizal.playground.spring.dto.StudentEnrollment;
import com.gdn.afrizal.playground.spring.model.Course;
import com.gdn.afrizal.playground.spring.model.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "enrollments")
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override
    @Cacheable
    public List<StudentEnrollment> findByStudentId(Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        List<String> courseIds = enrollments.stream()
                .map(Enrollment::getCourseId).collect(Collectors.toList());
        Map<String, Course> courseMap = courseRepository.findCoursesByCourseIds(courseIds).stream()
                .collect(Collectors.toMap(
                        Course::getId,
                        Function.identity()
                ));
        return enrollments.stream()
                .map(enrollment -> buildStudentEnrollment(courseMap.get(enrollment.getCourseId()), enrollment))
                .collect(Collectors.toList());
    }

    private StudentEnrollment buildStudentEnrollment(Course course, Enrollment enrollment) {
        return StudentEnrollment.builder()
                .enrollment(enrollment)
                .course(course)
                .build();
    }

    @Override
    @Cacheable
    public StudentEnrollment findByStudentIdAndCourseId(Long studentId, String courseId) {
        Enrollment enrollment = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);
        Course course = courseRepository.findByCourseId(courseId);
        if (enrollment == null) {
            return null;
        }
        return StudentEnrollment.builder().course(course).enrollment(enrollment).build();
    }

    @Override
    @CachePut
    public List<Enrollment> enrollStudents(final String courseId, List<Long> studentIds) {
        final Date enrollmentDate = Calendar.getInstance().getTime();
        return enrollmentRepository.insertBulk(studentIds.stream()
                .map(studentId -> buildEnrollment(courseId, studentId, enrollmentDate))
                .collect(Collectors.toList()));
    }

    private Enrollment buildEnrollment(String courseId, Long studentId, Date enrollmentDate) {
        return Enrollment.builder()
                .active(true)
                .courseId(courseId)
                .enrollmentDate(enrollmentDate)
                .studentId(studentId)
                .build();
    }
}

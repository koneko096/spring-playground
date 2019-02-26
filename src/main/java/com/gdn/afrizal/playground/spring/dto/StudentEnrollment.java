package com.gdn.afrizal.playground.spring.dto;

import com.gdn.afrizal.playground.spring.model.Course;
import com.gdn.afrizal.playground.spring.model.Enrollment;
import com.gdn.afrizal.playground.spring.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentEnrollment {
    private Enrollment enrollment;
    private Course course;
}

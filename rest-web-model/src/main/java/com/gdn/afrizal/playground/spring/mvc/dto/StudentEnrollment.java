package com.gdn.afrizal.playground.spring.mvc.dto;

import com.gdn.afrizal.playground.spring.mvc.model.Course;
import com.gdn.afrizal.playground.spring.mvc.model.Enrollment;
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

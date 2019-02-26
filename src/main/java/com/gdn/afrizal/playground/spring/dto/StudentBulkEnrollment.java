package com.gdn.afrizal.playground.spring.dto;

import com.gdn.afrizal.playground.spring.model.Enrollment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentBulkEnrollment {
  private List<Long> studentIds;
  private List<Enrollment> enrollments;
  private String courseId;
}

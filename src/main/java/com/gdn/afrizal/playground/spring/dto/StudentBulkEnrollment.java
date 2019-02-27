package com.gdn.afrizal.playground.spring.dto;

import com.gdn.afrizal.playground.spring.model.Enrollment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentBulkEnrollment {
  @NotEmpty private List<Long> studentIds;
  private List<Enrollment> enrollments;
  @NotBlank private String courseId;
}

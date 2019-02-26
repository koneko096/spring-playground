package com.gdn.afrizal.playground.spring.dto;

import com.gdn.afrizal.playground.spring.dto.validation.ValidEmail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegistration {
  @NotEmpty private String username;
  @ValidEmail private String email;
  @NotEmpty private String password;
  @NotEmpty private Long nationalId;
  @NotEmpty private Long facultyId;
  @NotEmpty private Long programId;
}

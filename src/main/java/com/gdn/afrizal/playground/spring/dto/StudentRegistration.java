package com.gdn.afrizal.playground.spring.dto;

import com.gdn.afrizal.playground.spring.dto.validation.ValidEmail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegistration {
  @NotEmpty private String username;
  @ValidEmail private String email;
  @NotEmpty private String password;
  @NotNull private Long nationalId;
  @NotNull private Long facultyId;
  @NotNull private Long programId;
}

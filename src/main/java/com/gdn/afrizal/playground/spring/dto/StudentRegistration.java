package com.gdn.afrizal.playground.spring.dto;

import com.gdn.afrizal.playground.spring.dto.validation.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegistration {
  @NotBlank private String username;
  @NotBlank @ValidEmail private String email;
  @NotBlank private String password;
  @NotNull private Long nationalId;
  @NotNull private Long facultyId;
  @NotNull private Long programId;
}
